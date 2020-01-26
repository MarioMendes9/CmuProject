package com.example.cmuproject.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserLocation {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public double lat;

    public double lon;

    public String date;

    public String hora;

    public UserLocation(double lat, double lon, String date, String hora) {
        this.lat = lat;
        this.lon = lon;
        this.date = date;
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "UserLocation{" +
                "id=" + id +
                ", lat=" + lat +
                ", lon=" + lon +
                ", date='" + date + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }
}
