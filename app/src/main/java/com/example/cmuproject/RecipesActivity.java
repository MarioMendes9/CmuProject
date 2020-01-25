package com.example.cmuproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipesActivity extends AppCompatActivity {

    private Button btnSearch;
    private EditText inputValue;
    private RecyclerView mRecyclerView;
    private RecipeAdapter rAdapter;
    private final String API_KEY = "1bee02ccb720455eb04a6541f40887be";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipies);

        btnSearch = findViewById(R.id.search_recipe);
        inputValue = findViewById(R.id.editText_recipe);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getApiMeal().searchRecipes("vegetarian", API_KEY)
                        .enqueue(new Callback<Recipes>() {
                            @Override
                            public void onResponse(Call<Recipes> call, Response<Recipes> response) {
                                System.out.println("ESTA FIXE");
                                System.out.println(call.request());
                                System.out.println(response.body().toString());
                                rAdapter = new RecipeAdapter(getApplicationContext(), response.body());
                                mRecyclerView = findViewById(R.id.mRecyclerView);
                                mRecyclerView.setAdapter(rAdapter);
                                mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                            }

                            @Override
                            public void onFailure(Call<Recipes> call, Throwable t) {
                                System.out.println("ESTA MAU");
                                System.out.println(call.request());
                                System.out.println(t.toString());
                            }
                        });
            }
        });

    }

    private Retrofit getRetrofitMeal() {
        return new Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private SpoonacularApi getApiMeal() {
        return getRetrofitMeal().create(SpoonacularApi.class);
    }
}
