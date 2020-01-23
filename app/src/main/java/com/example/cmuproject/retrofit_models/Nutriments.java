package com.example.cmuproject.retrofit_models;

import com.google.gson.annotations.SerializedName;

public class Nutriments {
    private float sugars;
    private float sodium;
    private String salt_unit;
    private String sugar_unit;
    private float fat;
    private String fat_unit;

    @SerializedName("saturated-fat_unit") //porque o nome no json contem um hifen e não é permitido esse simbolo no nome de uma variavel
    private String saturated_fat_unit;

    @SerializedName("saturated-fat") //porque o nome no json contem um hifen e não é permitido esse simbolo no nome de uma variavel
    private float saturated_fat;


    public Nutriments(float sugars, float sodium, String salt_unit, String sugar_unit, float fat, String fat_unit, String saturated_fat_unit, float saturated_fat) {
        this.sugars = sugars;
        this.sodium = sodium;
        this.salt_unit = salt_unit;
        this.sugar_unit = sugar_unit;
        this.fat = fat;
        this.fat_unit = fat_unit;
        this.saturated_fat_unit = saturated_fat_unit;
        this.saturated_fat = saturated_fat;
    }

    public String getSalt_unit() {
        return salt_unit;
    }

    public String getSugar_unit() {
        return sugar_unit;
    }

    public float getSugars() {
        return sugars;
    }

    public float getSodium() {
        return sodium;
    }

    public float getFat() {
        return fat;
    }

    public String getFat_unit() {
        return fat_unit;
    }

    public String getSaturated_fat_unit() {
        return saturated_fat_unit;
    }

    public float getSaturated_fat() {
        return saturated_fat;
    }

    @Override
    public String toString() {
        return "Nutriments{" +
                "sugars=" + sugars +
                ", sodium=" + sodium +
                ", salt_unit='" + salt_unit + '\'' +
                ", sugar_unit='" + sugar_unit + '\'' +
                ", fat=" + fat +
                ", fat_unit='" + fat_unit + '\'' +
                ", saturated_fat_unit='" + saturated_fat_unit + '\'' +
                ", saturated_fat=" + saturated_fat +
                '}';
    }
}
