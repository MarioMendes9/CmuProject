package com.example.cmuproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class JogoMemoriaResultado extends AppCompatActivity {

    private Button jogarNovamente;
    private Button menuJogos;
    private TextView tempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_memoria_resultado);
        jogarNovamente=findViewById(R.id.button4);
        menuJogos=findViewById(R.id.button5);

        menuJogos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent it = new Intent(JogoMemoriaResultado.this, MenuJogos.class);
                startActivity(it);
            }
        });

        jogarNovamente.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent it = new Intent(JogoMemoriaResultado.this, JogoMemoria.class);
                startActivity(it);
            }
        });


    }
}
