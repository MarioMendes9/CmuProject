package com.example.cmuproject;

import android.Manifest;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;

import android.content.Intent;
import android.content.pm.PackageManager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toolbar;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class FirstPage extends Fragment {

    private ImageView img;
    private Button btnGerirMedic;
    private Button botaoJogo;
    private OnFragmentFirstPageInteractionListener mListener;
    private Button btnFarmacias;
    private Button btnFood;
    private Button btnMeal;

    private Toolbar myToolbar;
    private Button btnTomas;

    private FirebaseAuth auth;
    private DatabaseReference mRootRef;
    private DatabaseReference childRef;



    private PendingIntent myPi;


    public FirstPage() {
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getLastLocation();


        int permissionLocation = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionLocation == PackageManager.PERMISSION_GRANTED) {

            if (!isMyServiceRunning(TrackService.class)) {

                Intent i = new Intent(getContext(), TrackService.class);
                getContext().startService(i);
            }


        }


        int permissionMessage = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.SEND_SMS);
        if (permissionMessage == PackageManager.PERMISSION_GRANTED) {
            startAlarm();
        }

        auth = FirebaseAuth.getInstance();
        mRootRef = FirebaseDatabase.getInstance().getReference();
        String email = auth.getCurrentUser().getEmail();
        email = email.replace(".", ",");
        childRef = mRootRef.child(email).child("EmergencyNumber");
        childRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.getValue() == null) {
                    FragmentManager manager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
                    EmergencyNumberDialog dialog = new EmergencyNumberDialog();

                    dialog.show(manager, "dialog");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void getLastLocation() {
       List<String> listPermissionsNeeded = new ArrayList<>();
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if ((ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS)) != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.SEND_SMS);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 100);
            return;
        }


    }

    private void startAlarm() {
        AlarmManager manager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

        ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();

        Date dat = new Date();
        Calendar calNow = Calendar.getInstance();
        calNow.setTime(dat);


        //jantar 20
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        if (calendar.before(calNow)) {
            calendar.add(Calendar.DATE, 1);
        }
        //Noite 00
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY, 0);
        calendar2.set(Calendar.MINUTE, 0);
        calendar2.set(Calendar.SECOND, 0);
        if (calendar2.before(calNow)) {
            calendar2.add(Calendar.DATE, 1);
        }
        //Manha 6
        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTimeInMillis(System.currentTimeMillis());
        calendar3.set(Calendar.HOUR_OF_DAY, 6);
        calendar3.set(Calendar.MINUTE, 0);
        calendar3.set(Calendar.SECOND, 0);
        if (calendar3.before(calNow)) {
            calendar3.add(Calendar.DATE, 1);
        }

        //Almoço 12
        Calendar calendar4 = Calendar.getInstance();
        calendar4.setTimeInMillis(System.currentTimeMillis());
        calendar4.set(Calendar.HOUR_OF_DAY, 12);
        calendar4.set(Calendar.MINUTE, 0);
        calendar4.set(Calendar.SECOND, 0);
        if (calendar4.before(calNow)) {
            calendar4.add(Calendar.DATE, 1);
        }
        //Tarde 14

        Calendar calendar5 = Calendar.getInstance();
        calendar5.setTimeInMillis(System.currentTimeMillis());
        calendar5.set(Calendar.HOUR_OF_DAY, 14);
        calendar5.set(Calendar.MINUTE, 0);
        calendar5.set(Calendar.SECOND, 0);
        if (calendar5.before(calNow)) {
            calendar5.add(Calendar.DATE, 1);
        }
        ArrayList<Calendar> myCal = new ArrayList<>();
        myCal.add(calendar);
        myCal.add(calendar2);
        myCal.add(calendar3);
        myCal.add(calendar4);
        myCal.add(calendar5);

        long timee = System.currentTimeMillis();


        for (int i = 0; i < myCal.size(); i++) {
            Intent alarmIntent = new Intent(getActivity(), AlarmReceiver.class);
            myPi = PendingIntent.getBroadcast(getActivity(), i, alarmIntent, 0);
            manager.set(AlarmManager.RTC_WAKEUP, myCal.get(i).getTimeInMillis(), myPi);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_first_page, container, false);
        img = view.findViewById(R.id.principalImage);
        btnGerirMedic = view.findViewById(R.id.gerirMedicamentos);
        btnFarmacias = view.findViewById(R.id.farmaciaMap);
        btnTomas = view.findViewById(R.id.tomasMedicamentos);
        btnFood = view.findViewById(R.id.foodDetails);
        btnMeal = view.findViewById(R.id.mealDetails);



        btnGerirMedic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mListener.gerirMedicamentosInteraction();
            }
        });

        botaoJogo = view.findViewById(R.id.jogos);
        botaoJogo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mListener.loadGames();
            }
        });

        img.setImageResource(R.drawable.logo);

        btnFarmacias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.loadMapaFarmacias();
            }
        });

        btnTomas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLastLocation();
                mListener.gerirTomas();
            }
        });
        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.foodDetails();
            }
        });
        btnMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.loadRecipes();
            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mListener = (OnFragmentFirstPageInteractionListener) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public interface OnFragmentFirstPageInteractionListener {
        void gerirMedicamentosInteraction();

        void loadGames();

        void loadMapaFarmacias();

        void gerirTomas();

        void foodDetails();

        void loadRecipes();

    }

}
