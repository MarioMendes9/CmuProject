package com.example.cmuproject.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cmuproject.MedicamentosRepository;

import java.util.List;

public class MedicamentosViewModel extends AndroidViewModel {

    private MedicamentosRepository mRepository;
    private LiveData<List<Medicamento>> allMedicamentos;
    private LiveData<List<Toma>> allTomas;



    private MutableLiveData<List<PendentToma>> pendentTomas;



    public MedicamentosViewModel(@NonNull Application application) {
        super(application);
        mRepository=new MedicamentosRepository(application);
        allMedicamentos=mRepository.getmMedicamentos();

        allTomas=mRepository.getMTomas();
        pendentTomas=new MutableLiveData<>();
    }

    public LiveData<List<Medicamento>> getallMedicamentos() {
        return allMedicamentos;
    }

    public LiveData<List<Toma>> getAllTomas(){return allTomas;};

    public void inserMedicamento(Medicamento medi){
        mRepository.insertMedicamento(medi);
    }

    public void removeMedicamento(Medicamento medi){
        mRepository.removeMedicamento(medi);
    }

    public void updateMedic(String tempName,int tempQuant,String tempDays,String tempAlturas){
        mRepository.updateMedicamento(tempName,tempQuant,tempDays,tempAlturas);
    }
    public void removeQtd(String name,int qtd){
        mRepository.removeQuantidade(name,qtd);
    }

    public void inserToma(Toma toma){
        mRepository.insertToma(toma);
    }

    public LiveData<List<Toma>> getTodayTomas(String date){
       return mRepository.getTodayToma(date);
    }

    public void removeToma(Toma toma){
        mRepository.removeToma(toma);
    }

    public LiveData<List<PendentToma>> getPendentTomas(){
        return pendentTomas;
    }

    public void setPendentTomas(List<PendentToma> pendentTomas) {
        this.pendentTomas.setValue(pendentTomas);
    }




}
