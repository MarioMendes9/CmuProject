package com.example.cmuproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;

public class Jogos extends AppCompatActivity implements ResultadoNumberFragment.OnFragmentJogoAritmeticoResultadoInteractionListener, MenuJogosF.OnFragmentMenuJogosListener, JogoPalavrasFragmento.OnFragmentJogoPalavrasListener, JogoMemoriaFragmento.OnFragmentJogoMemoriaListener, JogoAritmeticoFragmento.OnFragmentJogoAritmeticoListener, ResultadoMemoryFragment.OnFragmentMemoryResultInteractionListener, ResultadoWordFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogos);


        MenuJogosF fragmentMenu=new MenuJogosF();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        ft.replace(R.id.fragment_container,fragmentMenu);
        ft.commit();


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
    public void OnFragmentMenuJogosListener(Uri uri) {
        MenuJogosF changeFragment=new MenuJogosF();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

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
