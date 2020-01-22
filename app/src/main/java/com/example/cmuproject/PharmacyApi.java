package com.example.cmuproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PharmacyApi {

    @GET("search?format=json")
    Call<List<FarmaciasPerto>> getPharm(@Query(value = "q", encoded = true) String city);



}
