package com.example.cmuproject.services;

import com.example.cmuproject.retrofit_models.RegionDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenStreetService {
    @GET("reverse?format=json")
    Call<RegionDetails> getTown(@Query("lat") double lat,
                                @Query("lon") double lon);


}
