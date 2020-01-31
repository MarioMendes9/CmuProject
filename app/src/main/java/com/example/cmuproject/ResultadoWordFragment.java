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



public class ResultadoWordFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    TextView resultado;
    TextView respostas;
    Button jogar;

    private String text;

    private OnFragmentInteractionListener mListener;

    public ResultadoWordFragment() {
        // Required empty public constructor
    }


    public static ResultadoWordFragment newInstance(String param1, String param2) {
        ResultadoWordFragment fragment = new ResultadoWordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            text = getArguments().getString("countNumber");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_resultado_word, container, false);
        resultado=v.findViewById(R.id.textView5);
        respostas=v.findViewById(R.id.Result);
        jogar=v.findViewById(R.id.jogarNovamente);

        resultado.setText( text+ " Respostas corretas");

        jogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.jogoPalavras();
            }
        });

        return v;
    }

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
                    + " must implement OnFragmentMemoryResultInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
        void jogoPalavras();
    }
}
