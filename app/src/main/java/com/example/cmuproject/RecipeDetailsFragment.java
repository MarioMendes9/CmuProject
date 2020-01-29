package com.example.cmuproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cmuproject.retrofit_models.RecipeDetails;
import com.example.cmuproject.services.SpoonacularApi;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentDetailsInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecipeDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeDetailsFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private final String API_KEY = "1bee02ccb720455eb04a6541f40887be";

    private String mParam1;
    private TextView title;
    private TextView readyInMinutes;
    private TextView servings;
    private ImageView image;
    private Button btnVegetarian;
    private Button btnVegan;
    private Button btnGlutenFree;
    private Button btnDairyFree;
    private Button btnVeryHealthy;
    private Button btnCheap;
    private Button btnVeryPopular;
    private TextView healthScore;
    private LinearLayout vegatarian;
    private LinearLayout vegan;
    private LinearLayout glutenFree;
    private LinearLayout dairy;
    private LinearLayout healthy;
    private LinearLayout cheap;
    private LinearLayout popular;
    private TextView link;

    private OnFragmentDetailsInteractionListener mListener;

    public RecipeDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment RecipeDetailsFragment.
     */
    public static RecipeDetailsFragment newInstance(String param1) {
        RecipeDetailsFragment fragment = new RecipeDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recipe_details, container, false);
        this.title = v.findViewById(R.id.titleRecipe);
        this.readyInMinutes = v.findViewById(R.id.readyRecipe);
        this.servings = v.findViewById(R.id.servingsRecipe);
        this.image = v.findViewById(R.id.imageRecipe);
        this.btnVegetarian = v.findViewById(R.id.vegetarianButton);
        this.btnVegan = v.findViewById(R.id.veganButton);
        this.btnGlutenFree = v.findViewById(R.id.glutenFreeButton);
        this.btnDairyFree = v.findViewById(R.id.dairyFreeButton);
        this.btnVeryHealthy = v.findViewById(R.id.veryHealthyButton);
        this.btnCheap = v.findViewById(R.id.cheapButton);
        this.btnVeryPopular = v.findViewById(R.id.veryPopularButton);
        this.healthScore = v.findViewById(R.id.healthScore);
        this.vegatarian = v.findViewById(R.id.container_vegetarian);
        this.vegan = v.findViewById(R.id.container_vegan);
        this.glutenFree = v.findViewById(R.id.container_gluten);
        this.dairy = v.findViewById(R.id.container_dairy);
        this.healthy = v.findViewById(R.id.container_healthy);
        this.cheap = v.findViewById(R.id.container_cheap);
        this.popular = v.findViewById(R.id.container_popular);
        this.link = v.findViewById(R.id.link);


        getApiMeal().recipeDetails(Integer.parseInt(mParam1),API_KEY)
                .enqueue(new Callback<RecipeDetails>() {
                    @Override
                    public void onResponse(Call<RecipeDetails> call, Response<RecipeDetails> response) {
                        showAll();
                        Picasso.get().load(response.body().getImage()).into(image);
                        title.setHint(response.body().getTitle());
                        readyInMinutes.setText("Ready In Minutes: "+response.body().getReadyInMinutes());
                        servings.setText("Servings: "+response.body().getServings());
                        healthScore.setText("Health Score: "+response.body().getHealthScore());
                        System.out.println(response.body().isGlutenFree());
                        if(!response.body().isVegetarian()){
                            vegatarian.setVisibility(View.GONE);
                        }
                        if(!response.body().isVegan()){
                            vegan.setVisibility(View.GONE);
                        }
                        if(response.body().isGlutenFree()) {
                            glutenFree.setVisibility(View.GONE);
                        }
                        if(response.body().isDairyFree()){
                            dairy.setVisibility(View.GONE);
                        }
                        if(!response.body().isVeryHealthy()){
                            healthy.setVisibility(View.GONE);
                        }
                        if(!response.body().isCheap()){
                            cheap.setVisibility(View.GONE);
                        }
                        if(!response.body().isVeryPopular()){
                            popular.setVisibility(View.GONE);
                        }
                        String linkText = "Mais informação: <a href=" + response.body().getSpoonacularSourceUrl()+">Spoonacular Recipe</a>";
                        link.setText(Html.fromHtml(linkText));
                        link.setMovementMethod(LinkMovementMethod.getInstance());
                    }

                    @Override
                    public void onFailure(Call<RecipeDetails> call, Throwable t) {
                        System.out.println("ESTA MAU");
                        System.out.println(call.request());
                    }
                });
        return v;
    }

    private void showAll(){

        this.btnVegetarian.setBackgroundResource(R.drawable.btn_vegetarian);
        this.btnVegan.setBackgroundResource(R.drawable.btn_vegan);
        this.btnGlutenFree.setBackgroundResource(R.drawable.btn_gluten);
        this.btnDairyFree.setBackgroundResource(R.drawable.btn_dairy);
        this.btnVeryHealthy.setBackgroundResource(R.drawable.btn_healthy);
        this.btnCheap.setBackgroundResource(R.drawable.btn_cheap);
        this.btnVeryPopular.setBackgroundResource(R.drawable.btn_popular);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentDetailsInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentDetailsInteractionListener) {
            mListener = (OnFragmentDetailsInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentDetailsInteractionListener");
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
    public interface OnFragmentDetailsInteractionListener {
        void onFragmentDetailsInteraction(Uri uri);

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
