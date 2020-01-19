package com.example.cmuproject.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MedicamentoDao {

    @Insert
    public void insertMedicamento(Medicamento ... medic);

    @Delete
    public void deleteMedicamento(Medicamento ... medic);

    @Query("SELECT * FROM Medicamento")
    public LiveData<List<Medicamento>> loadAllMedicamentos();

    @Query("SELECT COUNT(*) FROM Medicamento")
    int getCount();

    //Medicamentos abaixo que apenas dao para dois dias


}
