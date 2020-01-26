package com.example.cmuproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class JogoPalavras extends AppCompatActivity {


    //ImageView img;
    private TextView palavras;
    private EditText value;
    private Button resposta;
    private TextView respostasCorretas;
    private int a = 0;
    private int count = 0;
    private String palavra;

    String listaPalavras[] = {
            "carro",
            "carrocel",
            "pera",
            "laranja",
            "limao",
            "caracol",
            "papagaio",
            "azul",
            "elefante",
            "policia",
            "filosofo"

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_palavras);
        palavras = findViewById(R.id.palavra);
        value = findViewById(R.id.resposta);
        resposta = findViewById(R.id.button8);

        respostasCorretas = findViewById(R.id.respostasCorretas);

        palavra();
        value.setText("");

        resposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = value.getText().toString();
                System.out.println("PALAVRA::::::  " + palavra);
                boolean equals = s1.equals(palavra);
                if (equals == true) {
                    count++;
                    respostasCorretas.setText("Tem " + count + " respostas corretas");
                    palavra();
                    value.setText("");
                } else {
                    Intent intent = new Intent(JogoPalavras.this, JogoPalavrasResultado.class);
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
        count = 0;
        palavra();
    }
/*
    private void runthread() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        palavras.setVisibility(View.INVISIBLE);
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

 */
/*
   */
    private void palavra(){
    Random random = new Random();
    a = random.nextInt(listaPalavras.length);
    palavra = listaPalavras[a];
    String palavraTemp= listaPalavras[a];
    char[]characters = palavraTemp.toCharArray();
    int rand1 = (int)(Math.random()*palavraTemp.length());
    characters[rand1]='*';
    new String(characters);
    int rand2 = (int)(Math.random()*palavraTemp.length());
    characters[rand2]='*';
    palavras.setText(new String(characters));
}

}
