package com.example.cmuproject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegistoFragment extends Fragment {
    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;
    private OnFragmentRegisteInteractionListener mListener;

    private EditText userEmail;
    private EditText userPass;
    private Button btnRegisto;



    public RegistoFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_registo, container, false);

        userEmail=view.findViewById(R.id.fieldEmailRegisto);
        userPass=view.findViewById(R.id.fieldPasswordRegisto);
        btnRegisto=view.findViewById(R.id.createNewAcc);

        btnRegisto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

            mListener = (OnFragmentRegisteInteractionListener) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void createAccount() {

        String email=userEmail.getText().toString();
        String password=userPass.getText().toString();

        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            mListener.onFragmentRegisteInteraction(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


    private boolean validateForm() {
        boolean valid = true;

        if (TextUtils.isEmpty(userEmail.getText().toString())) {
            userEmail.setError("Required.");
            valid = false;
        } else {
            userEmail.setError(null);
        }

        if (TextUtils.isEmpty(userPass.getText().toString())) {
            userPass.setError("Required.");
            valid = false;
        } else {
            userPass.setError(null);
        }

        return valid;
    }

    public interface OnFragmentRegisteInteractionListener {
        // TODO: Update argument type and name
        void onFragmentRegisteInteraction(FirebaseUser user);
    }
}
