package com.example.cmuproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class jogo_memoria extends AppCompatActivity {
    TextView palavras;
    EditText value;
    Button resposta;
    TextView respostasCorretas;
    int a=0;
    int count=0;

    String listaPalavras[] = {"maca","carrocel", "pera", "laranja", "limao", "caracol", "papagaio", "carrocel", "azul", "eu", "elefante"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_memoria);
        palavras=findViewById(R.id.palavra);
        value=findViewById(R.id.resposta);
        resposta= findViewById(R.id.button8);

        respostasCorretas=findViewById(R.id.respostasCorretas);
        Random random = new Random();
        int a = random.nextInt(11);
        palavras.setText(listaPalavras[a]);

        resposta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String s1 = value.getText().toString();
                String s2 = palavras.getText().toString();
                boolean equals = s1.equals(s2);
                if(equals == true){
                    count++;
                    respostasCorretas.setText("Tem " +count+ " respostas corretas");
                    int a = random.nextInt(11);
                    palavras.setText(listaPalavras[a]);
                } else {
                    Intent intent = new Intent(getApplicationContext(), jogo_memoria2.class);
                    System.out.println(count);
                    intent.putExtra("count", count);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        count=0;
        Random random = new Random();
        int a = random.nextInt(5);
        palavras.setText(listaPalavras[a]);
    }


}



