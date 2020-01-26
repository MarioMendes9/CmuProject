package com.example.cmuproject;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toolbar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class FirstPage extends Fragment {

    private ImageView img;
    private Button btnGerirMedic;
    private Button botaoJogo;
    private OnFragmentFirstPageInteractionListener mListener;
    private Button btnFarmacias;
    private Button btnFood;
    private Button btnMeal;
    private static final int REQUEST_FINE_LOCATION = 100;

    private Toolbar myToolbar;
    private Button btnTomas;

    private FirebaseAuth auth;
    private DatabaseReference mRootRef;
    private DatabaseReference childRef;
    private TextView info;


    public FirstPage() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //start serviço

        getLastLocation();
        System.out.println(isMyServiceRunning(TrackService.class));
        if (!isMyServiceRunning(TrackService.class)) {

            Intent i = new Intent(getContext(), TrackService.class);
            getContext().startService(i);
        }


        auth = FirebaseAuth.getInstance();
        mRootRef = FirebaseDatabase.getInstance().getReference();
        String email = auth.getCurrentUser().getEmail();
        email = email.replace(".", ",");
        childRef = mRootRef.child(email);
        childRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.getValue() == null) {
                    FragmentManager manager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
                    EmergencyNumberDialog dialog = new EmergencyNumberDialog();

                    dialog.show(manager, "dialog");
                } else {
                    try {
                        JSONObject myobject = new JSONObject(dataSnapshot.getValue().toString());

                        // System.out.println(myobject.getString("EmergencyNumber"));
                        info.setText(myobject.getString("EmergencyNumber"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_first_page, container, false);
        img = view.findViewById(R.id.principalImage);
        btnGerirMedic = view.findViewById(R.id.gerirMedicamentos);
        btnFarmacias = view.findViewById(R.id.farmaciaMap);
        btnTomas = view.findViewById(R.id.tomasMedicamentos);
        btnFood = view.findViewById(R.id.foodDetails);
        btnMeal = view.findViewById(R.id.mealDetails);
        info = view.findViewById(R.id.info);


        btnGerirMedic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mListener.gerirMedicamentosInteraction();
            }
        });

        botaoJogo = view.findViewById(R.id.jogos);
        botaoJogo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mListener.loadGames();
            }
        });

        img.setImageResource(R.drawable.logo);

        btnFarmacias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.loadMapaFarmacias();
            }
        });

        btnTomas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLastLocation();
                mListener.gerirTomas();
            }
        });
        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.foodDetails();
            }
        });
        btnMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.loadRecipes();
            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mListener = (OnFragmentFirstPageInteractionListener) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
            return;
        }
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINE_LOCATION);
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public interface OnFragmentFirstPageInteractionListener {
        void gerirMedicamentosInteraction();

        void loadGames();

        void loadMapaFarmacias();

        void gerirTomas();

        void foodDetails();
        void loadRecipes();

    }

}
