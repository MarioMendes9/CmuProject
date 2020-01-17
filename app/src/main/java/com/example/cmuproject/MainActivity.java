package com.example.cmuproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements Login.OnFragmentLoginInteractionListener,RegistoFragment.OnFragmentRegisteInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login fragmentLogin=new Login();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        ft.replace(R.id.fragment_container,fragmentLogin);
        ft.commit();


    }


    @Override
    public void onFragmentLoginInteraction(FirebaseUser user) {
        FirstPage changeFrag=new FirstPage();

        FragmentTransaction tr=getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.fragment_container,changeFrag);
        tr.addToBackStack(null);
        tr.commit();


    }

    @Override
    public void onFragmentRegistInteraction() {

        RegistoFragment changeFrag=new RegistoFragment();

        FragmentTransaction tr=getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.fragment_container,changeFrag);
        tr.addToBackStack(null);
        tr.commit();
    }

    @Override
    public void onFragmentRegisteInteraction(FirebaseUser user) {
        FirstPage changeFrag=new FirstPage();

        FragmentTransaction tr=getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.fragment_container,changeFrag);
        tr.addToBackStack(null);
        tr.commit();
    }
}
