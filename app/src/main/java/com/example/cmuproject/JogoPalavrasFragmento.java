package com.example.cmuproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;


public class JogoPalavrasFragmento extends Fragment {

    private TextView palavras;
    private EditText value;
    private Button resposta;
    private TextView respostasCorretas;
    private int a = 0;
    private int count = 0;
    private String palavra;

    String listaPalavras[] = {
            "carro",
            "laranja",
            "papagaio",
            "azul",
            "elefante",
            "cenoura",
            "papel",
            "tinta",
            "tomate",
            "pepino",
            "correr",
            "saltar",
            "corda",
            "coentros",
            "salsa",
            "dinossauro",
            "comer",
            "sopa",
            "prato",
            "marido",
            "cadeira",
            "vassoura",
            "almofada",
            "cabelo",
            "boca",
            "nariz",
            "bolacha",
            "sapato",
            "sapatilha",
            "inteligente",
            "calças",
            "casaco",
            "camisola",
            "saber",
            "cozinhar",
            "tapete",
            "mesa",
            "anel",
            "pulseira",
            "caneta",
            "martelo"
    };
    private OnFragmentJogoPalavrasListener mListener;

    public JogoPalavrasFragmento() {

    }
    public JogoPalavrasFragmento newInstance() {
        JogoPalavrasFragmento fragment = new JogoPalavrasFragmento();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jogo_palavras, container, false);
        palavras = view.findViewById(R.id.palavra);
        value = view.findViewById(R.id.resposta);
        resposta = view.findViewById(R.id.button8);

        respostasCorretas = view.findViewById(R.id.respostasCorretas);

        palavra();
        value.setText("");

        resposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = value.getText().toString();

                boolean equals = s1.equals(palavra);
                if (equals == true) {
                    count++;
                    respostasCorretas.setText("Tem " + count + " respostas corretas");
                    palavra();
                    value.setText("");
                } else {



                    mListener.resultadoWord(String.valueOf(count));

                }


            }
        });
        return view;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
           mListener = (OnFragmentJogoPalavrasListener) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentJogoPalavrasListener {
        void OnFragmentJogoPalavrasListener(Uri uri);
        void resultadoWord(String count);
    }

    @Override
    public void onResume() {
        super.onResume();
        count = 0;
        palavra();
    }

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
