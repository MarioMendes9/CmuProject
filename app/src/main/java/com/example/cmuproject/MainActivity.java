package com.example.cmuproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.DatabaseMetaData;
import java.text.SimpleDateFormat;

import java.util.Arrays;
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
    private static final int REQUEST_FINE_LOCATION = 100;
    private FusedLocationProviderClient mFusedLocationClient;


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
                medis=medicamentos;
            }
        });


        Login fragmentLogin=new Login();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        ft.replace(R.id.fragment_container, fragmentLogin);
        ft.commit();

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

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
            return;
        }
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINE_LOCATION);
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
