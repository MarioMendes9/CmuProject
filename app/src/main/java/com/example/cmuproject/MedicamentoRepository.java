package com.example.cmuproject;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.cmuproject.Database.MedicamentosDB;
import com.example.cmuproject.model.Medicamento;
import com.example.cmuproject.model.MedicamentoDao;

import java.util.List;

public class MedicamentoRepository {

    private MedicamentoDao mMedicaDao;
    private LiveData<List<Medicamento>> mMedicamentos;

    public MedicamentoRepository(Application application) {
        MedicamentosDB db=MedicamentosDB.getDatabase(application);

        this.mMedicaDao = db.getMedicamentosDao();
        this.mMedicamentos = mMedicaDao.loadAllMedicamentos();
    }

    public LiveData<List<Medicamento>> getmMedicamentos() {
        return mMedicamentos;
    }

    public void insertMedicamento(final Medicamento medi){
        MedicamentosDB.databaseWriteExecutor.execute(()->{
            mMedicaDao.insertMedicamento(medi);
        });
    }

    public void removeMedicamento(Medicamento medi){
        MedicamentosDB.databaseWriteExecutor.execute(()->{
            mMedicaDao.deleteMedicamento(medi);
        });
    }

    public void updateMedicamento(String tempName,int tempQuant,String tempDays,String tempAlturas){
        MedicamentosDB.databaseWriteExecutor.execute(()->{
                mMedicaDao.updateMedic(tempName,tempQuant,tempDays,tempAlturas);
        });
    }

}
