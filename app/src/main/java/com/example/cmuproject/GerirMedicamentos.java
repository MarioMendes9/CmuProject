package com.example.cmuproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cmuproject.Adapters.MedicamentoAdpter;
import com.example.cmuproject.model.MedicamentosViewModel;
import com.example.cmuproject.model.Medicamento;
import com.example.cmuproject.model.Toma;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class GerirMedicamentos extends Fragment {


    private OnFragmentGMInteractionListener mListener;
    private MedicamentosViewModel medicamentoViewModel;
    private RecyclerView rc;
    private MedicamentoAdpter medicAdapter;
    private FloatingActionButton fab;

    public GerirMedicamentos() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        medicamentoViewModel=new ViewModelProvider(getActivity()).get(MedicamentosViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_gerir_medicamentos, container, false);
        rc=view.findViewById(R.id.reciclerview);
        fab=view.findViewById(R.id.fab);


        rc.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.ItemDecoration itemDecoration=new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        rc.addItemDecoration(itemDecoration);
        medicAdapter=new MedicamentoAdpter();
        rc.setAdapter(medicAdapter);

        medicamentoViewModel.getallMedicamentos().observe(getActivity(), new Observer<List<Medicamento>>() {
            @Override
            public void onChanged(List<Medicamento> medicamentos) {
                medicAdapter.setmMedicamentos(medicamentos);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getParentFragmentManager();
                MedicamentoDialog newDialog=new MedicamentoDialog();
                newDialog.show(fragmentManager,"dialog");
            }
        });

        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentGMInteractionListener {
        void onFragmentGMInteraction(Uri uri);
    }
}
