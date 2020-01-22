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
    TextView palavras;
    EditText value;
    Button resposta;
    TextView respostasCorretas;
    int a = 0;
    int count = 0;
    String replace;
    String palavra;

    String listaPalavras[] = {"maca", "carrocel", "pera", "laranja", "limao", "caracol", "papagaio", "azul", "eu", "elefante"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_palavras);
        palavras = findViewById(R.id.palavra);
        value = findViewById(R.id.resposta);
        resposta = findViewById(R.id.button8);

        respostasCorretas = findViewById(R.id.respostasCorretas);

        palavra();

        resposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = value.getText().toString();
                System.out.println("PALAVRA::::::  " + palavra);
                //String palavraCorreta=s2.replaceAll("_","a");
                boolean equals = s1.equals(palavra);
                if (equals == true) {
                    count++;
                    respostasCorretas.setText("Tem " + count + " respostas corretas");
                    palavra();

                } else {

                    Intent intent = new Intent(getApplicationContext(), JogoPalavrasResultado.class);
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
        Random random = new Random();
        a = random.nextInt(10);
        palavra = listaPalavras[a];
        System.out.println("PALAVRA::::: " + palavra);
        palavras.setText(listaPalavras[a].replaceAll("[ape]", "_ "));
    }

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

    private void palavra() {
        Random random = new Random();
        a = random.nextInt(10);
        palavra = listaPalavras[a];
        System.out.println("PALAVRA::::: " + palavra);
        palavras.setText(listaPalavras[a].replaceAll("[ape]", "_ "));
    }


}
