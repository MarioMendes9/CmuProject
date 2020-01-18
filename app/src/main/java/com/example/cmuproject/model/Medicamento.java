package com.example.cmuproject.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Medicamento {

    @PrimaryKey
    public String name;

    public long quantity;

    public float periodo;

    public Medicamento(String name, long quantity, float periodo) {
        this.name = name;
        this.quantity = quantity;
        this.periodo = periodo;
    }

    @Override
    public String toString() {
        return "Medicamento{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", periodo=" + periodo +
                '}';
    }
}
