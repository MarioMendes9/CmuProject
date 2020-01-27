package com.example.cmuproject.Adapters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cmuproject.R;
import com.example.cmuproject.TomaDialog;
import com.example.cmuproject.model.MedicamentosViewModel;
import com.example.cmuproject.model.PendentToma;
import com.example.cmuproject.model.Toma;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class TomaAdapter extends RecyclerView.Adapter<TomaAdapter.TomasViewHolder> {

    private Context context;
    private List<PendentToma> mTomas;


    public TomaAdapter() {

    }
    public void setmTomas(List<PendentToma> tomas){
        this.mTomas=tomas;
        notifyDataSetChanged();
    }

    @Override
    public TomasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();

        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_toma, parent, false);

        return new TomasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TomasViewHolder holder, int position) {
        PendentToma ptoma=mTomas.get(position);
        System.out.println(ptoma.toString());
        holder.medicamento.setText(ptoma.getMedicamento());
        holder.alturaDoDia.setText(ptoma.getAltura());
        String theAltura="";
        String hour=new SimpleDateFormat("HH:mm").format(new Date());
        int thehora=Integer.parseInt(hour.split(":")[0]);


        //FAZ SENTIDO????????????????????????????????????????????????

        if(thehora>=6 && thehora<12){
            theAltura="Manha";
        }else if(thehora>=12 && thehora<14 ){
            theAltura="Almoço";
        }else if(thehora>=14 && thehora<20){
            theAltura="Tarde";
        }else if(thehora>=20 && thehora<=23){
            theAltura="Jantar";
        }else{
            theAltura="Noite";
        }

        if(theAltura.equals(ptoma.getAltura())){
        holder.btnVerificar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager manager=((AppCompatActivity)context).getSupportFragmentManager();
                    TomaDialog newDialog=new TomaDialog();

                    Bundle args=new Bundle();

                    args.putString("medicamento",ptoma.getMedicamento());
                    newDialog.setArguments(args);
                    newDialog.show(manager,"dialog");
                }
            });
        }else{
           holder.btnVerificar.setEnabled(false);
        }


        }

    @Override
    public int getItemCount() {

        if(mTomas!=null){
            return mTomas.size();
        }
        return 0;
    }

    public class TomasViewHolder extends RecyclerView.ViewHolder {

        public TextView medicamento;
        public TextView alturaDoDia;
        public Button btnVerificar;

        public TomasViewHolder(View view) {
            super(view);
            medicamento=view.findViewById(R.id.medicamento);
            alturaDoDia=view.findViewById(R.id.alturaDoDia);
            btnVerificar=view.findViewById(R.id.validarToma);

        }

    }
}
