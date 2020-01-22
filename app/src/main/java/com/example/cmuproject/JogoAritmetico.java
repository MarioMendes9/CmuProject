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
    String listaOperadores[] = {"+","-", "*"};
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
        equacao=findViewById(R.id.num1);
        titulo=findViewById(R.id.titulo);
        resposta=findViewById(R.id.resposta);
        validar=findViewById(R.id.validar);

        Random random = new Random();
        int a = random.nextInt(3);
        String op= listaOperadores[a];
        valor1=(int)(Math.random()*10);
        valor2=(int)(Math.random()*10);
        equacao.setText(valor1+ op + valor2 + "=");
        validar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String op = listaOperadores[a];
                System.out.println("OPERADOR:::: " +op);
                //String palavraCorreta=s2.replaceAll("_","a");
                equacao();
                if (listaOperadores[a] == "-") {
                    sub = (valor1 - valor2);
                    System.out.println("SUB::::: " +sub);
                    int s1 = Integer.parseInt(resposta.getText().toString());
                    System.out.println("S1:::: " +s1);
                    if (s1==sub) {
                        cont++;
                        respostasCorretas.setText("Tem " + cont + " respostas corretas");
                        equacao();
                    }else{
                        //errado();
                        equacao();
                    }
                } else if (listaOperadores[a] == "+") {
                    sum = (valor1 + valor2);
                    System.out.println("SUm::::: " +sub);
                    int s2 = Integer.parseInt(resposta.getText().toString());
                    System.out.println("S2:::: " +s2);
                    if (s2 == sum) {
                        cont++;
                        respostasCorretas.setText("Tem " + cont + " respostas corretas");
                        equacao();
                    }else{
                       equacao();
                        // errado();
                    }
                } else if (listaOperadores[a] == "*") {
                    mult = (valor1 * valor2);
                    System.out.println("MULT:::: " +mult);
                    int s3 = Integer.parseInt(resposta.getText().toString());
                    System.out.println("S3:::: " +s3);
                    if (s3 == mult) {
                        cont++;
                        respostasCorretas.setText("Tem " + cont + " respostas corretas");
                        equacao();
                    }else{
                       // errado();
                        equacao();
                    }
                }
            }

        });


    }
    @Override
    protected void onResume() {
        super.onResume();
        Random random = new Random();
        int a = random.nextInt(3);
        valor1=(int)(Math.random()*10);
        valor2=(int)(Math.random()*10);
        String op= listaOperadores[a];
        equacao.setText(valor1 + op + valor2 + "=");
    }

    protected void equacao(){
        Random random = new Random();
        int a = random.nextInt(3);
        valor1=(int)(Math.random()*10);
        valor2=(int)(Math.random()*10);
        equacao.setText(valor1 + listaOperadores[a] + valor2 + "=");
    }

    protected void errado(){
        Intent intent = new Intent(getApplicationContext(), JogoAritmeticoResultado.class);
        System.out.println(cont);
        intent.putExtra("cont", cont);
        startActivity(intent);
    }


}
