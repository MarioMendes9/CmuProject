package com.example.cmuproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private Context mContext;
    private Recipes mRecipe;

    public RecipeAdapter(Context mContext, Recipes mRecipe) {
        System.out.println("ENTROU AQUI");
        this.mContext = mContext;
        this.mRecipe = mRecipe;
        System.out.println(mRecipe.toString());
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("ENTROU VIEW HOLDER");
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View recipeView = inflater.inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(recipeView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {

        Recipe rec = mRecipe.getResults().get(position);
        System.out.println("RECIPE :::::::::::::::: " + rec.toString());
        TextView title = holder.title;
        System.out.println("TITLE ::::::::::::::::: " + rec.getTitle());
        title.setText(rec.getTitle());

        TextView ready = holder.ready;
        System.out.println("READY ::::::::::::::::: " + rec.getReadyInMinutes());
        ready.setText(rec.getReadyInMinutes());

        TextView servings = holder.servings;
        System.out.println("SERVINGS ::::::::::::::::: " + rec.getServings());
        servings.setText(rec.getServings());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView ready;
        public TextView servings;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            System.out.println("ENTROU RECIPE VIEW HOLDER");
            this.title=itemView.findViewById(R.id.title);
            this.ready=itemView.findViewById(R.id.ready);
            this.servings = itemView.findViewById(R.id.servings);

        }
    }
}
