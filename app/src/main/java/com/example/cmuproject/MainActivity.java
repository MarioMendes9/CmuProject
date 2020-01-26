package com.example.cmuproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;

import androidx.core.content.ContextCompat;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;



import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import android.graphics.Color;
import android.location.Location;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import com.example.cmuproject.model.MedicamentosViewModel;
import com.example.cmuproject.model.Medicamento;
import com.example.cmuproject.model.Toma;
import com.example.cmuproject.retrofit_models.RegionDetails;
import com.example.cmuproject.services.OpenStreetService;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Login.OnFragmentLoginInteractionListener,
        RegistoFragment.OnFragmentRegisteInteractionListener,
        FirstPage.OnFragmentFirstPageInteractionListener,
        MedicamentoDialog.MediListernerInterface,
        EditMedicamentoDialog.MediEditListernerInterface, TomaDialog.TomaListernerInterface {

    private FirebaseAuth mAuth;
    private MedicamentosViewModel medicamentoViewModel;
    private Toolbar myToolbar;
    private SharedPreferences mSettings;
    private List<Medicamento> medis;

    private PendingIntent myPi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        System.out.println(getTheme());
        setTheme(R.style.ThemeLight);
        mSettings= getSharedPreferences("themeMode", MODE_PRIVATE);
        String s = mSettings.getString("mode","");
        if(s.equals("light")){
            setTheme(R.style.ThemeLight);
        } else if(s.equals("dark")){
            setTheme(R.style.ThemeDark);
        }
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        myToolbar = findViewById(R.id.toolbar);
        myToolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.setting));
        setSupportActionBar(myToolbar);

        mAuth = FirebaseAuth.getInstance();

        medicamentoViewModel = new ViewModelProvider(this).get(MedicamentosViewModel.class);

        medicamentoViewModel.getallMedicamentos().observe(this, new Observer<List<Medicamento>>() {
            @Override
            public void onChanged(List<Medicamento> medicamentos) {
                medis = medicamentos;
            }
        });


        Login fragmentLogin = new Login();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.fragment_container, fragmentLogin);
        ft.commit();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 100:

                if (permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent i = new Intent(this, TrackService.class);
                    startService(i);
                } else if (permissions[0].equals(Manifest.permission.SEND_SMS) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startAlarm();
                }
                else if (permissions[1].equals(Manifest.permission.SEND_SMS) &&grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                        startAlarm();
                    }


                break;


        }

    }


    @Override
    public void onFragmentLoginInteraction(FirebaseUser user) {
        FirstPage changeFrag = new FirstPage();

        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.fragment_container, changeFrag);
        tr.addToBackStack(null);
        tr.commit();


    }

    @Override
    public void onFragmentRegistInteraction() {

        RegistoFragment changeFrag = new RegistoFragment();

        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.fragment_container, changeFrag);
        tr.addToBackStack(null);
        tr.commit();
    }

    @Override
    public void onFragmentRegisteInteraction(FirebaseUser user) {
        FirstPage changeFrag = new FirstPage();

        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.fragment_container, changeFrag);
        tr.addToBackStack(null);
        tr.commit();
    }

    @Override
    public void gerirMedicamentosInteraction() {
        GerirMedicamentos gm = new GerirMedicamentos();

        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.fragment_container, gm);
        tr.addToBackStack(null);
        tr.commit();
    }

    @Override
    public void loadGames() {
        Intent myIntent = new Intent(this, MenuJogos.class);
        startActivity(myIntent);
    }


    public void addDialogMedicamento(String name, int qtd, String[] dias, String[] alturas) {
        Medicamento medicamento = new Medicamento(name, qtd, Arrays.toString(dias), Arrays.toString(alturas));
        medicamentoViewModel.inserMedicamento(medicamento);
    }

    public void loadMapaFarmacias() {
        Intent mIntent = new Intent(this, FarmaciaMapsActivity.class);
        startActivity(mIntent);
    }

    @Override
    public void gerirTomas() {
        GerirTomas gt = new GerirTomas();

        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.fragment_container, gt);
        tr.addToBackStack(null);
        tr.commit();
    }

    @Override
    public void foodDetails() {
        Intent mIntent = new Intent(this, FoodActivity.class);
        startActivity(mIntent);
    }

    @Override
    public void loadRecipes() {
        Intent mIntent = new Intent(this, RecipesActivity.class);
        startActivity(mIntent);
    }

    @Override
    public void deleteMedicament(Medicamento medi) {
        medicamentoViewModel.removeMedicamento(medi);
    }

    @Override
    public void editMedicament(String tempName, int tempQuant, String tempDays, String tempAlturas) {
        medicamentoViewModel.updateMedic(tempName, tempQuant, tempDays, tempAlturas);
    }

    @Override
    public void addTomaDialog(String medicName, int quantidade) {
        getLastLocation();
        medicamentoViewModel.removeQtd(medicName, quantidade);



        String pattern = "dd/MM/yyyy";
        String dateInString = new SimpleDateFormat(pattern).format(new Date());

        String hour = new SimpleDateFormat("HH:mm").format(new Date());

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location!=null){
                            getApiStreet().getTown(location.getLatitude(), location.getLongitude())
                                    .enqueue(new Callback<RegionDetails>() {
                                        @Override
                                        public void onResponse(Call<RegionDetails> call, Response<RegionDetails> response) {
                                            String regi = response.body().getAddress().getCounty();
                                            if (regi.split(" ").length > 0) {
                                                regi.replace(" ", "-");
                                            }
                                            Toma newToma = new Toma(medicName, quantidade, dateInString, hour, regi);
                                            System.out.println(newToma.toString());

                                            medicamentoViewModel.inserToma(newToma);
                                        }

                                        @Override
                                        public void onFailure(Call<RegionDetails> call, Throwable t) {
                                            System.out.println(t.fillInStackTrace());

                                        }
                                    });

                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println(e.fillInStackTrace());
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_def:

                Intent mIntent = new Intent(this, SettingsActivity.class);
                mIntent.putExtra("theme", mSettings.getString("mode",""));
                startActivity(mIntent);

                break;

            case R.id.action_logout:
                mAuth.signOut();
                Login fragmentLogin = new Login();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                ft.replace(R.id.fragment_container, fragmentLogin);
                ft.commit();
                Intent i = new Intent(this, TrackService.class);
                stopService(i);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    public long checkQuantity(String medicaName) {
        for (int i = 0; i < medis.size(); i++) {
            if (medis.get(i).name == medicaName) {
                return medis.get(i).quantity;
            }
        }
        return 0;
    }

    private void startAlarm() {

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

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
        //Noite 22
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(Calendar.HOUR_OF_DAY, 22);
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
            Intent alarmIntent = new Intent(this, AlarmReceiver.class);
            myPi = PendingIntent.getBroadcast(this, i, alarmIntent, 0);
            manager.set(AlarmManager.RTC_WAKEUP, myCal.get(i).getTimeInMillis(), myPi);
        }

    }

    private Retrofit getRetrofitStreet() {
        return new Retrofit.Builder()
                .baseUrl("https://nominatim.openstreetmap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OpenStreetService getApiStreet() {
        return getRetrofitStreet().create(OpenStreetService.class);
    }
}
