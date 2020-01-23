package com.example.cmuproject;

import com.example.cmuproject.retrofit_models.FoodDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OpenFoodApi {

    @GET("api/v0/product/{barcode}.json")
    Call<FoodDetails> getDetails(@Path("barcode") long barcode);



}
