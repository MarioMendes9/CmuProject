package com.example.cmuproject.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cmuproject.model.Medicamento;
import com.example.cmuproject.model.MedicamentoDao;
import com.example.cmuproject.model.Toma;
import com.example.cmuproject.model.TomaDao;
import com.example.cmuproject.model.UserLocation;
import com.example.cmuproject.model.UserLocationDao;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Medicamento.class, Toma.class, UserLocation.class}, version = 4,exportSchema = false)
public abstract class MedicamentosDB extends RoomDatabase {

    private static final int NUMBER_OF_THREADS=4;
    private static volatile MedicamentosDB INSTANCE;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    //
    public static MedicamentosDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MedicamentosDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MedicamentosDB.class, "medicamentos_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    public abstract MedicamentoDao getMedicamentosDao();

    public abstract TomaDao getTomasDao();

    public abstract UserLocationDao getUserLocationDao();
}