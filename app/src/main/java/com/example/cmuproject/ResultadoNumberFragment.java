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



public class ResultadoNumberFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    TextView respostasCertas;
    TextView tituloResult;
    Button jogarNovamente;



    private String texto;

    private OnFragmentJogoAritmeticoResultadoInteractionListener mListener;

    public ResultadoNumberFragment() {
        // Required empty public constructor
    }


    public static ResultadoNumberFragment newInstance(String param1, String param2) {
        ResultadoNumberFragment fragment = new ResultadoNumberFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            texto = getArguments().getString("countNumber");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_resultado_number, container, false);
        respostasCertas=v.findViewById(R.id.respostasCertas);
        tituloResult=v.findViewById(R.id.tituloResult);
        jogarNovamente=v.findViewById(R.id.button);

        System.out.println(texto);
        respostasCertas.setText( texto+ " Respostas corretas");

        jogarNovamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.jogoAritmetico();
            }
        });
        return v;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentJogoAritmeticoResultadoInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentJogoAritmeticoResultadoInteractionListener) {
            mListener = (OnFragmentJogoAritmeticoResultadoInteractionListener) context;
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


    public interface OnFragmentJogoAritmeticoResultadoInteractionListener {
        void onFragmentJogoAritmeticoResultadoInteraction(Uri uri);
        void jogoAritmetico();
    }
}
