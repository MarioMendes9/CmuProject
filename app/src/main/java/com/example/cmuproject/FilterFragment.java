package com.example.cmuproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cmuproject.Adapters.RecipeAdapter;
import com.example.cmuproject.retrofit_models.Recipes;
import com.example.cmuproject.services.SpoonacularApi;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class FilterFragment extends Fragment {

    private ChipGroup chipAler;
    private ChipGroup chipDiet;
    private String dietQuery;
    private String alerQuery;
    private TextView aler;
    private TextView diet;
    private Button btnSearch;

    private OnFragmentFilterInteractionListener mListener;

    public FilterFragment() {
    }

    public static FilterFragment newInstance() {
        FilterFragment fragment = new FilterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter,container,false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        btnSearch = view.findViewById(R.id.search_recipe);
        chipAler = view.findViewById(R.id.chipGroupIntolerancias);
        chipDiet = view.findViewById(R.id.chipGroupDiet);
        aler=view.findViewById(R.id.alergenios);
        diet = view.findViewById(R.id.diet);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dietQuery = "";
                alerQuery = "";


                for (int i = 0; i < chipAler.getChildCount(); i++) {
                    Chip tempChip =(Chip)chipAler.getChildAt(i);
                    if(tempChip.isChecked()){
                        String temp = tempChip.getText().toString();
                        if(temp.equals("Laticinios")){
                            alerQuery += "dairy" + ",";
                        } else if(temp.equals("Ovo")){
                            alerQuery += "egg" + ",";
                        } else if(temp.equals("Glutén")){
                            alerQuery += "gluten" + ",";
                        } else if(temp.equals("Grão")){
                            alerQuery += "grain" + ",";
                        } else if(temp.equals("Amendoim")){
                            alerQuery += "peanut" + ",";
                        } else if(temp.equals("Frutos do Mar")){
                            alerQuery += "seafood" + ",";
                        } else if(temp.equals("Sésamo")){
                            alerQuery += "sesame" + ",";
                        } else if(temp.equals("Marisco")){
                            alerQuery += "shellfish" + ",";
                        } else if(temp.equals("Soja")){
                            alerQuery += "soy" + ",";
                        } else if(temp.equals("Sulfito")){
                            alerQuery += "sulfite" + ",";
                        } else if(temp.equals("Frutos Secos")){
                            alerQuery += "tree+nut" + ",";
                        } else if(temp.equals("Trigo")){
                            alerQuery += "wheat" + ",";

                        }
                    }

                }
                if(alerQuery.endsWith(",")) {
                    alerQuery = alerQuery.substring(0,alerQuery.length() - 1);
                }
                for (int i = 0; i < chipDiet.getChildCount(); i++) {
                    Chip tempChip =(Chip)chipDiet.getChildAt(i);
                    if(tempChip.isChecked()){
                        String temp = tempChip.getText().toString();
                        if(temp.equals("Vegetariano")){
                            dietQuery += "vegetarian" + ",";
                        } else if(temp.equals("Sem Gluten")){
                            dietQuery += "glutenFree" + ",";
                        } else {
                            dietQuery += "vegan" + ",";
                        }
                    }

                }

                if(dietQuery.endsWith(",")) {
                    dietQuery = dietQuery.substring(0,dietQuery.length() - 1);
                }

                mListener.getList(alerQuery,dietQuery);

            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentFilterInteractionListener) {
            mListener = (OnFragmentFilterInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentListInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentFilterInteractionListener {
        void onFragmentFilterInteraction(Uri uri);

        void getList(String aler, String diet);

    }
}
