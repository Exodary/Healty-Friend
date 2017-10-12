package com.example.krasimir.fitness_friend.RecipeDetails;

import android.os.Bundle;

import com.example.krasimir.fitness_friend.NavMain.NavMainActivity;
import com.example.krasimir.fitness_friend.R;
import com.example.krasimir.fitness_friend.base.models.Recipe;

import javax.inject.Inject;

public class RecipeDetailsActivity extends NavMainActivity {

    private Recipe mRecipe;

    @Inject
    RecipeDetailsContracts.Presenter mRecipeDetailsPresenter;

    private RecipeDetailsView mRecipeDetailsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecipeDetailsView = RecipeDetailsView.newInstance();

        mRecipe = (Recipe) getIntent().getParcelableExtra("recipe_details");
        mRecipeDetailsPresenter.setRecipe(mRecipe);

        mRecipeDetailsView.setPresenter(mRecipeDetailsPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_container, mRecipeDetailsView)
                .commit();
    }

    @Override
    protected void onResume() {
        mRecipeDetailsView.setPresenter(mRecipeDetailsPresenter);
        super.onResume();
    }
}
