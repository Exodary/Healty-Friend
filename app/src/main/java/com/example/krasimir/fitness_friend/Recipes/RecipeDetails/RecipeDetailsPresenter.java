package com.example.krasimir.fitness_friend.Recipes.RecipeDetails;

import com.example.krasimir.fitness_friend.base.authentication.AuthenticationProvider;
import com.example.krasimir.fitness_friend.base.data.RemoteRecipesData;
import com.example.krasimir.fitness_friend.base.models.Recipe;

import javax.inject.Inject;

/**
 * Created by Krasimir on 10/10/2017.
 */

public class RecipeDetailsPresenter implements RecipeDetailsContracts.Presenter {
    private final AuthenticationProvider mAuthProvider;
    private final RemoteRecipesData<Recipe> mRemoteRecipesData;
    private Recipe mRecipe;
    private RecipeDetailsContracts.View mView;

    @Inject
    RecipeDetailsPresenter(AuthenticationProvider authProvider, RemoteRecipesData<Recipe> remoteRecipesData){
        mAuthProvider = authProvider;
        mRemoteRecipesData = remoteRecipesData;
    }

    @Override
    public void subscribe(RecipeDetailsContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void setRecipe(Recipe recipe){
        mRecipe = recipe;
    }

    @Override
    public Recipe getRecipe(){
        return mRecipe;
    }

}
