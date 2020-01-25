package com.example.cmuproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import com.example.cmuproject.model.MedicamentosViewModel;
import com.example.cmuproject.model.Medicamento;
import com.example.cmuproject.model.Toma;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;

import java.util.Arrays;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements Login.OnFragmentLoginInteractionListener,
        RegistoFragment.OnFragmentRegisteInteractionListener,
        FirstPage.OnFragmentFirstPageInteractionListener,
        MedicamentoDialog.MediListernerInterface,
        EditMedicamentoDialog.MediEditListernerInterface, TomaDialog.TomaListernerInterface {

    private FirebaseAuth mAuth;
    private MedicamentosViewModel medicamentoViewModel;
    private Toolbar myToolbar;
    private SharedPreferences mSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSettings= getSharedPreferences("themeMode", MODE_PRIVATE);
        String s = mSettings.getString("mode","");
        System.out.println("S ::::::::::::::::::::::::::::: " + s);
        System.out.println("THEME :::::::::::::::::: " + getTheme());
        if(s.equals("light")){
            //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            System.out.println("ENTROU LIGHT");
            //setTheme(R.style.ThemeLight);
        } else if(s.equals("dark")){
            //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            System.out.println("ENTROU DARK");
            //setTheme(R.style.ThemeDark);
        }
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        myToolbar = findViewById(R.id.toolbar);
        myToolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.setting));
        setSupportActionBar(myToolbar);

        mAuth = FirebaseAuth.getInstance();

        medicamentoViewModel = new ViewModelProvider(this).get(MedicamentosViewModel.class);

        Login fragmentLogin = new Login();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

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
        medicamentoViewModel.removeQtd(medicName, quantidade);


        String pattern = "dd/MM/yyyy";
        String dateInString = new SimpleDateFormat(pattern).format(new Date());

        String hour = new SimpleDateFormat("HH:mm").format(new Date());

        //Alterar o local
        Toma newToma = new Toma(medicName, quantidade, dateInString, hour, "Felgueiras");
        System.out.println(newToma.toString());

        medicamentoViewModel.inserToma(newToma);
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



}
