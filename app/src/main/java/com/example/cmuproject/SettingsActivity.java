package com.example.cmuproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

public class SettingsActivity extends AppCompatActivity {

    private Toolbar myToolbar;
    private final String lightMode = "light";
    private final String darkMode = "dark";
    private RadioGroup rg;
    private SharedPreferences mSettings;
    private RadioButton light;
    private RadioButton dark;
    private FirebaseAuth auth;
    private Button btnGuardar;
    private EditText etGuardar;
    private DatabaseReference mRootRef;
    private DatabaseReference childRef;
    private DatabaseReference chiilRef2;
    private EditText passEt;
    private Button btnPass;
    private EditText oldpwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.ThemeLight);
        Bundle bundle = getIntent().getExtras();
        String s = bundle.getString("theme");
        if(s.equals("light")){
            setTheme(R.style.ThemeLight);
        } else if(s.equals("dark")){
            setTheme(R.style.ThemeDark);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        auth = FirebaseAuth.getInstance();
        mRootRef = FirebaseDatabase.getInstance().getReference();
        String email = auth.getCurrentUser().getEmail();
        email = email.replace(".", ",");
        childRef = mRootRef.child(email);
        chiilRef2=childRef.child("EmergencyNumber");

        childRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.getValue() == null) {
                    FragmentManager manager =getSupportFragmentManager();
                    EmergencyNumberDialog dialog = new EmergencyNumberDialog();
                    dialog.show(manager, "dialog");
                } else {
                    try {
                        JSONObject myobject = new JSONObject(dataSnapshot.getValue().toString());

                        // System.out.println(myobject.getString("EmergencyNumber"));
                        etGuardar.setText(myobject.getString("EmergencyNumber"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        rg=findViewById(R.id.radioGroup);

        oldpwd=findViewById(R.id.oldpasswd);
        passEt=findViewById(R.id.ETchangePasswd);
        btnPass=findViewById(R.id.changePasswd);

        btnGuardar=findViewById(R.id.guardar);
        etGuardar=findViewById(R.id.etGuardar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSettings= getSharedPreferences("themeMode", MODE_PRIVATE);
        System.out.println(mSettings.getString("mode",""));

        final SharedPreferences.Editor mEditor = mSettings.edit();


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.lightOption:
                        mEditor.putString("mode", lightMode);
                        break;
                    case R.id.darkOption:
                        mEditor.putString("mode", darkMode);
                        break;
                }
                mEditor.commit();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }

        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Long.parseLong(etGuardar.getText().toString()) < 900000000 || Long.parseLong(etGuardar.getText().toString())>1000000000){
                    Toast.makeText(getApplication(),"Numbero invalido",Toast.LENGTH_LONG).show();

                }else{
                    chiilRef2.setValue(etGuardar.getText().toString());
                    Toast.makeText(getApplication(),"Numero alterado com sucesso",Toast.LENGTH_LONG).show();

                }
            }
        });

        btnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthCredential credential = EmailAuthProvider
                        .getCredential(auth.getCurrentUser().getEmail(),oldpwd.getText().toString());

            auth.getCurrentUser().reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            auth.getCurrentUser().updatePassword(passEt.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplication(),"Password alterada com sucesso",Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(getApplication(),"Ocorreu um erro",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(getApplication(),"Dados incorretos",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }


        });

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
