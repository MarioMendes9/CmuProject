package com.example.cmuproject.retrofit_models;

import com.google.gson.annotations.SerializedName;

public class Nutrient_Levels {
    private String sugars;
    private String salt;
    private String fat;
    @SerializedName("saturated-fat") //porque o nome no json contem um hifen e não é permitido esse simbolo no nome de uma variavel
    private String saturated_fat;

    public Nutrient_Levels(String sugars, String salt, String fat, String saturated_fat) {
        this.sugars = sugars;
        this.salt = salt;
        this.fat = fat;
        this.saturated_fat = saturated_fat;
    }

    public String getSugars() {
        return sugars;
    }

    public String getSalt() {
        return salt;
    }

    public String getFat() {
        return fat;
    }

    public String getSaturated_fat() {
        return saturated_fat;
    }

    @Override
    public String toString() {
        return "Nutrient_Levels{" +
                "sugars='" + sugars + '\'' +
                ", salt='" + salt + '\'' +
                ", fat='" + fat + '\'' +
                ", saturated_fat=" + saturated_fat +
                '}';
    }
}
