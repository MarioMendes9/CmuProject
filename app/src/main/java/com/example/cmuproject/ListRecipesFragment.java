package com.example.cmuproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cmuproject.Adapters.RecipeAdapter;
import com.example.cmuproject.retrofit_models.Recipes;
import com.example.cmuproject.services.SpoonacularApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentListInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListRecipesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListRecipesFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final String API_KEY = "1bee02ccb720455eb04a6541f40887be";

    private String alerQuery;
    private String dietQuery;

    private OnFragmentListInteractionListener mListener;
    private RecipeAdapter rAdapter;
    private RecyclerView mRecyclerView;


    public ListRecipesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListRecipesFragment.
     */
    public static ListRecipesFragment newInstance(String param1, String param2) {
        ListRecipesFragment fragment = new ListRecipesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            alerQuery = getArguments().getString("aler");
            dietQuery = getArguments().getString("diet");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_list_recipes, container, false);


        getApiMeal().searchRecipes(dietQuery, API_KEY, alerQuery)
                .enqueue(new Callback<Recipes>() {
                    @Override
                    public void onResponse(Call<Recipes> call, Response<Recipes> response) {
                        System.out.println("ESTA FIXE");
                        System.out.println(call.request());
                        System.out.println(response.body().toString());
                        rAdapter = new RecipeAdapter(getContext(), response.body());
                        mRecyclerView = view.findViewById(R.id.mRecyclerView);
                        mRecyclerView.setAdapter(rAdapter);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    }

                    @Override
                    public void onFailure(Call<Recipes> call, Throwable t) {
                        System.out.println("ESTA MAU");
                        System.out.println(call.request());
                        System.out.println(t.toString());
                    }
                });
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentListInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentListInteractionListener) {
            mListener = (OnFragmentListInteractionListener) context;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentListInteractionListener {
        void onFragmentListInteraction(Uri uri);
        void getDetails(String id);
    }

    private Retrofit getRetrofitMeal() {
        return new Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private SpoonacularApi getApiMeal() {
        return getRetrofitMeal().create(SpoonacularApi.class);
    }

}
