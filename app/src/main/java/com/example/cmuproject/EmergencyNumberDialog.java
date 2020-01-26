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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EmergencyNumberDialog extends DialogFragment {

    //private  emListernerInterface mListener;
    private EditText mNumber;

    private FirebaseAuth auth;
    private DatabaseReference mRootRef;
    private DatabaseReference childRef;

    public EmergencyNumberDialog() {
    }

    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder b=new AlertDialog.Builder(getActivity());
        auth=FirebaseAuth.getInstance();
        mRootRef= FirebaseDatabase.getInstance().getReference();


        b.setTitle("Adicionar Numero de Emergencia");
        b.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(Long.parseLong(mNumber.getText().toString()) < 900000000 || Long.parseLong(mNumber.getText().toString())>1000000000){
                    Toast.makeText(getActivity(),"Numbero invalido",Toast.LENGTH_LONG).show();

                }else{
                    //Cria na base de dados
                    String email=auth.getCurrentUser().getEmail();
                    email=email.replace(".",",");

                   // Map<String,Map> userinfo=new HashMap<String,Map>();
                    Map<String,String> userDados=new HashMap<String,String>();
                    userDados.put("EmergencyNumber",mNumber.getText().toString());
                    userDados.put("LastLocat","null");
                    //userinfo.put(email,userDados);
                   // mRootRef.push().setValue(email);
                    mRootRef.child(email).setValue(userDados);

                    //mRootRef.push().setValue(userinfo);

                }

            }
        });

        LayoutInflater i=getActivity().getLayoutInflater();

        View v=i.inflate(R.layout.dialog_emergency_number,null);
        mNumber=v.findViewById(R.id.numeroEmergencia);

        b.setView(v);
        return b.create();


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
     //   mListener = (emListernerInterface) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
       // mListener = null;
    }

    public interface emListernerInterface {

    }
}
