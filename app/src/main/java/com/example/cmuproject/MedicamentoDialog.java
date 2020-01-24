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

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class MedicamentoDialog extends DialogFragment {

    private MediListernerInterface mListener;
    private EditText name;
    private EditText quantidade;
    private ChipGroup chipDias;
    private ChipGroup chipTimeStamp;



    public MedicamentoDialog() {}

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
        b.setTitle("Adicionar Medicamento");
        b.setPositiveButton("Adicionar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        ArrayList<String> tempdias=new ArrayList<>();
                        ArrayList<String> alturas=new ArrayList<>();

                        for (int i = 0; i < chipDias.getChildCount(); i++) {
                            Chip tempChip =(Chip)chipDias.getChildAt(i);
                            if(tempChip.isChecked()){
                                tempdias.add(tempChip.getText().toString());
                            }

                        }
                        for (int i = 0; i < chipTimeStamp.getChildCount(); i++) {
                            Chip tempChip =(Chip)chipTimeStamp.getChildAt(i);
                            if(tempChip.isChecked()){
                                alturas.add(tempChip.getText().toString());
                            }

                        }

                        if (quantidade.getText().toString().matches("") || name.toString().matches("") || tempdias.size()==0 || tempdias.size()==0) {
                            Toast.makeText(getActivity(), "Medicamento invalido",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            if(Integer.parseInt(quantidade.getText().toString()) > 0 ){
                                mListener.addDialogMedicamento(name.getText().toString(), Integer.parseInt(quantidade.getText().toString()), tempdias.toArray(new String[0]),alturas.toArray(new String[0]));
                            }else{
                                Toast.makeText(getActivity(), "A quantidade deve ser maior que 0",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                        dialog.dismiss();
                    }
                }
        ); b.setNegativeButton("Cancelar",
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
        this.chipDias=v.findViewById(R.id.chipGroupDias);
        this.chipTimeStamp=v.findViewById(R.id.chipGroupAlturas);



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
        void addDialogMedicamento(String name, int qtd,String[] dias, String[] alturas);
    }
}
