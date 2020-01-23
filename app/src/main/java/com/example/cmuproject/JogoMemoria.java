package com.example.cmuproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class JogoMemoria extends AppCompatActivity {

    TextView[] img;
    Integer[] numeros;

    int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_memoria);

        img[0]=findViewById(R.id.img1);
        img[1]=findViewById(R.id.img2);
        img[2]=findViewById(R.id.img3);
        img[3]=findViewById(R.id.img4);
        img[4]=findViewById(R.id.img5);
        img[5]=findViewById(R.id.img6);

        /*
        for(int i=0; i<6; i++){
            Random random = new Random();
            a=random.nextInt(6);
            boolean contains = Arrays.asList(numeros).contains(a);
            if(contains==true){
                a=random.nextInt(6);
            }else{
                img[i].setText(a);
            }
        }
*/

    }
}
