package com.example.cmuproject.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MedicamentoDao {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    public void insertMedicamento(Medicamento ... medic);

    @Delete
    public void deleteMedicamento(Medicamento ... medic);

    @Query("SELECT * FROM Medicamento")
    public LiveData<List<Medicamento>> loadAllMedicamentos();

    @Query("SELECT COUNT(*) FROM Medicamento")
    public int getCount();

    @Query("UPDATE Medicamento SET quantity=quantity+:tempQuant,days=:tempDays,alturas=:tempAlturas WHERE name=:tempName")
    public void updateMedic(String tempName,int tempQuant,String tempDays,String tempAlturas);

    @Query("UPDATE Medicamento SET quantity=quantity-:qtd WHERE name=:name")
    public void removeQuantidade(String name,int qtd);


    @Query("SELECT * FROM Medicamento")
    public List<Medicamento> loadAllMedicamentos2();
}
