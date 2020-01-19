package com.example.cmuproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

public class MedicamentoDialog extends DialogFragment {

    private MediListernerInterface mListener;
    private EditText name;
    private EditText quantidade;
    private Spinner spin_hour;
    private Spinner spin_min;


    public MedicamentoDialog() {}

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder b=  new  AlertDialog.Builder(getActivity())
                .setTitle("Adicionar Medicamento")
                .setPositiveButton("Adicionar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                String time_stamp=spin_hour.getSelectedItem().toString()+":"+spin_min.getSelectedItem().toString();

                                if(Integer.parseInt(quantidade.getText().toString())>0){
                                    mListener.addDialogMedicamento(name.getText().toString(),Integer.parseInt(quantidade.getText().toString()),time_stamp);
                                }else {
                                    Toast.makeText(getActivity(), "Medicamento invalido",
                                            Toast.LENGTH_SHORT).show();
                                }
                                dialog.dismiss();
                            }
                        }
                )
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        }
                );

        LayoutInflater i = getActivity().getLayoutInflater();

        View v = i.inflate(R.layout.dialog_add_med,null);

        this.name = v.findViewById(R.id.editText_nameMedicamento);
        this.quantidade = v.findViewById(R.id.editText_quantidade);
        this.spin_hour=v.findViewById(R.id.spin_Hour);
        this.spin_min=v.findViewById(R.id.spin_Min);

        b.setView(v);
        return b.create();
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (MediListernerInterface) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface MediListernerInterface {
        void addDialogMedicamento(String name, int qtd,String timeStamp);
    }
}
