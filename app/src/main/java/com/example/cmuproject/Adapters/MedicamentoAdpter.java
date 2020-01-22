package com.example.cmuproject.Adapters;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;


import androidx.recyclerview.widget.RecyclerView;

import com.example.cmuproject.EditMedicamentoDialog;
import com.example.cmuproject.R;
import com.example.cmuproject.model.MedicamentViewModel;
import com.example.cmuproject.model.Medicamento;

import java.util.List;

public class MedicamentoAdpter extends RecyclerView.Adapter<MedicamentoAdpter.MedicamentoViewHolder>{

    private List<Medicamento> mMedicamentos;
    MedicamentViewModel mMedicamentoModel;
    private Context context;

    public MedicamentoAdpter(MedicamentViewModel mMedicamentoModel) {
        this.mMedicamentoModel = mMedicamentoModel;
    }



    public void setmMedicamentos(List<Medicamento> medicamentos){
        this.mMedicamentos=medicamentos;
        notifyDataSetChanged();

    }


    @Override
    public MedicamentoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View medicamentoView=inflater.inflate(R.layout.medicamento_row,parent,false);
        return new MedicamentoViewHolder(medicamentoView);
    }

    @Override
    public void onBindViewHolder(MedicamentoViewHolder viewHolder, int position) {
        Medicamento medicamento=mMedicamentos.get(position);
        viewHolder.name.setText(medicamento.name);
        viewHolder.quantidade.setText(""+medicamento.quantity);
        viewHolder.dias.setText(medicamento.days.substring(1,medicamento.days.length()-1));
        viewHolder.altura.setText(medicamento.alturas.substring(1,medicamento.alturas.length()-1));
        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                EditMedicamentoDialog newDialog=new EditMedicamentoDialog(medicamento);
                newDialog.show(manager,"dialog");
            }
        });

    }

    @Override
    public int getItemCount(){
        if(mMedicamentos!=null){
            return mMedicamentos.size();
        }
        return 0;
    }

    public class MedicamentoViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView quantidade;
        public TextView dias;
        public TextView altura;
        public Button btnEdit;


        public MedicamentoViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            quantidade=itemView.findViewById(R.id.quantidade);
            dias=itemView.findViewById(R.id.dias);
            altura=itemView.findViewById(R.id.altura);
            btnEdit=itemView.findViewById(R.id.editBtn);


        }
    }

}
