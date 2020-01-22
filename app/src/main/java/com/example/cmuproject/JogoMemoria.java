package com.example.cmuproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;


public class JogoMemoria extends AppCompatActivity {



    //ImageView img;
    TextView palavras;
    EditText value;
    Button resposta;
    TextView respostasCorretas;
    int a=0;
    int count=0;
    String replace;

    String listaPalavras[] = {"maca","carrocel", "pera", "laranja", "limao", "caracol", "papagaio", "azul", "eu", "elefante"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_memoria);
        palavras=findViewById(R.id.palavra);
        value=findViewById(R.id.resposta);
        resposta= findViewById(R.id.button8);
        //img=findViewById(R.id.imageView2);
        //img.setImageResource(R.drawable.bacon);
        respostasCorretas=findViewById(R.id.respostasCorretas);

        Random random = new Random();
        int a = random.nextInt(10);
        String string = listaPalavras[a];
        replace= string.replaceAll("a","___");
        palavras.setText(replace);

        //runthread();

        resposta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                palavras.setVisibility(View.VISIBLE);
                String s1 = value.getText().toString();
                //String s2 = palavras.getText().toString();
                String s2=listaPalavras[a];
                boolean equals = s1.equals(s2);
                if(equals == true){
                    count++;
                    respostasCorretas.setText("Tem " +count+ " respostas corretas");
                    //int a = random.nextInt(10);
                    //palavras.setText(listaPalavras[a]);
                    //runthread();

                    int a = random.nextInt(10);
                    String string = listaPalavras[a];
                    replace= string.replaceAll("a","___");
                    palavras.setText(replace);
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

}
