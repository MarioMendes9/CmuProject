package com.example.cmuproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

public class JogosActivity extends AppCompatActivity implements JogosMenuFragment.OnFragmentMenuJogosInteractionListener, ResultadoNumberFragment.OnFragmentJogoAritmeticoResultadoInteractionListener, JogoPalavrasFragmento.OnFragmentJogoPalavrasListener, JogoMemoriaFragmento.OnFragmentJogoMemoriaListener, JogoAritmeticoFragmento.OnFragmentJogoAritmeticoListener, ResultadoMemoryFragment.OnFragmentMemoryResultInteractionListener, ResultadoWordFragment.OnFragmentInteractionListener {

    private Toolbar myToolbar;
    private SharedPreferences mSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSettings= getSharedPreferences("themeMode", MODE_PRIVATE);
        String s = mSettings.getString("mode","");
        if(s.equals("light")){
            setTheme(R.style.ThemeLight);
        } else if(s.equals("dark")){
            setTheme(R.style.ThemeDark);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogos);


        myToolbar = findViewById(R.id.toolbar);
        myToolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.setting));
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        JogosMenuFragment chFragment = new JogosMenuFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container,chFragment);
        ft.commit();


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


    @Override
    public void onFragmentJogoAritmeticoListener(Uri uri) {
        JogoAritmeticoFragmento changeFragment=new JogoAritmeticoFragmento();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        ft.replace(R.id.fragment_container,changeFragment);
        ft.commit();

    }

    @Override
    public void resultadoJogoAritmetico(String count) {
        ResultadoNumberFragment changeFragment=new ResultadoNumberFragment();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        Bundle args = new Bundle();
        args.putString("countNumber", count);
        changeFragment.setArguments(args);

        ft.replace(R.id.fragment_container,changeFragment);
        ft.commit();
    }


    @Override
    public void onFragmentJogoMemoriaListener(Uri uri) {
        JogoMemoriaFragmento changeFragment=new JogoMemoriaFragmento();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        ft.replace(R.id.fragment_container,changeFragment);
        ft.commit();
    }


    @Override
    public void resultadoMemoria() {
        ResultadoMemoryFragment changeFragment=new ResultadoMemoryFragment();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        ft.replace(R.id.fragment_container,changeFragment);
        ft.commit();
    }

    @Override
    public void OnFragmentJogoPalavrasListener(Uri uri) {
        JogoPalavrasFragmento changeFragment=new JogoPalavrasFragmento();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        ft.replace(R.id.fragment_container,changeFragment);
        ft.commit();
    }

    @Override
    public void resultadoWord(String count) {
        ResultadoWordFragment changeFragment=new ResultadoWordFragment();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        Bundle args = new Bundle();
        args.putString("countNumber", count);
        changeFragment.setArguments(args);

        ft.replace(R.id.fragment_container,changeFragment);
        ft.commit();
    }




    @Override
    public void onFragmentMemoryResultInteraction(Uri uri) {
        ResultadoMemoryFragment changeFragment=new ResultadoMemoryFragment();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        ft.replace(R.id.fragment_container,changeFragment);
        ft.commit();
    }




    @Override
    public void jogoMemoria() {
        JogoMemoriaFragmento changeFragment=new JogoMemoriaFragmento();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        ft.replace(R.id.fragment_container,changeFragment);
        ft.commit();
    }

    @Override
    public void onFragmentJogoAritmeticoResultadoInteraction(Uri uri) {
        ResultadoNumberFragment changeFragment=new ResultadoNumberFragment();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        ft.replace(R.id.fragment_container,changeFragment);
        ft.commit();
    }

    @Override
    public void jogoAritmetico() {
        JogoAritmeticoFragmento changeFragment=new JogoAritmeticoFragmento();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        ft.replace(R.id.fragment_container,changeFragment);
        ft.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        ResultadoWordFragment changeFragment=new ResultadoWordFragment();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        ft.replace(R.id.fragment_container,changeFragment);
        ft.commit();
    }

    @Override
    public void jogoPalavras() {
        JogoPalavrasFragmento changeFragment=new JogoPalavrasFragmento();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        ft.replace(R.id.fragment_container,changeFragment);
        ft.commit();

    }
}
