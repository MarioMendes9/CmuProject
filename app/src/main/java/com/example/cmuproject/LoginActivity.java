package com.example.cmuproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.example.cmuproject.model.MedicamentViewModel;
import com.example.cmuproject.model.Medicamento;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements Login.OnFragmentLoginInteractionListener,
                                                    RegistoFragment.OnFragmentRegisteInteractionListener,
                                                    FirstPage.OnFragmentFirstPageInteractionListener,
                                                    MedicamentoDialog.MediListernerInterface{

    private FirebaseAuth mAuth;
    private MedicamentViewModel medicamentoViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        medicamentoViewModel=new ViewModelProvider(this).get(MedicamentViewModel.class);

        Login fragmentLogin=new Login(mAuth);
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

        RegistoFragment changeFrag=new RegistoFragment(mAuth);

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

    @Override
    public void gerirMedicamentosInteraction() {
        GerirMedicamentos gm=new GerirMedicamentos(medicamentoViewModel);

        FragmentTransaction tr=getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.fragment_container,gm);
        tr.addToBackStack(null);
        tr.commit();
    }

    @Override
    public void loadGames() {
        Intent myIntent = new Intent(this, jogo_memoria.class);
        startActivity(myIntent);
    }

    @Override
    public void addDialogMedicamento(String name, int qtd, String timeStamp) {
        Medicamento medicamento=new Medicamento(name,qtd,timeStamp);
        medicamentoViewModel.inserMedicamento(medicamento);
    }
}
