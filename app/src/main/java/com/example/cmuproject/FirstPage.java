package com.example.cmuproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class FirstPage extends Fragment {

    private ImageView img;
    private Button btnGerirMedic;
    private Button botaoJogo;
    private OnFragmentFirstPageInteractionListener mListener;
    private Button btnFarmacias;
    private Button btnFood;
    private Button btnMeal;

    public FirstPage() {
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
        View view= inflater.inflate(R.layout.fragment_first_page, container, false);
        img=view.findViewById(R.id.principalImage);
        btnGerirMedic=view.findViewById(R.id.gerirMedicamentos);
        btnFarmacias=view.findViewById(R.id.farmaciaMap);
        btnFood = view.findViewById(R.id.foodDetails);
        btnMeal = view.findViewById(R.id.mealDetails);

        view.setBackgroundColor(getResources().getColor(android.R.color.white));

        btnGerirMedic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mListener.gerirMedicamentosInteraction();
            }
        });



        //View viewJogo = inflater.inflate(R.layout.activity_jogo_palavras, container, false);
        botaoJogo=view.findViewById(R.id.jogos);
        botaoJogo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mListener.loadGames();
            }
        });

        img.setImageResource(R.drawable.bacon);

        img.setImageResource(R.drawable.logo);


        btnFarmacias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.loadMapaFarmacias();
            }
        });

        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.foodDetails();
            }
        });

        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mListener = (OnFragmentFirstPageInteractionListener) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentFirstPageInteractionListener {
        void gerirMedicamentosInteraction();
        void loadGames();
        void loadMapaFarmacias();
        void foodDetails();

    }
}
