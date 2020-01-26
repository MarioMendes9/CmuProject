package com.example.cmuproject.services;

import com.example.cmuproject.retrofit_models.RecipeDetails;
import com.example.cmuproject.retrofit_models.Recipes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SpoonacularApi {

    @GET("recipes/search")
    Call<Recipes> searchRecipes(@Query("diet") String diet,
                                @Query("apiKey") String key,
                                @Query("excludeIngredients") String aler);


    @GET("recipes/{id}/information")
    Call<RecipeDetails> recipeDetails(@Path("id") int id,
                                      @Query("apiKey") String key);


}
