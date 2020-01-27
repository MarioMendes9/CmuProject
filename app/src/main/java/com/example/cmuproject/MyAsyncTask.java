package com.example.cmuproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.telephony.SmsManager;

import androidx.annotation.NonNull;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


import com.example.cmuproject.Database.MedicamentosDB;
import com.example.cmuproject.model.Medicamento;
import com.example.cmuproject.model.MedicamentoDao;
import com.example.cmuproject.model.Toma;
import com.example.cmuproject.model.TomaDao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyAsyncTask extends AsyncTask<Void, Void, Void> {

    private Context context;
    private List<Medicamento> mymedis;
    private List<Toma> todayTomas;
    private String previousAltura;
    private String altura;
    private List<Medicamento> previousAlturaMedis;
    private String todayIS;

    private FirebaseAuth auth;
    private DatabaseReference mRootRef;
    private DatabaseReference childRef;

    private NotificationManagerCompat notificationManager;
    public static final int NOTIFICATION_ID = 888;
    public static final String CHANNEL_ID = "com.chikeandroid.tutsplustalerts.ANDROID";
    public static final String channel_name = "ANDROID CHANNEL";
    public static final String channel_description = "Notificaçao que tem medicamentos para tomar";
    private NotificationCompat.Builder builder;

    public MyAsyncTask(Context context) {
        this.context = context;
        previousAlturaMedis = new ArrayList<>();
    }

    @Override
    protected Void doInBackground(Void... voids) {

        System.out.println("doInBackground");
        String hour = (new SimpleDateFormat("HH:mm").format(new Date())).split(":")[0];
        altura = getAltura(hour);
        System.out.println(altura);
        todayIS=getDayOfWeek();
        if (altura.equals("Manha")) {
            previousAltura = "Noite";
        } else if (altura.equals("Almoço")) {
            previousAltura = "Manha";
        } else if (altura.equals("Tarde")) {
            previousAltura = "Almoço";
        } else if (altura.equals("Jantar")) {
            previousAltura = "Tarde";
        } else {
            previousAltura = "Jantar";
        }

        System.out.println(previousAltura);
        Date mydate=new Date();
        if(previousAltura.equals("Jantar")){
            mydate = new Date(mydate.getTime() - 86400000); // 7 * 24 * 60 * 60 * 1000
        }
        System.out.println(":::::::::::::::::::::::::::::::: "+mydate);
        auth = FirebaseAuth.getInstance();
        mRootRef = FirebaseDatabase.getInstance().getReference();
        String pattern = "dd/MM/yyyy";
        String dateInString = new SimpleDateFormat(pattern).format(mydate);
        MedicamentosDB mydb = MedicamentosDB.getDatabase(context);
        MedicamentoDao mydao = mydb.getMedicamentosDao();
        TomaDao tomaDao = mydb.getTomasDao();
        mymedis = mydao.loadAllMedicamentos2();
        todayTomas = tomaDao.getTodayTomas2(dateInString);


        /**
         * Ver se existem medicamentos na proxima hora para enviar notificaçao a dizer que tem
         */

        String tempTodayis=todayIS;

        if(previousAltura.equals("Jantar")){
            switch (todayIS) {
                case "Segunda":
                    tempTodayis = "Domingo";
                    break;
                case "Terça":
                    tempTodayis = "Segunda";
                    break;
                case "Quarta":
                    tempTodayis = "Terça";
                    break;
                case "Quinta":
                    tempTodayis = "Quarta";
                    break;
                case "Sexta":
                    tempTodayis = "Quinta";
                    break;
                case "Sabado":
                    tempTodayis = "Sexta";
                    break;
                case "Domingo":
                    tempTodayis = "Sabado";
                    break;
            }
        }

        boolean havenext = false;
        for (int i = 0; i < mymedis.size() && !havenext; i++) {
            String tempDays = mymedis.get(i).days;
            tempDays = tempDays.replace(" ", "");
            tempDays = tempDays.substring(1, tempDays.length() - 1);
            String[] thisDays = tempDays.split(",");
            for (int j = 0; j< thisDays.length && !havenext; j++) {
                System.out.println("COMPARA DIAS:"+thisDays[j]+" "+todayIS);
                if (thisDays[j].equals(todayIS)) {
                    String tempAlturas = mymedis.get(i).alturas;
                    tempAlturas = tempAlturas.replace(" ", "");
                    tempAlturas = tempAlturas.substring(1, tempAlturas.length() - 1);
                    String[] thisAlturas = tempAlturas.split(",");
                    for (int k = 0; k < thisAlturas.length; k++) {
                        System.out.println("COMPARA ALTURAS: "+thisAlturas[k].equals(altura));
                        if (thisAlturas[k].equals(altura)) {
                            havenext = true;
                            break;
                        }
                    }
                }
            }

        }

        /**
         * Ver se foram feitas todas as tomas
         */



        for (int i = 0; i < mymedis.size(); i++) {
            String tempDays = mymedis.get(i).days;
            tempDays = tempDays.replace(" ", "");
            tempDays = tempDays.substring(1, tempDays.length() - 1);
            String[] thisDays = tempDays.split(",");
            for (int j = 0;j < thisDays.length; j++) {
                System.out.println(thisDays[j]+" :::::::::::::: "+tempTodayis);
                if (thisDays[j].equals(tempTodayis)) {
                    String tempAlturas = mymedis.get(i).alturas;
                    tempAlturas = tempAlturas.replace(" ", "");
                    tempAlturas = tempAlturas.substring(1, tempAlturas.length() - 1);
                    String[] thisAlturas = tempAlturas.split(",");
                    for (int k = 0; k < thisAlturas.length; k++) {
                        System.out.println(thisAlturas[k]+" :::::::::::::: "+previousAltura);

                        if (thisAlturas[k].equals(previousAltura)) {
                            previousAlturaMedis.add(mymedis.get(i));
                            break;
                        }
                    }
                }
            }

        }


        System.out.println("**********************************");
        System.out.println("medis size: " + mymedis.size());
        System.out.println("tomastoday: " + todayTomas.size());
        System.out.println("TEM NEXT: " + havenext);
        System.out.println("Medis da toma anterior: " + previousAlturaMedis.size());
        System.out.println("**********************************+");

        if (havenext) {
            sendNotification();
        }


        if (previousAlturaMedis.size() > 0) {
            for (int i = 0; i < previousAlturaMedis.size(); i++) {
                boolean done = false;
                for (int j = 0; j < todayTomas.size() && !done; j++) {

                    if (todayTomas.get(j).medicamentoName.equals(previousAlturaMedis.get(i).name)) {
                        if (previousAltura.equals(getAltura(todayTomas.get(j).hora))) {
                            done = true;
                            break;
                        }
                    }
                }
                if (!done) {
                    //send message
                    // Toast.makeText(context,"Nao fez esta toma",Toast.LENGTH_SHORT).show();
                    System.out.println("NAO FEZ ESTA TOMA ***************************");
                    System.out.println(previousAltura + " " + previousAlturaMedis.get(i).name);
                    sendSMS(i);

                }
            }
        }


        return null;
    }

    public void sendSMS(int i) {
        String email = auth.getCurrentUser().getEmail();
        email = email.replace(".", ",");
        childRef = mRootRef.child(email);
        childRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try {
                    JSONObject myobject = new JSONObject(dataSnapshot.getValue().toString());
                    String message = "O utilizador " + auth.getCurrentUser().getEmail() + " nao tomou o medicamento " + previousAlturaMedis.get(i).name + " na altura " + previousAltura;
                    System.out.println("MESSAGE ::::::::::::::::::::::::: " + message);
                    System.out.println("NUMERO :::::::::::::::" + myobject.getString("EmergencyNumber"));

                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(myobject.getString("EmergencyNumber"), null, message, null, null);

                    //Intent intent = new Intent(context,smsReceiver.class);
                    //intent.setData(Uri.parse("smsto:"+ myobject.getString("EmergencyNumber")));  // This ensures only SMS apps respond
                    //intent.putExtra("sms_body", message);

                    System.out.println("vai enviar");
                 //   context.sendBroadcast(intent);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void sendNotification() {
        System.out.println("ENVIOU A NOTIFICAÇAO");
        builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.meal_icon)
                .setContentTitle("Medicaçao")
                .setContentText("Tem medicaçao para tomar nas proximas horas")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        // builder.setDeleteIntent(getDeleteIntent());
        Intent resultIntent = new Intent(Intent.ACTION_VIEW);
        resultIntent.setData(Uri.parse("sms:"));
        PendingIntent resultPedingIntent = PendingIntent.getActivity(context, 0, resultIntent, 0);


        //builder.addAction(R.drawable.myimage_background,"mensage",resultPedingIntent);

        notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // CharSequence name = getString(R.string.channel_name);
            CharSequence name = channel_name;
            String description = channel_description;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private String getDayOfWeek() {
        Date calendar = Calendar.getInstance().getTime();
        String tempDate = calendar.toString();
        String[] myDate = tempDate.split(" ");
        String todayIs = "";

        switch (myDate[0]) {
            case "Mon":
                todayIs = "Segunda";
                break;
            case "Tue":
                todayIs = "Terça";
                break;
            case "Wed":
                todayIs = "Quarta";
                break;
            case "Thu":
                todayIs = "Quinta";
                break;
            case "Fri":
                todayIs = "Sexta";
                break;
            case "Sat":
                todayIs = "Sabado";
                break;
            case "Sun":
                todayIs = "Domingo";
                break;
        }
        return todayIs;
    }

    private String getAltura(String hora) {

        String[] tempHora = hora.split(":");

        int thehora = Integer.parseInt(tempHora[0]);
        if (thehora >= 6 && thehora < 12) {
            return "Manha";
        } else if (thehora >= 12 && thehora < 14) {
            return "Almoço";

        } else if (thehora >= 14 && thehora < 20) {
            return "Tarde";

        } else if (thehora >= 20 && thehora <= 23) {
            return "Jantar";

        } else {
            return "Noite";

        }

    }
}
