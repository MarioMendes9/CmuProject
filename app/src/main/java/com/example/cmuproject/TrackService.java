package com.example.cmuproject;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import android.app.Service;
import android.content.Intent;
import android.location.Location;

import android.os.Build;
import android.os.IBinder;


import androidx.core.app.NotificationCompat;


import com.example.cmuproject.Database.MedicamentosDB;
import com.example.cmuproject.model.UserLocation;
import com.example.cmuproject.model.UserLocationDao;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class TrackService extends Service {

    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest mLocationRequest;
    private LocationCallback mLocationCallback;
    private MedicamentosDB mydatabase;
    private UserLocationDao userLocationDao;

    private FirebaseAuth auth;
    private DatabaseReference mRootRef;
    private DatabaseReference childRef;


    private List<UserLocation> dbLastLoc;

    private UserLocation userLastLoc;

    public static final String CHANNEL_ID = "com.chikeandroid.tutsplustalerts.ANDROID";
    public static final String channel_name = "ANDROID CHANNEL";
    public static final String channel_description = "Notificaçao da localizaçao";

    public TrackService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
        sendNotification();
    }


    private void sendNotification() {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);
        Notification notification = notificationBuilder.setOngoing(true)
                .setSmallIcon(R.drawable.trackenable)
                .setContentText("We are checking your location")
                .setPriority(Notification.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .build();

        startForeground(1337, notification);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        //de 10 em 10 min
        mLocationRequest.setInterval(3000);
        mLocationRequest.setFastestInterval(3000);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                onNewLocation(locationResult.getLastLocation());
            }
        };
        mydatabase = MedicamentosDB.getDatabase(this);
        userLocationDao = mydatabase.getUserLocationDao();

        String pattern = "dd/MM/yyyy";
        String todayDate = new SimpleDateFormat(pattern).format(new Date());

        MedicamentosDB.databaseWriteExecutor.execute(() -> {
            dbLastLoc = userLocationDao.getLastLocation(todayDate);
            if(dbLastLoc.size()>0){
                userLastLoc = dbLastLoc.get(dbLastLoc.size() - 1);
            }
        });

        auth = FirebaseAuth.getInstance();
        mRootRef = FirebaseDatabase.getInstance().getReference();
        String email = auth.getCurrentUser().getEmail();
        email = email.replace(".", ",");
        childRef = mRootRef.child(email).child("LastLocat");


        startLocationUpdates();


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("vai ser destruido");

    }

    private void onNewLocation(Location location) {
        String pattern = "dd/MM/yyyy";
        if (userLastLoc == null || location.getLatitude() != userLastLoc.lat || location.getLongitude() != userLastLoc.lon) {
            String dateInString = new SimpleDateFormat(pattern).format(new Date());
            String hour = new SimpleDateFormat("HH:mm").format(new Date());
            MedicamentosDB.databaseWriteExecutor.execute(() -> {
                userLastLoc = new UserLocation(location.getLatitude(), location.getLongitude(), dateInString, hour);
                userLocationDao.insertLocation(new UserLocation(location.getLatitude(), location.getLongitude(), dateInString, hour));
                childRef.setValue("lat:"+location.getLatitude()+",long:"+location.getLongitude());
            });


        }
    }

    private void startLocationUpdates() {
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, null);

    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence name = channel_name;
            String description = channel_description;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    /*private void stopLocationUpdates(){
        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
    }*/


}
