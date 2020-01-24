package com.example.cmuproject.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(foreignKeys = @ForeignKey(entity = Medicamento.class,parentColumns = "name",childColumns = "medicamentoName"))
public class Toma {

    @PrimaryKey(autoGenerate = true)
    public int idToma;

    public String medicamentoName;

    public int quantidade;

    public String date;

    public String hora;

    public String local;

    public Toma(String medicamentoName, int quantidade, String date, String hora, String local) {
        this.medicamentoName = medicamentoName;
        this.quantidade = quantidade;
        this.date = date;
        this.hora = hora;
        this.local = local;
    }

    @Override
    public String toString() {
        return "Toma{" +
                "idToma=" + idToma +
                ", medicamentoName='" + medicamentoName + '\'' +
                ", quantidade=" + quantidade +
                ", date='" + date + '\'' +
                ", hora='" + hora + '\'' +
                ", local='" + local + '\'' +
                '}';
    }
}
