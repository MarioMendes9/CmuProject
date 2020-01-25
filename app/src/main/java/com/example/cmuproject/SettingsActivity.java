package com.example.cmuproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {

    private Toolbar myToolbar;
    private final String lightMode = "light";
    private final String darkMode = "dark";
    private RadioGroup rg;
    private SharedPreferences mSettings;
    private RadioButton light;
    private RadioButton dark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        String s = bundle.getString("theme");
        if(s.equals("light")){
            setTheme(R.style.ThemeLight);
        } else if(s.equals("dark")){
            setTheme(R.style.ThemeDark);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        rg=findViewById(R.id.radioGroup);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSettings= getSharedPreferences("themeMode", MODE_PRIVATE);
        System.out.println(mSettings.getString("mode",""));

        final SharedPreferences.Editor mEditor = mSettings.edit();


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.lightOption:
                        mEditor.putString("mode", lightMode);
                        break;
                    case R.id.darkOption:
                        mEditor.putString("mode", darkMode);
                        break;
                }
                mEditor.commit();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }

        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
