package com.example.krasimir.fitness_friend.RecipeDetails;

import com.example.krasimir.fitness_friend.base.authentication.AuthenticationProvider;
import com.example.krasimir.fitness_friend.base.data.RemoteData;
import com.example.krasimir.fitness_friend.base.models.Recipe;

import javax.inject.Inject;

/**
 * Created by Krasimir on 10/10/2017.
 */

class RecipeDetailsPresenter implements RecipeDetailsContracts.Presenter {
    private final AuthenticationProvider mAuthProvider;
    private final RemoteData<Recipe> mRemoteData;
    private Recipe mRecipe;
    private String mRecipeId;
    private RecipeDetailsContracts.View mView;

    @Inject
    RecipeDetailsPresenter(AuthenticationProvider authProvider, RemoteData<Recipe> remoteData){
        mAuthProvider = authProvider;
        mRemoteData = remoteData;
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
    public void setRecipeId(String recipeId){
        mRecipeId = recipeId;
    }

    @Override
    public Recipe getRecipe(){
        return mRecipe;
    }

}
