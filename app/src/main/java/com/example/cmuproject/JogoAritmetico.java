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
    String listaOperadores[] = {
            "+",
            "-",
            "*"};
    String op;
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
/*
        Random random = new Random();
        int a = random.nextInt(3);
        String op= listaOperadores[a];
        valor1=(int)(Math.random()*10);
        valor2=(int)(Math.random()*10);
        equacao.setText(valor1+ op + valor2 + "=");

*/
equacao();

        validar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(op=="+"){
                    sum=(valor1+valor2);
                    if(Integer.parseInt(resposta.getText().toString())==sum){
                        cont++;
                        respostasCorretas.setText("Tem " +cont+ "respostas corretas");
                    }else{
                        errado();
                    }
                }else if(op=="-"){
                    sub=(valor1-valor2);
                    if(Integer.parseInt(resposta.getText().toString())==sub){
                        cont++;
                        respostasCorretas.setText("Tem " +cont+ "respostas corretas");
                    }else{
                        errado();
                    }
                }else if(op=="*"){
                    mult=(valor1*valor2);
                    if(Integer.parseInt(resposta.getText().toString())==mult){
                        cont++;
                        respostasCorretas.setText("Tem " +cont+ "respostas corretas");
                    }else{
                        errado();
                    }
                }
                    equacao();
            }
        });


    }
    @Override
    protected void onResume() {
        super.onResume();
        Random random = new Random();
        cont=0;
        equacao();
    }

    protected void equacao(){
        Random random = new Random();
        int a = random.nextInt(listaOperadores.length);
        op= listaOperadores[a];
        System.out.println("OPERADOR::::: " +op);
        valor1=(int)(Math.random()*10);
        valor2=(int)(Math.random()*10);
        equacao.setText(valor1 + listaOperadores[a] + valor2 + "=");
        //return op;
    }

    protected void errado(){
        Intent intent = new Intent(getApplicationContext(), JogoAritmeticoResultado.class);
        System.out.println(cont);
        intent.putExtra("cont", cont);
        startActivity(intent);
    }


}
