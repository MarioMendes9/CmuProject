package com.example.cmuproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class JogoAritmeticoFragmento extends Fragment {


    private TextView equacao;
    private TextView titulo;
    private TextView resposta;
    private TextView respostasCorretas;
    private Button validar;
    private String listaOperadores[] = {
            "+",
            "-",
            "*"};
    private String op;
    private int valor1=0;
    private int valor2=0;
    private int sum=0;
    private int mult=0;
    private int sub=0;
    private int cont=0;

    private OnFragmentJogoAritmeticoListener mListener;

    public JogoAritmeticoFragmento() {

    }

    public JogoAritmeticoFragmento newInstance() {
        JogoAritmeticoFragmento fragment = new JogoAritmeticoFragmento();
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
        View  view = inflater.inflate(R.layout.fragment_jogo_aritmetico, container, false);
        respostasCorretas=view.findViewById(R.id.respostasCorretas);
        equacao=view.findViewById(R.id.num1);
        titulo=view.findViewById(R.id.titulo);
        resposta=view.findViewById(R.id.resposta);
        validar=view.findViewById(R.id.validar);

        equacao();

        validar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(op=="+"){
                    sum=(valor1+valor2);
                    if(Integer.parseInt(resposta.getText().toString())==sum){
                        cont++;
                        respostasCorretas.setText("Tem " +cont+ " respostas corretas");
                    }else{
                        errado();
                    }
                }else if(op=="-"){
                    sub=(valor1-valor2);
                    if(Integer.parseInt(resposta.getText().toString())==sub){
                        cont++;
                        respostasCorretas.setText("Tem " +cont+ " respostas corretas");
                    }else{
                        errado();
                    }
                }else if(op=="*"){
                    mult=(valor1*valor2);
                    if(Integer.parseInt(resposta.getText().toString())==mult){
                        cont++;
                        respostasCorretas.setText("Tem " +cont+ " respostas corretas");
                    }else{
                        errado();
                    }
                }
                equacao();
                resposta.setText("");
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentJogoAritmeticoListener) {
            mListener = (OnFragmentJogoAritmeticoListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentMemoryResultInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentJogoAritmeticoListener {
        // TODO: Update argument type and name
        void onFragmentJogoAritmeticoListener(Uri uri);
        void resultadoJogoAritmetico(String count);
    }

    @Override
    public void onResume() {
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
    }

    protected void errado(){
        mListener.resultadoJogoAritmetico(String.valueOf(cont));

    }
}
