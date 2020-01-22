package com.example.cmuproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class JogoMemoria extends AppCompatActivity {

    TextView img1;
    TextView img2;
    TextView img3;
    TextView img4;
    TextView img5;
    TextView img6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_memoria);
    }
}
