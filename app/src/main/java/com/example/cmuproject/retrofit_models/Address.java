package com.example.cmuproject.retrofit_models;

public class Address {
    private String hamlet;
    private String village;
    private String county;
    private String state_district;
    private String state;
    private String country;
    private String country_code;

    public Address(String hamlet, String village, String county, String state_district, String state, String country, String country_code) {
        this.hamlet = hamlet;
        this.village = village;
        this.county = county;
        this.state_district = state_district;
        this.state = state;
        this.country = country;
        this.country_code = country_code;
    }

    public String getHamlet() {
        return hamlet;
    }

    public String getVillage() {
        return village;
    }

    public String getCounty() {
        return county;
    }

    public String getState_district() {
        return state_district;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getCountry_code() {
        return country_code;
    }

    @Override
    public String toString() {
        return "Address{" +
                "hamlet='" + hamlet + '\'' +
                ", village='" + village + '\'' +
                ", county='" + county + '\'' +
                ", state_district='" + state_district + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", country_code='" + country_code + '\'' +
                '}';
    }
}
