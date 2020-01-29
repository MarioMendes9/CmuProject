package com.example.cmuproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentMenuJogosListener interface
 * to handle interaction events.
 * Use the {@link MenuJogosF #newInstance} factory method to
 * create an instance of this fragment.
 */

public class MenuJogosF extends Fragment {


    private Button iniciarJogoPalavras;
    private Button iniciarJogoAritmetico;
    private Button iniciarJogoMemoria;

    private OnFragmentMenuJogosListener mListener;

    public MenuJogosF() {

    }


    public  MenuJogosF newInstance() {
        MenuJogosF fragment = new MenuJogosF();
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


        View view = inflater.inflate(R.layout.fragment_menu_jogos, container, false);

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
            mListener = (OnFragmentMenuJogosListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentMenuJogosListener {
        void OnFragmentMenuJogosListener(Uri uri);
        void jogoMemoria();
        void jogoAritmetico();
        void jogoPalavras();
    }
}
