package com.example.krasimir.fitness_friend.ListRecipes;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.krasimir.fitness_friend.R;
import com.example.krasimir.fitness_friend.RecipeDetails.RecipeDetailsActivity;
import com.example.krasimir.fitness_friend.base.models.Recipe;

import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;

/**
 * Created by Krasimir on 10/9/2017.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.MyViewHolder> {

    private ArrayList<Recipe> mRecipesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView RecipeName;

        public MyViewHolder(View view) {
            super(view);
            RecipeName = (TextView) view.findViewById(R.id.recipe_name);        }
    }

    public RecipesAdapter(ArrayList<Recipe> Recipes) {
        this.mRecipesList = Recipes;
    }

    @Override
    public RecipesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecipesAdapter.MyViewHolder holder, int position) {
        final Recipe Recipe = mRecipesList.get(position);

        holder.RecipeName.setText(Recipe.getName());
    }

    @Override
    public int getItemCount() {
        return mRecipesList.size();
    }
}

