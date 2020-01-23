package com.example.cmuproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.cmuproject.model.Medicamento;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class EditMedicamentoDialog extends DialogFragment {
    private MediEditListernerInterface mListener;
    private EditText name;
    private EditText quantidade;
    private ChipGroup chipDias;
    private ChipGroup chipTimeStamp;
    private Medicamento medi;



    public EditMedicamentoDialog(Medicamento medi) {
        this.medi=medi;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
        b.setTitle("Editar medicamento");
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
                                mListener.editMedicament(name.getText().toString(),Integer.parseInt(quantidade.getText().toString()),
                                        Arrays.toString(tempdias.toArray(new String[0])),Arrays.toString(alturas.toArray(new String[0])));

                        }
                        dialog.dismiss();
                    }
                }
        );
        b.setNeutralButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    mListener.deleteMedicament(medi);
            }
        });
        b.setNegativeButton("Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                }
        );


        LayoutInflater i = getActivity().getLayoutInflater();

        View v = i.inflate(R.layout.dialog_edit_med,null);

        this.name = v.findViewById(R.id.editText_nameMedicamento);
        name.setText(medi.name);
        name.setFocusable(false);

        this.quantidade = v.findViewById(R.id.editText_quantidade);
        quantidade.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
        quantidade.setText(""+0);
        this.chipDias=v.findViewById(R.id.chipGroupDias);
        medi.days=medi.days.replace(" ","");
        medi.alturas=medi.alturas.replace(" ","");
        String[] tempDias=medi.days.substring(1,medi.days.length()-1).split(",");
        for (int j = 0; j < tempDias.length; j++) {
            for (int k = 0; k < chipDias.getChildCount(); k++) {
                Chip tempChip=(Chip) chipDias.getChildAt(k);
                System.out.println(tempChip.getText().toString()+" "+tempDias[j]);
                if(tempDias[j].equals(tempChip.getText().toString())){
                    tempChip.setChecked(true);
                }
            }
        }
        this.chipTimeStamp=v.findViewById(R.id.chipGroupAlturas);
        String[] tempAlturas=medi.alturas.substring(1,medi.alturas.length()-1).split(",");
        for (int j = 0; j < tempAlturas.length; j++) {
            for (int k = 0; k < chipTimeStamp.getChildCount(); k++) {
                Chip tempChip=(Chip) chipTimeStamp.getChildAt(k);
                if(tempAlturas[j].equals(tempChip.getText().toString())){
                    tempChip.setChecked(true);
                }
            }
        }



        b.setView(v);
        return b.create();
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (MediEditListernerInterface) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface MediEditListernerInterface {
        void deleteMedicament(Medicamento medi);
        void editMedicament(String tempName,int tempQuant,String tempDays,String tempAlturas);
    }
}

