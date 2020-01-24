package com.example.cmuproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.example.cmuproject.model.MedicamentosViewModel;
import com.example.cmuproject.model.Medicamento;
import com.example.cmuproject.model.Toma;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Login.OnFragmentLoginInteractionListener,
                                                    RegistoFragment.OnFragmentRegisteInteractionListener,
                                                    FirstPage.OnFragmentFirstPageInteractionListener,
                                                    MedicamentoDialog.MediListernerInterface,
                                                EditMedicamentoDialog.MediEditListernerInterface,TomaDialog.TomaListernerInterface{

    private FirebaseAuth mAuth;
    private MedicamentosViewModel medicamentoViewModel;
    private List<Medicamento> medis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        medicamentoViewModel=new ViewModelProvider(this).get(MedicamentosViewModel.class);

        medicamentoViewModel.getallMedicamentos().observe(this, new Observer<List<Medicamento>>() {
            @Override
            public void onChanged(List<Medicamento> medicamentos) {
                medis=medicamentos;
            }
        });


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

    @Override
    public void gerirMedicamentosInteraction() {
        GerirMedicamentos gm=new GerirMedicamentos();

        FragmentTransaction tr=getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.fragment_container,gm);
        tr.addToBackStack(null);
        tr.commit();
    }

    @Override
    public void loadGames() {
        Intent myIntent = new Intent(this, MenuJogos.class);
        startActivity(myIntent);
    }



    public void addDialogMedicamento(String name, int qtd, String[] dias, String[] alturas) {
        Medicamento medicamento=new Medicamento(name,qtd, Arrays.toString(dias),Arrays.toString(alturas));
        medicamentoViewModel.inserMedicamento(medicamento);
    }
    public void loadMapaFarmacias() {
        Intent mIntent = new Intent(this, FarmaciaMaps.class);
        startActivity(mIntent);
    }

    @Override
    public void gerirTomas() {
        GerirTomas gt=new GerirTomas();

        FragmentTransaction tr=getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.fragment_container,gt);
        tr.addToBackStack(null);
        tr.commit();
    }
    @Override
    public void foodDetails() {
        Intent mIntent = new Intent(this, Food.class);
        startActivity(mIntent);
    }

    @Override
    public void deleteMedicament(Medicamento medi) {
        medicamentoViewModel.removeMedicamento(medi);
    }

    @Override
    public void editMedicament(String tempName, int tempQuant, String tempDays, String tempAlturas) {
        medicamentoViewModel.updateMedic(tempName,tempQuant,tempDays,tempAlturas);
    }

    @Override
    public void addTomaDialog(String medicName, int quantidade) {
        medicamentoViewModel.removeQtd(medicName,quantidade);


        String pattern = "dd/MM/yyyy";
        String dateInString =new SimpleDateFormat(pattern).format(new Date());

        String hour=new SimpleDateFormat("HH:mm").format(new Date());

        //Alterar o local
        Toma newToma=new Toma(medicName,quantidade,dateInString,hour,"Felgueiras");
        System.out.println(newToma.toString());

        medicamentoViewModel.inserToma(newToma);
    }

    @Override
    public long checkQuantity(String medicaName) {
        for (int i = 0; i < medis.size(); i++) {
            if (medis.get(i).name == medicaName) {
                return medis.get(i).quantity;
            }
        }
        return 0;
    }
}
