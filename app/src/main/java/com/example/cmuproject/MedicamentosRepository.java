package com.example.cmuproject;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.cmuproject.Database.MedicamentosDB;
import com.example.cmuproject.model.Medicamento;
import com.example.cmuproject.model.MedicamentoDao;
import com.example.cmuproject.model.Toma;
import com.example.cmuproject.model.TomaDao;

import java.util.List;

public class MedicamentosRepository {

    private MedicamentoDao mMedicaDao;
    private LiveData<List<Medicamento>> mMedicamentos;

    private TomaDao mTomaDao;
    private LiveData<List<Toma>> mTomas;

    public MedicamentosRepository(Application application) {
        MedicamentosDB db = MedicamentosDB.getDatabase(application);

        this.mMedicaDao = db.getMedicamentosDao();
        this.mMedicamentos = mMedicaDao.loadAllMedicamentos();

        this.mTomaDao = db.getTomasDao();
        this.mTomas = mTomaDao.loadAllTomas();


    }

    public LiveData<List<Medicamento>> getmMedicamentos() {
        return mMedicamentos;
    }

    public LiveData<List<Toma>> getMTomas() {
        return mTomas;
    }

    public void insertMedicamento(final Medicamento medi) {
        MedicamentosDB.databaseWriteExecutor.execute(() -> {
            mMedicaDao.insertMedicamento(medi);
        });
    }

    public void removeMedicamento(Medicamento medi) {
        MedicamentosDB.databaseWriteExecutor.execute(() -> {
            mMedicaDao.deleteMedicamento(medi);
        });
    }

    public void updateMedicamento(String tempName, int tempQuant, String tempDays, String tempAlturas) {
        MedicamentosDB.databaseWriteExecutor.execute(() -> {
            mMedicaDao.updateMedic(tempName, tempQuant, tempDays, tempAlturas);
        });
    }
    public void removeQuantidade(String name,int qtd){
        MedicamentosDB.databaseWriteExecutor.execute(()->{
            mMedicaDao.removeQuantidade(name,qtd);
        });
    }

    public void insertToma(Toma toma){
        MedicamentosDB.databaseWriteExecutor.execute(()->{
            mTomaDao.insertToma(toma);
        });
    }

    public void removeToma(Toma toma){
        MedicamentosDB.databaseWriteExecutor.execute(()->{
            mTomaDao.deleteToma(toma);
        });
    }

    public LiveData<List<Toma>> getTodayToma(String date){
        return mTomaDao.getTodayTomas(date);
    }

}
