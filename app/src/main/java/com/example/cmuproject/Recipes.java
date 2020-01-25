package com.example.cmuproject;

import java.util.List;

public class Recipes {
    private List<Recipe> results;

    public Recipes(List<Recipe> results) {
        
        this.results = results;
    }

    public List<Recipe> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "Recipes{" +
                "result=" + results.toString() +
                '}';
    }
}
