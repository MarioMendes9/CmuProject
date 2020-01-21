package com.example.cmuproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class jogo_memoria2 extends AppCompatActivity {

    TextView resultado;
    TextView respostas;
    Button jogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_memoria2);
        resultado=findViewById(R.id.textView5);
        respostas=findViewById(R.id.Result);
        jogar=findViewById(R.id.jogarNovamente);

        int text=getIntent().getIntExtra("count", 0);
        System.out.println(text);
        resultado.setText( Integer.toString(text)+ " Respostas corretas");

        jogar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


            }
        });
    }
}
