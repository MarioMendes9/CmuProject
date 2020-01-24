package com.example.cmuproject.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TomaDao {

    @Insert
    public void insertToma(Toma... toma);

    @Delete
    public void deleteToma(Toma... toma);

    @Query("SELECT * FROM Toma")
    public LiveData<List<Toma>> loadAllTomas();

    @Query("SELECT COUNT(*) FROM Toma")
    public int getCountTomas();

    @Query("SELECT * FROM Toma WHERE date=:date")
    public LiveData<List<Toma>> getTodayTomas(String date);
}
