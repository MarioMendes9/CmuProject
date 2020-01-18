package com.example.cmuproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmuproject.R;
import com.example.cmuproject.model.MedicamentViewModel;
import com.example.cmuproject.model.Medicamento;

import java.util.List;

public class MedicamentoAdpter extends RecyclerView.Adapter<MedicamentoAdpter.MedicamentoViewHolder>{

    private List<Medicamento> mMedicamentos;
    MedicamentViewModel mMedicamentoModel;

    public MedicamentoAdpter(MedicamentViewModel mMedicamentoModel) {
        this.mMedicamentoModel = mMedicamentoModel;
    }



    public void setmMedicamentos(List<Medicamento> medicamentos){
        this.mMedicamentos=medicamentos;
        notifyDataSetChanged();

    }


    @Override
    public MedicamentoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View medicamentoView=inflater.inflate(R.layout.medicamento_row,parent,false);
        return new MedicamentoViewHolder(medicamentoView);
    }

    @Override
    public void onBindViewHolder(MedicamentoViewHolder viewHolder, int position) {
        Medicamento medicamento=mMedicamentos.get(position);

        //completar
    }

    @Override
    public int getItemCount(){
        return mMedicamentos.size();
    }

    public class MedicamentoViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView quantidade;
        public TextView periodo;
        public Button btnEdit;


        public MedicamentoViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            quantidade=itemView.findViewById(R.id.quantidade);
            periodo=itemView.findViewById(R.id.periodo);
            btnEdit=itemView.findViewById(R.id.editBtn);

            //Escolher o que faz o btn
        }
    }

}
