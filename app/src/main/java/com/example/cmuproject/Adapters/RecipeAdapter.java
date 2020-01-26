package com.example.cmuproject.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmuproject.R;
import com.example.cmuproject.retrofit_models.Recipe;
import com.example.cmuproject.retrofit_models.Recipes;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private Context mContext;
    private Recipes mRecipe;

    public RecipeAdapter(Context mContext, Recipes mRecipe) {
        this.mContext = mContext;
        this.mRecipe = mRecipe;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View recipeView = inflater.inflate(R.layout.item_recipe, parent, false);

        return new RecipeViewHolder(recipeView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {

        Recipe rec = mRecipe.getResults().get(position);
        TextView title = holder.title;
        title.setText(rec.getTitle());

        TextView ready = holder.ready;
        ready.setText("Ready In Minutes: "+rec.getReadyInMinutes());

        TextView servings = holder.servings;
        servings.setText("Servings: "+rec.getServings());
    }

    @Override
    public int getItemCount() {
        return mRecipe.getResults().size();
    }



    public class RecipeViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView ready;
        public TextView servings;
        public Button details;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title=itemView.findViewById(R.id.title);
            this.ready=itemView.findViewById(R.id.ready);
            this.servings = itemView.findViewById(R.id.servings);
            this.details = itemView.findViewById(R.id.detailsRecipe);

        }

    }
}
