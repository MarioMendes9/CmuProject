package com.example.cmuproject;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import com.example.cmuproject.retrofit_models.FarmaciasPerto;
import com.example.cmuproject.retrofit_models.RegionDetails;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FarmaciaMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int REQUEST_FINE_LOCATION = 100;
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmacia_maps);
        getLastLocation();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            getApiStreet().getTown(location.getLatitude(), location.getLongitude())
                                    .enqueue(new Callback<RegionDetails>() {
                                        @Override
                                        public void onResponse(Call<RegionDetails> call, Response<RegionDetails> response) {
                                            String regi = response.body().getAddress().getCounty();
                                            if (regi.split(" ").length > 0) {
                                                regi.replace(" ", "-");
                                            }
                                            callFarmacias(regi);
                                        }

                                        @Override
                                        public void onFailure(Call<RegionDetails> call, Throwable t) {
                                            System.out.println(t.fillInStackTrace());
                                        }
                                    });

                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("FAILED");
                    }
                });


    }

    private void callFarmacias(String regiao) {
        getApiPharmacy().getPharm(regiao+"+pharmacy")
                .enqueue(new Callback<List<FarmaciasPerto>>() {
                    @Override
                    public void onResponse(Call<List<FarmaciasPerto>> call, Response<List<FarmaciasPerto>> response) {
                        System.out.println(call.request());
                        for (int i = 0; i < response.body().size(); i++) {
                            double lat = Double.parseDouble(response.body().get(i).getLat());
                            double lon = Double.parseDouble(response.body().get(i).getLon());
                            LatLng location = new LatLng(lat, lon);
                            addMarker(location, response.body().get(i).getDisplay_name());
                            zoomToLocation(location);
                        }

                    }

                    @Override
                    public void onFailure(Call<List<FarmaciasPerto>> call, Throwable t) {
                        System.out.println(call.request());
                        System.out.println(t.fillInStackTrace());
                    }
                });
    }

    private void zoomToLocation(LatLng location){
        CameraUpdate cameraUpdate= CameraUpdateFactory.newLatLngZoom(location, 12);
        mMap.animateCamera(cameraUpdate);
    }

    private void addMarker(LatLng latlng, String title) {
        String[] temp = title.split(",");
        title = temp[0];
        Marker mymarker = mMap.addMarker(new MarkerOptions().position(latlng).title(title));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
            return;
        }
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINE_LOCATION);
    }

    private Retrofit getRetrofitStreet() {
        return new Retrofit.Builder()
                .baseUrl("https://nominatim.openstreetmap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OpenStreetService getApiStreet() {
        return getRetrofitStreet().create(OpenStreetService.class);
    }

    private Retrofit getRetrofitPharmacy() {
        return new Retrofit.Builder()
                .baseUrl("https://nominatim.openstreetmap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private PharmacyApi getApiPharmacy() {
        return getRetrofitPharmacy().create(PharmacyApi.class);
    }

}
