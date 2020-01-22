package com.example.cmuproject.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cmuproject.MedicamentoRepository;

import java.util.List;

public class MedicamentViewModel extends AndroidViewModel {

    private MedicamentoRepository mRepository;
    private LiveData<List<Medicamento>> allMedicamentos;


    public MedicamentViewModel(@NonNull Application application) {
        super(application);
        mRepository=new MedicamentoRepository(application);
        allMedicamentos=mRepository.getmMedicamentos();
    }

    public LiveData<List<Medicamento>> getallMedicamentos() {
        return allMedicamentos;
    }

    public void inserMedicamento(Medicamento medi){
        mRepository.insertMedicamento(medi);
    }

    public void removeMedicamento(Medicamento medi){
        mRepository.removeMedicamento(medi);
    }

    public void updateMedic(String tempName,int tempQuant,String tempDays,String tempAlturas){
        mRepository.updateMedicamento(tempName,tempQuant,tempDays,tempAlturas);
    }
}
