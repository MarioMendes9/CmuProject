package com.example.cmuproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cmuproject.Adapters.RecipeAdapter;
import com.example.cmuproject.retrofit_models.Recipes;
import com.example.cmuproject.services.SpoonacularApi;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipesActivity extends AppCompatActivity {

    private Button btnSearch;
    private RecyclerView mRecyclerView;
    private RecipeAdapter rAdapter;
    private final String API_KEY = "1bee02ccb720455eb04a6541f40887be";
    private ChipGroup chipAler;
    private ChipGroup chipDiet;
    private String dietQuery;
    private String alerQuery;
    private TextView aler;
    private TextView diet;
    private Toolbar myToolbar;
    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSettings= getSharedPreferences("themeMode", MODE_PRIVATE);
        String s = mSettings.getString("mode","");
        if(s.equals("light")){
            setTheme(R.style.ThemeLight);
        } else if(s.equals("dark")){
            setTheme(R.style.ThemeDark);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipies);

        btnSearch = findViewById(R.id.search_recipe);
        chipAler = findViewById(R.id.chipGroupIntolerancias);
        chipDiet = findViewById(R.id.chipGroupDiet);
        aler=findViewById(R.id.alergenios);
        diet = findViewById(R.id.diet);

        myToolbar = findViewById(R.id.toolbar);
        myToolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.setting));
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        aler.setText("Alergenios");

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dietQuery = "";
                alerQuery = "";


                for (int i = 0; i < chipAler.getChildCount(); i++) {
                    Chip tempChip =(Chip)chipAler.getChildAt(i);
                    if(tempChip.isChecked()){
                        String temp = tempChip.getText().toString();
                        if(temp.equals("Laticinios")){
                            alerQuery += "dairy" + ",";
                        } else if(temp.equals("Ovo")){
                            alerQuery += "egg" + ",";
                        } else if(temp.equals("Glutén")){
                            alerQuery += "gluten" + ",";
                        } else if(temp.equals("Grão")){
                            alerQuery += "grain" + ",";
                        } else if(temp.equals("Amendoim")){
                            alerQuery += "peanut" + ",";
                        } else if(temp.equals("Frutos do Mar")){
                            alerQuery += "seafood" + ",";
                        } else if(temp.equals("Sésamo")){
                            alerQuery += "sesame" + ",";
                        } else if(temp.equals("Marisco")){
                            alerQuery += "shellfish" + ",";
                        } else if(temp.equals("Soja")){
                            alerQuery += "soy" + ",";
                        } else if(temp.equals("Sulfito")){
                            alerQuery += "sulfite" + ",";
                        } else if(temp.equals("Frutos Secos")){
                            alerQuery += "tree+nut" + ",";
                        } else if(temp.equals("Trigo")){
                            alerQuery += "wheat" + ",";

                        }
                    }

                }
                if(alerQuery.endsWith(",")) {
                    alerQuery = alerQuery.substring(0,alerQuery.length() - 1);
                }
                for (int i = 0; i < chipDiet.getChildCount(); i++) {
                    Chip tempChip =(Chip)chipDiet.getChildAt(i);
                    if(tempChip.isChecked()){
                        String temp = tempChip.getText().toString();
                        if(temp.equals("Vegetariano")){
                            dietQuery += "vegetarian" + ",";
                        } else if(temp.equals("Sem Gluten")){
                            dietQuery += "glutenFree" + ",";
                        } else {
                            dietQuery += "vegan" + ",";
                        }
                    }

                }

                if(dietQuery.endsWith(",")) {
                    dietQuery = dietQuery.substring(0,dietQuery.length() - 1);
                }

                System.out.println("DIET :::::::::::::::::::::::::: " + dietQuery);
                System.out.println("ALER :::::::::::::::::::::::::: " + alerQuery);

                getApiMeal().searchRecipes(dietQuery, API_KEY, alerQuery)
                        .enqueue(new Callback<Recipes>() {
                            @Override
                            public void onResponse(Call<Recipes> call, Response<Recipes> response) {
                                System.out.println("ESTA FIXE");
                                System.out.println(call.request());
                                System.out.println(response.body().toString());
                                hideAll();
                                aler.setText("Receitas");
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

    private void hideAll(){
        btnSearch.setVisibility(View.GONE);
        diet.setVisibility(View.GONE);
        chipAler.setVisibility(View.GONE);
        chipDiet.setVisibility(View.GONE);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_def:
                Intent mIntent = new Intent(this, SettingsActivity.class);
                mIntent.putExtra("theme", mSettings.getString("mode",""));
                startActivity(mIntent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
