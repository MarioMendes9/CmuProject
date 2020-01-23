package com.example.cmuproject.retrofit_models;

public class FoodDetails {
    private Product product;

    public FoodDetails(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "FoodDetails{" +
                "product=" + product.toString() +
                '}';
    }
}
