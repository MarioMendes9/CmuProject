package com.example.cmuproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class JogoMemoria extends AppCompatActivity {

    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    ImageView img5;
    ImageView img6;
    ImageView img7;
    ImageView img8;
    ImageView img9;
    ImageView img10;
    ImageView img11;
    ImageView img12;



    Integer[] numeros={101,102,103,104,105,106,201,202,203,204,205,206};
    int carta101,carta102,carta103,carta104,carta105,carta106,carta201,carta202,carta203,carta204,carta205,carta206;
    int a;

    int firstNumber, secondNumber;
    int clickFirst, clickSecond;
    int cardNumber=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_memoria);

        img1=findViewById(R.id.img11);
        img2=findViewById(R.id.img12);
        img3=findViewById(R.id.img13);
        img4=findViewById(R.id.img14);
        img5=findViewById(R.id.img21);
        img6=findViewById(R.id.img22);
        img7=findViewById(R.id.img23);
        img8=findViewById(R.id.img24);
        img9=findViewById(R.id.img31);
        img10=findViewById(R.id.img32);
        img11=findViewById(R.id.img33);
        img12=findViewById(R.id.img34);

        img1.setTag("0");
        img2.setTag("1");
        img3.setTag("2");
        img4.setTag("3");
        img5.setTag("4");
        img6.setTag("5");
        img7.setTag("6");
        img8.setTag("7");
        img9.setTag("8");
        img10.setTag("9");
        img11.setTag("10");
        img12.setTag("11");


        frontOfCards();
        Collections.shuffle(Arrays.asList(numeros));
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt((String) v.getTag());
                System.out.println("THE CARD::::: " + theCard);
                mostrarCartas(img1,theCard);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt((String) v.getTag());
                System.out.println("THE CARD::::: " + theCard);
                mostrarCartas(img2,theCard);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt((String) v.getTag());
                System.out.println("THE CARD::::: " + theCard);
                mostrarCartas(img3,theCard);
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt((String) v.getTag());
                System.out.println("THE CARD::::: " + theCard);
                mostrarCartas(img4,theCard);
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt((String) v.getTag());
                System.out.println("THE CARD::::: " + theCard);
                mostrarCartas(img5,theCard);
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt((String) v.getTag());
                System.out.println("THE CARD::::: " + theCard);
                mostrarCartas(img6,theCard);
            }
        });
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt((String) v.getTag());
                System.out.println("THE CARD::::: " + theCard);
                mostrarCartas(img7,theCard);
            }
        });
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt((String) v.getTag());
                System.out.println("THE CARD::::: " + theCard);
                mostrarCartas(img8,theCard);
            }
        });
        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt((String) v.getTag());
                System.out.println("THE CARD::::: " + theCard);
                mostrarCartas(img9,theCard);
            }
        });
        img10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt((String) v.getTag());
                System.out.println("THE CARD::::: " + theCard);
                mostrarCartas(img10,theCard);
            }
        });
        img11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt((String) v.getTag());
                System.out.println("THE CARD::::: " + theCard);
                mostrarCartas(img11,theCard);
            }
        });
        img12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt((String) v.getTag());
                System.out.println("THE CARD::::: " + theCard);
                mostrarCartas(img12,theCard);
            }
        });



    }

    private void frontOfCards(){
        carta101=R.drawable.img101;
        carta102=R.drawable.img102;
        carta103=R.drawable.img103;
        carta104=R.drawable.img104;
        carta105=R.drawable.img105;
        carta106=R.drawable.img106;
        carta201=R.drawable.img201;
        carta202=R.drawable.img202;
        carta203=R.drawable.img203;
        carta204=R.drawable.img204;
        carta205=R.drawable.img205;
        carta206=R.drawable.img206;
    }

    private void mostrarCartas(ImageView iv, int card){
        //coloca a imagem correta nas cartas
        if(numeros[card]==101){
            iv.setImageResource(carta101);
        }else if(numeros[card]==102) {
            iv.setImageResource(carta102);
        }else if(numeros[card]==103) {
            iv.setImageResource(carta103);
        }else if(numeros[card]==104) {
            iv.setImageResource(carta104);
        }else if(numeros[card]==105) {
            iv.setImageResource(carta105);
        }else if(numeros[card]==106) {
            iv.setImageResource(carta106);
        }else if(numeros[card]==201) {
            iv.setImageResource(carta201);
        }else if(numeros[card]==202) {
                iv.setImageResource(carta202);
        }else if(numeros[card]==203) {
            iv.setImageResource(carta203);
        }else if(numeros[card]==204) {
            iv.setImageResource(carta204);
        }else if(numeros[card]==205) {
            iv.setImageResource(carta205);
        }else if(numeros[card]==206) {
            iv.setImageResource(carta206);
        }
    }

}
