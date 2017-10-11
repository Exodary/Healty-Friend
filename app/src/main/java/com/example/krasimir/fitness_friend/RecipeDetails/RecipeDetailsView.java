package com.example.krasimir.fitness_friend.RecipeDetails;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.krasimir.fitness_friend.R;
import com.example.krasimir.fitness_friend.base.models.Recipe;
import com.squareup.picasso.Picasso;

/**
 * Created by Krasimir on 10/10/2017.
 */

public class RecipeDetailsView extends Fragment implements RecipeDetailsContracts.View{

    private RecipeDetailsContracts.Presenter mPresenter;

    private TextView mRecipeName;
    private TextView mRecipeDescription;
    private TextView mRecipeIngredients;
    private TextView mRecipeCookingTime;
    private TextView mRecipePreparationTime;
    private TextView mRecipeServings;
    private ImageView mRecipeImage;



    public RecipeDetailsView() {
        // Required empty public constructor
    }

    public static RecipeDetailsView newInstance() {
        return new RecipeDetailsView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_details, container, false);

        Recipe recipe = mPresenter.getRecipe();

        mRecipeName = (TextView) view.findViewById(R.id.recipe_name);
        mRecipeIngredients = (TextView) view.findViewById(R.id.recipe_ingredients);
        mRecipeCookingTime = (TextView) view.findViewById(R.id.recipe_cookingTime);
        mRecipePreparationTime = (TextView) view.findViewById(R.id.recipe_preparationTime);
        mRecipeServings = (TextView) view.findViewById(R.id.recipe_servings);
        mRecipeImage = (ImageView)view.findViewById(R.id.recipe_image);
        mRecipeDescription = (TextView) view.findViewById(R.id.recipe_description);

        mRecipeName.setText("Name: " + recipe.getName());
        mRecipeIngredients.setText("Ingredients: " + recipe.getIngredients());
        mRecipeCookingTime.setText("Cooking Time: " + recipe.getCookingTime());
        mRecipePreparationTime.setText("Preparation Time: " + recipe.getPreparationTime());
        mRecipeServings.setText("Servings: " + recipe.getServings());
        Picasso.with(getContext()).load(recipe.getImageUrl()).into(mRecipeImage);
        mRecipeDescription.setText("Description: " + recipe.getDescription());

        return view;
    }

    @Override
    public void setPresenter(RecipeDetailsContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        mPresenter.subscribe(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        mPresenter.unsubscribe();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
        mPresenter = null;
    }
}
