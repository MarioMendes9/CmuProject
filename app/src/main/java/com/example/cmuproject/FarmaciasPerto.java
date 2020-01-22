package com.example.cmuproject;

public class FarmaciasPerto {
    private String lat;
    private String lon;
    private String display_name;

    public FarmaciasPerto(String lat, String lon, String display_name) {
        this.lat = lat;
        this.lon = lon;
        this.display_name = display_name;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getDisplay_name() {
        return display_name;
    }

    @Override
    public String toString() {
        return "FarmaciasPerto{" +
                "lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", display_name='" + display_name + '\'' +
                '}';
    }
}
