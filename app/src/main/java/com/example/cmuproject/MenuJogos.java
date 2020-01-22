package com.example.cmuproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuJogos extends AppCompatActivity {
 TextView jogoMemoria;
 TextView jogoAritmetico;
 TextView jogoPalavras;

 Button iniciarJogoPalavras;
 Button iniciarJogoAritmetico;
 Button iniciarJogoMemoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_jogos);

        jogoMemoria=findViewById(R.id.textJogoMemoria);
        jogoAritmetico=findViewById(R.id.textJogoAritmetico);
        jogoPalavras=findViewById(R.id.textJogoPalavras);

        iniciarJogoAritmetico=findViewById(R.id.jogoAritmetico);
        iniciarJogoPalavras=findViewById(R.id.jogoPalavras);
        iniciarJogoMemoria=findViewById(R.id.jogoMemoria);

        iniciarJogoPalavras.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MenuJogos.this, JogoMemoria.class);
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
    }
}
