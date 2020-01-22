package com.example.cmuproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class JogoPalavrasResultado extends AppCompatActivity {

    TextView resultado;
    TextView respostas;
    Button jogar;
    Button voltarMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_palavras_result);
        resultado=findViewById(R.id.textView5);
        respostas=findViewById(R.id.Result);
        jogar=findViewById(R.id.jogarNovamente);
        voltarMenu=findViewById(R.id.button3);

        int text=getIntent().getIntExtra("count", 0);
        System.out.println(text);
        resultado.setText( Integer.toString(text)+ " Respostas corretas");

        jogar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(this, MenuJogos.class);
                //startActivity(intent);
                Intent it = new Intent(JogoPalavrasResultado.this, JogoPalavras.class);
                startActivity(it);
                //setContentView(R.layout.activity_jogo_palavras);
            }

        });

        voltarMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent it = new Intent(JogoPalavrasResultado.this, MenuJogos.class);
                startActivity(it);
            }

        });
    }
}
