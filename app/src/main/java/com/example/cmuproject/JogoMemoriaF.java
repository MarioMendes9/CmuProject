package com.example.cmuproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.Collections;

public class JogoMemoriaF extends Fragment {

    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;
    private ImageView img6;
    private ImageView img7;
    private ImageView img8;
    private ImageView img9;
    private ImageView img10;
    private ImageView img11;
    private ImageView img12;

    private Integer[] numeros={101,102,103,104,105,106,201,202,203,204,205,206};
    private int carta101,carta102,carta103,carta104,carta105,carta106,carta201,carta202,carta203,carta204,carta205,carta206;


    private int primeiraCarta, segundaCarta;
    private int primeiraCartaSelecionada, segundaCartaSelecionada;
    private int cardNumber=1;


    private OnFragmentInteractionListener mListener;

    public JogoMemoriaF() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View  view = inflater.inflate(R.layout.fragment_jogo_memoria, container, false);

        img1=view.findViewById(R.id.img11);
        img2=view.findViewById(R.id.img12);
        img3=view.findViewById(R.id.img13);
        img4=view.findViewById(R.id.img14);
        img5=view.findViewById(R.id.img21);
        img6=view.findViewById(R.id.img22);
        img7=view.findViewById(R.id.img23);
        img8=view.findViewById(R.id.img24);
        img9=view.findViewById(R.id.img31);
        img10=view.findViewById(R.id.img32);
        img11=view.findViewById(R.id.img33);
        img12=view.findViewById(R.id.img34);

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
                int theCard=Integer.parseInt(v.getTag().toString());
                System.out.println("THE CARD1::::: " + theCard);
                mostrarCartas(img1,theCard);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt(v.getTag().toString());
                System.out.println("THE CARD2::::: " + theCard);
                mostrarCartas(img2,theCard);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt(v.getTag().toString());
                System.out.println("THE CARD3::::: " + theCard);
                mostrarCartas(img3,theCard);
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt(v.getTag().toString());
                System.out.println("THE CARD4::::: " + theCard);
                mostrarCartas(img4,theCard);
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt(v.getTag().toString());
                System.out.println("THE CARD5::::: " + theCard);
                mostrarCartas(img5,theCard);
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt(v.getTag().toString());
                System.out.println("THE CARD6::::: " + theCard);
                mostrarCartas(img6,theCard);
            }
        });
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt(v.getTag().toString());
                System.out.println("THE CARD7::::: " + theCard);
                mostrarCartas(img7,theCard);
            }
        });
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt( v.getTag().toString());
                System.out.println("THE CARD8::::: " + theCard);
                mostrarCartas(img8,theCard);
            }
        });
        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt(v.getTag().toString());
                System.out.println("THE CARD9::::: " + theCard);
                mostrarCartas(img9,theCard);
            }
        });
        img10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt(v.getTag().toString());
                System.out.println("THE CARD10::::: " + theCard);
                mostrarCartas(img10,theCard);
            }
        });
        img11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt(v.getTag().toString());
                System.out.println("THE CARD11::::: " + theCard);
                mostrarCartas(img11,theCard);
            }
        });
        img12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt((String) v.getTag());
                System.out.println("THE CARD12::::: " + theCard);
                mostrarCartas(img12,theCard);

            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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

    private void mostrarCartas(ImageView image, int card) {
        //coloca a imagem correta nas cartas
        if (numeros[card] == 101) {
            image.setImageResource(carta101);
            image.setClickable(false);
            System.out.println(numeros[card] + " entrou");
        } else if (numeros[card] == 102) {
            image.setImageResource(carta102);
            image.setClickable(false);
            System.out.println(numeros[card] + " entrou");
        } else if (numeros[card] == 103) {
            image.setImageResource(carta103);
            image.setClickable(false);
            System.out.println(numeros[card] + " entrou");
        } else if (numeros[card] == 104) {
            image.setImageResource(carta104);
            image.setClickable(false);
            System.out.println(numeros[card] + " entrou");
        } else if (numeros[card] == 105) {
            image.setImageResource(carta105);
            image.setClickable(false);
            System.out.println(numeros[card] + " entrou");
        } else if (numeros[card] == 106) {
            image.setImageResource(carta106);
            image.setClickable(false);
            System.out.println(numeros[card] + " entrou");
        } else if (numeros[card] == 201) {
            image.setImageResource(carta201);
            image.setClickable(false);
            System.out.println(numeros[card] + " entrou");
        } else if (numeros[card] == 202) {
            image.setImageResource(carta202);
            image.setClickable(false);
            System.out.println(numeros[card] + " entrou");
        } else if (numeros[card] == 203) {
            image.setImageResource(carta203);
            image.setClickable(false);
            System.out.println(numeros[card] + " entrou");
        } else if (numeros[card] == 204) {
            image.setImageResource(carta204);
            image.setClickable(false);
            System.out.println(numeros[card] + " entrou");
        } else if (numeros[card] == 205) {
            image.setImageResource(carta205);
            image.setClickable(false);
            System.out.println(numeros[card] + " entrou");
        } else if (numeros[card] == 206) {
            image.setImageResource(carta206);
            image.setClickable(false);
            System.out.println(numeros[card] + " entrou");
        }
        System.out.println(numeros[card]);
        if (cardNumber == 1) {
            if (numeros[card] > 200) {
                primeiraCarta = numeros[card] - 100;
            } else {
                primeiraCarta = numeros[card];
            }
            cardNumber = 2;
            primeiraCartaSelecionada = card;
        } else if (cardNumber == 2) {
            if (numeros[card] > 200) {
                segundaCarta = numeros[card] - 100;
            } else {
                segundaCarta = numeros[card];
            }
            segundaCartaSelecionada = card;


            img1.setClickable(false);
            img2.setClickable(false);
            img3.setClickable(false);
            img4.setClickable(false);
            img5.setClickable(false);
            img6.setClickable(false);
            img7.setClickable(false);
            img8.setClickable(false);
            img9.setClickable(false);
            img10.setClickable(false);
            img11.setClickable(false);
            img12.setClickable(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //verifica
                    verificarCartas();
                }
            }, 500);

        }

    }

    private void verificarCartas(){


        if(primeiraCarta==segundaCarta){
            if(primeiraCartaSelecionada==0){
                img1.setVisibility(View.INVISIBLE);
            }else if(primeiraCartaSelecionada==1){
                img2.setVisibility(View.INVISIBLE);;
            }else if(primeiraCartaSelecionada==2){
                img3.setVisibility(View.INVISIBLE);
            }else if(primeiraCartaSelecionada==3){
                img4.setVisibility(View.INVISIBLE);
            }else if(primeiraCartaSelecionada==4){
                img5.setVisibility(View.INVISIBLE);
            }else if(primeiraCartaSelecionada==5){
                img6.setVisibility(View.INVISIBLE);
            }else if(primeiraCartaSelecionada==6){
                img7.setVisibility(View.INVISIBLE);
            }else if(primeiraCartaSelecionada==7){
                img8.setVisibility(View.INVISIBLE);
            }else if(primeiraCartaSelecionada==8){
                img9.setVisibility(View.INVISIBLE);
            }else if(primeiraCartaSelecionada==9){
                img10.setVisibility(View.INVISIBLE);
            }else if(primeiraCartaSelecionada==10){
                img11.setVisibility(View.INVISIBLE);
            }else if(primeiraCartaSelecionada==11){
                img12.setVisibility(View.INVISIBLE);
            }

            if(segundaCartaSelecionada==0){
                img1.setVisibility(View.INVISIBLE);
            }else if(segundaCartaSelecionada==1){
                img2.setVisibility(View.INVISIBLE);
            }else if(segundaCartaSelecionada==2){
                img3.setVisibility(View.INVISIBLE);
            }else if(segundaCartaSelecionada==3){
                img4.setVisibility(View.INVISIBLE);
            }else if(segundaCartaSelecionada==4){
                img5.setVisibility(View.INVISIBLE);
            }else if(segundaCartaSelecionada==5){
                img6.setVisibility(View.INVISIBLE);
            }else if(segundaCartaSelecionada==6){
                img7.setVisibility(View.INVISIBLE);
            }else if(segundaCartaSelecionada==7){
                img8.setVisibility(View.INVISIBLE);
            }else if(segundaCartaSelecionada==8){
                img9.setVisibility(View.INVISIBLE);
            }else if(segundaCartaSelecionada==9){
                img10.setVisibility(View.INVISIBLE);
            }else if(segundaCartaSelecionada==10){
                img11.setVisibility(View.INVISIBLE);
            }else if(primeiraCartaSelecionada==11){
                img12.setVisibility(View.INVISIBLE);
            }


        }else{
            if(primeiraCartaSelecionada==0){
                img1.setImageResource(R.drawable.download);
                img1.setClickable(true);
            }else if(primeiraCartaSelecionada==1){
                img2.setImageResource(R.drawable.download);
                img2.setClickable(true);
            }else if(primeiraCartaSelecionada==2){
                img3.setImageResource(R.drawable.download);
                img3.setClickable(true);
            }else if(primeiraCartaSelecionada==3){
                img4.setImageResource(R.drawable.download);
                img4.setClickable(true);
            }else if(primeiraCartaSelecionada==4){
                img5.setImageResource(R.drawable.download);
                img5.setClickable(true);
            }else if(primeiraCartaSelecionada==5){
                img6.setImageResource(R.drawable.download);
                img6.setClickable(true);
            }else if(primeiraCartaSelecionada==6){
                img7.setImageResource(R.drawable.download);
                img7.setClickable(true);
            }else if(primeiraCartaSelecionada==7){
                img8.setImageResource(R.drawable.download);
                img8.setClickable(true);
            }else if(primeiraCartaSelecionada==8){
                img9.setImageResource(R.drawable.download);
                img9.setClickable(true);
            }else if (primeiraCartaSelecionada==9){
                img10.setImageResource(R.drawable.download);
                img10.setClickable(true);
            }else if(primeiraCartaSelecionada==10){
                img11.setImageResource(R.drawable.download);
                img11.setClickable(true);
            }else if(primeiraCartaSelecionada==11){
                img12.setImageResource(R.drawable.download);
                img12.setClickable(true);
            }

            if(segundaCartaSelecionada==0){
                img1.setImageResource(R.drawable.download);
                img1.setClickable(true);
            }else if(segundaCartaSelecionada==1){
                img2.setImageResource(R.drawable.download);
                img2.setClickable(true);
            }else if(segundaCartaSelecionada==2){
                img3.setImageResource(R.drawable.download);
                img3.setClickable(true);
            }else if(segundaCartaSelecionada==3){
                img4.setImageResource(R.drawable.download);
                img4.setClickable(true);
            }else if(segundaCartaSelecionada==4){
                img5.setImageResource(R.drawable.download);
                img5.setClickable(true);
            }else if(segundaCartaSelecionada==5){
                img6.setImageResource(R.drawable.download);
                img6.setClickable(true);
            }else if(segundaCartaSelecionada==6){
                img7.setImageResource(R.drawable.download);
                img7.setClickable(true);
            }else if(segundaCartaSelecionada==7){
                img8.setImageResource(R.drawable.download);
                img8.setClickable(true);
            }else if(segundaCartaSelecionada==8){
                img9.setImageResource(R.drawable.download);
                img9.setClickable(true);
            }else if(segundaCartaSelecionada==9){
                img10.setImageResource(R.drawable.download);
                img10.setClickable(true);
            }else if(segundaCartaSelecionada==10){
                img11.setImageResource(R.drawable.download);
                img11.setClickable(true);
            }else if(segundaCartaSelecionada==11){
                img12.setImageResource(R.drawable.download);
                img12.setClickable(true);
            }


        }
        img1.setClickable(true);
        img2.setClickable(true);
        img3.setClickable(true);
        img4.setClickable(true);
        img5.setClickable(true);
        img6.setClickable(true);
        img7.setClickable(true);
        img8.setClickable(true);
        img9.setClickable(true);
        img10.setClickable(true);
        img11.setClickable(true);
        img12.setClickable(true);

        cardNumber=1;
        primeiraCarta=-1;
        segundaCarta=-1;
        checkEnd();
    }
    private void checkEnd(){
        if(img1.getVisibility()==View.INVISIBLE &&
                img2.getVisibility()==View.INVISIBLE &&
                img3.getVisibility()==View.INVISIBLE &&
                img4.getVisibility()==View.INVISIBLE &&
                img5.getVisibility()==View.INVISIBLE &&
                img6.getVisibility()==View.INVISIBLE &&
                img7.getVisibility()==View.INVISIBLE&&
                img8.getVisibility()==View.INVISIBLE&&
                img9.getVisibility()==View.INVISIBLE&&
                img10.getVisibility()==View.INVISIBLE&&
                img11.getVisibility()==View.INVISIBLE&&
                img12.getVisibility()==View.INVISIBLE){
            /*
            Intent intent = new Intent(JogoMemoria.this, JogoMemoriaResultado.class);
            startActivity(intent);

             */
        }
    }
}
