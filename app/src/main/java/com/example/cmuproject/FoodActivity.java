package com.example.cmuproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.cmuproject.retrofit_models.FoodDetails;
import com.example.cmuproject.services.OpenFoodApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodActivity extends AppCompatActivity {

    private Button btnSearch;
    private EditText inputBarcode;
    private TextView sugarTextView;
    private TextView sodiumTextView;
    private Button sugarButton;
    private Button sodiumButton;
    private TextView nameProduct;
    private TextView fatTextView;
    private TextView saturatedFatTextView;
    private Button fatButton;
    private Button saturated_fatButton;
    private Toolbar myToolbar;
    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.ThemeLight);
        mSettings= getSharedPreferences("themeMode", MODE_PRIVATE);
        String s = mSettings.getString("mode","");
        if(s.equals("light")){
            setTheme(R.style.ThemeLight);
        } else if(s.equals("dark")){
            setTheme(R.style.ThemeDark);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        btnSearch = findViewById(R.id.searchBC);
        inputBarcode = findViewById(R.id.barcode);
        sugarTextView = findViewById(R.id.sugar);
        sodiumTextView = findViewById(R.id.sodium);
        sugarButton = findViewById(R.id.sugarButton);
        sodiumButton = findViewById(R.id.sodiumButton);
        nameProduct = findViewById(R.id.nameTextView);
        fatTextView = findViewById(R.id.fat);
        saturatedFatTextView = findViewById(R.id.saturated_fat);
        fatButton = findViewById(R.id.fatButton);
        saturated_fatButton = findViewById(R.id.saturated_fatButton);



        myToolbar = findViewById(R.id.toolbar);
        myToolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.setting));
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long barcode = Long.parseLong(inputBarcode.getText().toString());
                getApiFood().getDetails(barcode)
                        .enqueue(new Callback<FoodDetails>() {
                            @Override
                            public void onResponse(Call<FoodDetails> call, Response<FoodDetails> response) {
                                System.out.println(call.request());
                                nameProduct.setText(response.body().getProduct().getProduct_name());
                                sugarTextView.setText(response.body().getProduct().getNutriments().getSugars()+
                                        response.body().getProduct().getNutriments().getSugars_unit() + " Sugars in " +
                                        response.body().getProduct().getNutrient_levels().getSugars() + " quantity");
                                sodiumTextView.setText(response.body().getProduct().getNutriments().getSodium()+
                                        response.body().getProduct().getNutriments().getSalt_unit() + " Salt in " +
                                        response.body().getProduct().getNutrient_levels().getSalt() + " quantity");
                                fatTextView.setText(response.body().getProduct().getNutriments().getFat()+
                                        response.body().getProduct().getNutriments().getFat_unit() + " Fat in " +
                                        response.body().getProduct().getNutrient_levels().getFat() + " quantity");
                                saturatedFatTextView.setText(response.body().getProduct().getNutriments().getSaturated_fat()+
                                        response.body().getProduct().getNutriments().getSaturated_fat_unit() + " Saturated Fat in " +
                                        response.body().getProduct().getNutrient_levels().getSaturated_fat() + " quantity");

                                if(response.body().getProduct().getNutrient_levels().getSugars().equals("low")){
                                    sugarButton.setBackgroundResource(R.drawable.buttonshapelow);
                                } else if(response.body().getProduct().getNutrient_levels().getSugars().equals("high")){
                                    sugarButton.setBackgroundResource(R.drawable.buttonshapehigh);
                                    Toast.makeText(getApplicationContext(), "To much SUGAR!", Toast.LENGTH_LONG).show();
                                    //new AlertDialog.Builder(getApplicationContext()).setTitle("Too much Sugar!").setMessage("This product have too much sugar!").setNeutralButton("Close", null).show();
                                } else {
                                    sugarButton.setBackgroundResource(R.drawable.buttonshapemoderate);
                                }

                                if(response.body().getProduct().getNutrient_levels().getSalt().equals("low")){
                                    sodiumButton.setBackgroundResource(R.drawable.buttonshapelow);
                                } else if(response.body().getProduct().getNutrient_levels().getSalt().equals("high")){
                                    sodiumButton.setBackgroundResource(R.drawable.buttonshapehigh);
                                    Toast.makeText(getApplicationContext(), "To much SALT!", Toast.LENGTH_LONG).show();
                                    //new AlertDialog.Builder(getApplicationContext()).setTitle("Too much Salt!").setMessage("This product have too much salt!").setNeutralButton("Close", null).show();
                                } else {
                                    sodiumButton.setBackgroundResource(R.drawable.buttonshapemoderate);
                                }

                                if(response.body().getProduct().getNutrient_levels().getFat().equals("low")){
                                    fatButton.setBackgroundResource(R.drawable.buttonshapelow);
                                } else if(response.body().getProduct().getNutrient_levels().getFat().equals("high")){
                                    fatButton.setBackgroundResource(R.drawable.buttonshapehigh);
                                    Toast.makeText(getApplicationContext(), "To much FAT!", Toast.LENGTH_LONG).show();
                                   //new AlertDialog.Builder(getApplicationContext()).setTitle("Too much Fat!").setMessage("This product have too much fat!").setNeutralButton("Close", null).show();
                                } else {
                                    fatButton.setBackgroundResource(R.drawable.buttonshapemoderate);
                                }

                                if(response.body().getProduct().getNutrient_levels().getSaturated_fat().equals("low")){
                                    saturated_fatButton.setBackgroundResource(R.drawable.buttonshapelow);
                                } else if(response.body().getProduct().getNutrient_levels().getSaturated_fat().equals("high")){
                                    saturated_fatButton.setBackgroundResource(R.drawable.buttonshapehigh);
                                    Toast.makeText(getApplicationContext(), "To much SATURATED FAT!", Toast.LENGTH_LONG).show();
                                    //new AlertDialog.Builder(getApplicationContext()).setTitle("Too much Saturated Fat!").setMessage("This product have too much saturated fat!").setNeutralButton("Close", null).show();
                                } else {
                                    saturated_fatButton.setBackgroundResource(R.drawable.buttonshapemoderate);
                                }

                            }

                            @Override
                            public void onFailure(Call<FoodDetails> call, Throwable t) {
                                System.out.println("ESTA MAU");
                                System.out.println(call.request());
                            }
                        });
            }
        });




    }

    private Retrofit getRetrofitFood() {
        return new Retrofit.Builder()
                .baseUrl("https://world.openfoodfacts.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OpenFoodApi getApiFood() {
        return getRetrofitFood().create(OpenFoodApi.class);
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
