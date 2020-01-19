package com.example.cmuproject.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Medicamento {

    @NonNull
    @PrimaryKey
    public String name;

    public long quantity;

    public String timeStamp;

    public Medicamento(String name, long quantity, String timeStamp) {
        this.name = name;
        this.quantity = quantity;
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Medicamento{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", periodo=" + timeStamp +
                '}';
    }
}
