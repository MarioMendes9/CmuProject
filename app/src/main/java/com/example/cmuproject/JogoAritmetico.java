package com.example.cmuproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class JogoAritmetico extends AppCompatActivity {

    TextView equacao;
    TextView titulo;
    TextView resposta;
    TextView respostasCorretas;
    Button validar;
    String listaOperadores[] = {"+","-", "*", "/"};
    int valor1=0;
    int valor2=0;
    int sum=0;
    int mult=0;
    int div=0;
    int sub=0;
    int cont=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_aritmetico);
        respostasCorretas=findViewById(R.id.respostasCorretas);
        equacao=findViewById(R.id.equacao);
        titulo=findViewById(R.id.titulo);
        resposta=findViewById(R.id.resposta);
        validar=findViewById(R.id.validar);

        Random random = new Random();
        int a = random.nextInt(4);
        valor1=(int)(Math.random()*10);
        valor2=(int)(Math.random()*10);
        equacao.setText(valor1 + listaOperadores[a] + valor2 + "=");


        validar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (listaOperadores[a] == "+") {
                    sum = valor1 + valor2;
                    int s1 = Integer.parseInt(resposta.getText().toString());
                    if (s1 == sum) {
                        cont++;
                        respostasCorretas.setText("Tem " + cont + " respostas corretas");
                        int a = random.nextInt(4);
                        valor1=(int)(Math.random()*10);
                        valor2=(int)(Math.random()*10);
                        equacao.setText(valor1 + listaOperadores[a] + valor2 + "=");
                    }
                } else if (listaOperadores[a] == "-") {
                    sub = valor1 - valor2;
                    int s2 = Integer.parseInt(resposta.getText().toString());
                    if (s2 == sub) {
                        cont++;
                        respostasCorretas.setText("Tem " + cont + " respostas corretas");
                        int a = random.nextInt(4);
                        valor1=(int)(Math.random()*10);
                        valor2=(int)(Math.random()*10);
                        equacao.setText(valor1 + listaOperadores[a] + valor2 + "=");
                    }
                } else if (listaOperadores[a] == "*") {
                    mult = valor1 * valor2;
                    int s3 = Integer.parseInt(resposta.getText().toString());
                    if (s3 == mult) {
                        cont++;
                        respostasCorretas.setText("Tem " + cont + " respostas corretas");
                        int a = random.nextInt(4);
                        valor1=(int)(Math.random()*10);
                        valor2=(int)(Math.random()*10);
                        equacao.setText(valor1 + listaOperadores[a] + valor2 + "=");
                    }
                } else if (listaOperadores[a] == "/") {
                    div = valor1 / valor2;
                    int s4 = Integer.parseInt(resposta.getText().toString());
                    if (s4 == div) {
                        cont++;
                        respostasCorretas.setText("Tem " + cont + " respostas corretas");
                        int a = random.nextInt(4);
                        valor1=(int)(Math.random()*10);
                        valor2=(int)(Math.random()*10);
                        equacao.setText(valor1 + listaOperadores[a] + valor2 + "=");
                    }
                }
            }

        });


    }


}
