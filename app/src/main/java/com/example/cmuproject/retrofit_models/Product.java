package com.example.cmuproject.retrofit_models;

public class Product {
    private Nutriments nutriments;
    private Nutrient_Levels nutrient_levels;
    private String product_name;

    public Product(Nutriments nutriments, Nutrient_Levels nutrient_levels, String product_name) {
        this.nutriments = nutriments;
        this.nutrient_levels = nutrient_levels;
        this.product_name = product_name;
    }


    public Nutriments getNutriments() {
        return nutriments;
    }

    public Nutrient_Levels getNutrient_levels() {
        return nutrient_levels;
    }

    public String getProduct_name() {
        return product_name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "nutriments=" + nutriments.toString() +
                ", nutrient_levels=" + nutrient_levels.toString() +
                ", generic_name='" + product_name + '\'' +
                '}';
    }
}
