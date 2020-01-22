package com.example.cmuproject;

public class Nutriments {
    private FoodDetails food;


    public Nutriments(FoodDetails food) {
        this.food = food;
    }

    public FoodDetails getFood() {

        return food;
    }

    @Override
    public String toString() {
        return "Nutriments{" +
                "food=" + food.toString() +
                '}';
    }
}
