package com.example.cmuproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RegrasJogo extends AppCompatActivity {
    TextView titulo;
    TextView regras;
    TextView tituloRegras;
    Button iniciarJogo;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regras_jogo_memoria);
        img=findViewById(R.id.imageView);
        img.setImageResource(R.drawable.bacon);
        titulo= findViewById(R.id.textView2);
        regras= findViewById(R.id.regras);
        tituloRegras= findViewById(R.id.textView6);
        iniciarJogo= findViewById(R.id.button);

        iniciarJogo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent it = new Intent(RegrasJogo.this, JogoMemoria.class);
                startActivity(it);

            }

        });
    }
}



