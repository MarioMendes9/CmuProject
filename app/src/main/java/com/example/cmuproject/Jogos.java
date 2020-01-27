package com.example.cmuproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class Jogos extends AppCompatActivity implements MenuJogosF.OnFragmentMenuJogosListener, JogoMemoriaF.OnFragmentInteractionListener {

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
    public void onFragmentInteraction(Uri uri) {
        MenuJogosF changeFrag=new MenuJogosF();

        FragmentTransaction tr=getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.fragment_container,changeFrag);
        tr.addToBackStack(null);
        tr.commit();
    }


    @Override
    public void jogoMemoria() {
        MenuJogosF changeFrag=new MenuJogosF();

        FragmentTransaction tr=getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.fragment_container,changeFrag);
        tr.addToBackStack(null);
        tr.commit();
    }

    @Override
    public void jogoAritmetico() {
        Intent myIntent = new Intent(this, JogoAritmeticoF.class);
        startActivity(myIntent);
    }

    @Override
    public void jogoPalavras() {
        Intent myIntent = new Intent(this, JogoPalavrasF.class);
        startActivity(myIntent);
    }
}
