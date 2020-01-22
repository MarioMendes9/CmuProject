package com.example.cmuproject;

public class FoodDetails {
    private float sugars;
    private float sodium;

    public FoodDetails(float sugars, float sodium) {
        this.sugars = sugars;
        this.sodium = sodium;
    }

    public float getSugars() {
        return sugars;
    }

    public float getSodium() {
        return sodium;
    }
}
