package com.example.cmuproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class JogoAritmeticoResultado extends AppCompatActivity {

    TextView respostasCertas;
    TextView tituloResult;
    Button jogarNovamente;
    Button voltarMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_aritmetico_resultado);
        respostasCertas=findViewById(R.id.respostasCertas);
        tituloResult=findViewById(R.id.tituloResult);
        jogarNovamente=findViewById(R.id.button);
        voltarMenu=findViewById(R.id.button2);

        int text=getIntent().getIntExtra("cont", 0);
        System.out.println(text);
        respostasCertas.setText( Integer.toString(text)+ " Respostas corretas");


        jogarNovamente.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent it = new Intent(JogoAritmeticoResultado.this, JogoAritmetico.class);
                startActivity(it);
            }

        });

        voltarMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent it = new Intent(JogoAritmeticoResultado.this, MenuJogos.class);
                startActivity(it);
            }

        });
    }
}
