package com.example.cmuproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpoonacularApi {

    @GET("recipes/search")
    Call<Recipes> searchRecipes(@Query("diet") String diet,
                                     @Query("apiKey") String key);
}
