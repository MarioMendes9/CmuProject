package com.example.cmuproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class JogosMenuFragment extends Fragment {

    private Button iniciarJogoPalavras;
    private Button iniciarJogoAritmetico;
    private Button iniciarJogoMemoria;

    private OnFragmentMenuJogosInteractionListener mListener;

    public JogosMenuFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jogos_menu, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        iniciarJogoPalavras=view.findViewById(R.id.jogoPalavras);
        iniciarJogoAritmetico=view.findViewById(R.id.jogoAritmetico);
        iniciarJogoMemoria=view.findViewById(R.id.jogoMemoria);

        iniciarJogoPalavras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.jogoPalavras();
            }
        });

        iniciarJogoAritmetico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.jogoAritmetico();
            }
        });

        iniciarJogoMemoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.jogoMemoria();
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentMenuJogosInteractionListener) {
            mListener = (OnFragmentMenuJogosInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentMenuJogosInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentMenuJogosInteractionListener {

        void jogoMemoria();
        void jogoAritmetico();
        void jogoPalavras();
    }
}
