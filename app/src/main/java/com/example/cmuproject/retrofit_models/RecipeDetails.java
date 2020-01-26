package com.example.cmuproject.retrofit_models;

public class RecipeDetails {
    private String title;
    private int readyInMinutes;
    private int servings;
    private String image;
    private boolean vegetarian;
    private boolean vegan;
    private boolean glutenFree;
    private boolean dairyFree;
    private boolean veryHealthy;
    private boolean cheap;
    private boolean veryPopular;
    private float healthScore;

    private String spoonacularSourceUrl;

    public RecipeDetails(String title, int readyInMinutes, int servings, String image, boolean vegetarian, boolean vegan, boolean glutenFree, boolean dairyFree, boolean veryHealthy, boolean cheap, boolean veryPopular, float healthScore, String spoonacularSourceUrl) {
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.image = image;
        this.vegetarian = vegetarian;
        this.vegan = vegan;
        this.glutenFree = glutenFree;
        this.dairyFree = dairyFree;
        this.veryHealthy = veryHealthy;
        this.cheap = cheap;
        this.veryPopular = veryPopular;
        this.healthScore = healthScore;
        this.spoonacularSourceUrl = spoonacularSourceUrl;
    }

    public String getTitle() {
        return title;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public boolean isVegan() {
        return vegan;
    }

    public boolean isGlutenFree() {
        return glutenFree;
    }

    public boolean isDairyFree() {
        return dairyFree;
    }

    public boolean isVeryHealthy() {
        return veryHealthy;
    }

    public boolean isCheap() {
        return cheap;
    }

    public boolean isVeryPopular() {
        return veryPopular;
    }



    public float getHealthScore() {
        return healthScore;
    }

    public String getSpoonacularSourceUrl() {
        return spoonacularSourceUrl;
    }

    @Override
    public String toString() {
        return "RecipeDetails{" +
                "title='" + title + '\'' +
                ", readyInMinutes=" + readyInMinutes +
                ", servings=" + servings +
                ", image='" + image + '\'' +
                ", vegetarian=" + vegetarian +
                ", vegan=" + vegan +
                ", glutenFree=" + glutenFree +
                ", dairyFree=" + dairyFree +
                ", veryHealthy=" + veryHealthy +
                ", cheap=" + cheap +
                ", veryPopular=" + veryPopular +
                ", healthScore=" + healthScore +
                ", spoonacularSourceUrl='" + spoonacularSourceUrl + '\'' +
                '}';
    }
}
