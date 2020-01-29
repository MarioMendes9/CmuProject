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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentJogoAritmeticoResultadoInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ResultadoNumberFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */


public class ResultadoNumberFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    TextView respostasCertas;
    TextView tituloResult;
    Button jogarNovamente;


    // TODO: Rename and change types of parameters
    private String texto;

    private OnFragmentJogoAritmeticoResultadoInteractionListener mListener;

    public ResultadoNumberFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultadoNumberFragment.
     */
    // TODO: Rename and change types and number of parameters
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

    // TODO: Rename method, update argument and hook method into UI event
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentJogoAritmeticoResultadoInteractionListener {
        // TODO: Update argument type and name
        void onFragmentJogoAritmeticoResultadoInteraction(Uri uri);
        void jogoAritmetico();
    }
}
