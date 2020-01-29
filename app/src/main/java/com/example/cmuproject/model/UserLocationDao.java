package com.example.cmuproject.model;

import android.location.Location;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserLocationDao {

    @Insert
    public void insertLocation(UserLocation... userLocation);

    @Query("SELECT * FROM UserLocation")
    public LiveData<List<UserLocation>> loadUserLocations();

    @Query("SELECT * FROM UserLocation WHERE date=:date ORDER BY id")
    public LiveData<List<UserLocation>> getTodayLocations(String date);


    @Query("SELECT COUNT(*) FROM UserLocation")
    public int getCountLocations();

    @Query("SELECT * FROM UserLocation WHERE date=:date ORDER BY id")
    public List<UserLocation> getLastLocation(String date);
}
