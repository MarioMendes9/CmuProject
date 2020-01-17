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
import com.google.firebase.auth.*;


public class Login extends Fragment {

    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;

    private OnFragmentLoginInteractionListener mListener;

    private Button btnLogin;
    private Button btnRegisto;
    private EditText userEmail;
    private EditText userPass;


    public Login() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        btnLogin = view.findViewById(R.id.emailSignInButton);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        btnRegisto = view.findViewById(R.id.emailCreateAccountButton);
        btnRegisto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentRegistInteraction();
            }
        });
        userEmail = view.findViewById(R.id.fieldEmail);
        userPass = view.findViewById(R.id.fieldPassword);
        mAuth = FirebaseAuth.getInstance();


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (OnFragmentLoginInteractionListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    private void login() {

        if (!validateForm()) {
            return;
        }

        String email = userEmail.getText().toString();
        String password = userPass.getText().toString();


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            mListener.onFragmentLoginInteraction(user);


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
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


    public interface OnFragmentLoginInteractionListener {

        /**
         * Metodo para mudar de fragmento quando o login é realizado com sucesso
         * @param user utilizador que fez login
         */
        void onFragmentLoginInteraction(FirebaseUser user);

        void onFragmentRegistInteraction();
    }
}
