package com.example.cmuproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuJogos extends AppCompatActivity {


    private  Button iniciarJogoPalavras;
    private Button iniciarJogoAritmetico;
    private Button iniciarJogoMemoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_jogos);


        iniciarJogoAritmetico=findViewById(R.id.jogoAritmetico);
        iniciarJogoPalavras=findViewById(R.id.jogoPalavras);
        iniciarJogoMemoria=findViewById(R.id.jogoMemoria);

        iniciarJogoPalavras.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MenuJogos.this, JogoPalavras.class);
                startActivity(it);

            }

        });

        iniciarJogoAritmetico.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MenuJogos.this, JogoAritmetico.class);
                startActivity(it);

            }

        });

        iniciarJogoMemoria.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MenuJogos.this, JogoMemoria.class);
                startActivity(it);

            }
        });
    }
}
