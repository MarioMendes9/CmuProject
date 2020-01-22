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

    public String days;

    public String alturas;



    public Medicamento(String name, long quantity, String days,String alturas) {
        this.name = name;
        this.quantity = quantity;
        this.days= days;
        this.alturas=alturas;
    }

}
