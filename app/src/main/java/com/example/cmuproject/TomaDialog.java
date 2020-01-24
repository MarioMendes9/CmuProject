package com.example.cmuproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.cmuproject.model.PendentToma;

public class TomaDialog extends DialogFragment {

   // private PendentToma ptoma;
    private String medicamento;
    private TomaListernerInterface mListener;
    private EditText qtdTomar;
    private EditText medicamentoTomar;

    public TomaDialog() {
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder b=new AlertDialog.Builder(getActivity());

        Bundle args=getArguments();
       // ptoma= (PendentToma) args.getSerializable("ptoma");
        medicamento=args.getString("medicamento");
        b.setTitle("Adicionar Toma");
        b.setPositiveButton("Adicionar",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                    if(Integer.parseInt(qtdTomar.getText().toString())>0){
                        mListener.addTomaDialog(medicamento,Integer.parseInt(qtdTomar.getText().toString()));
                    }else{
                        Toast.makeText(getActivity(),"Toma invalida",Toast.LENGTH_LONG).show();
                    }
            }
        });
        b.setNegativeButton("Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                });

        LayoutInflater i=getActivity().getLayoutInflater();

        View v=i.inflate(R.layout.dialog_addtoma,null);
        System.out.println("Toma name: "+medicamento);
        medicamentoTomar=v.findViewById(R.id.medicamentoTomar);
        medicamentoTomar.setText(medicamento);
        medicamentoTomar.setFocusable(false);


        qtdTomar=v.findViewById(R.id.quantidadeTomar);

        b.setView(v);

        return b.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (TomaListernerInterface) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface TomaListernerInterface {
        void addTomaDialog(String medicName,int quantidade);
    }

}
