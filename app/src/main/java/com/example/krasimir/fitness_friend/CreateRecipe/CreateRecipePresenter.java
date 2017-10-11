package com.example.krasimir.fitness_friend.CreateRecipe;

import com.example.krasimir.fitness_friend.base.authentication.AuthenticationProvider;
import com.example.krasimir.fitness_friend.base.data.RemoteData;
import com.example.krasimir.fitness_friend.base.models.Recipe;

import javax.inject.Inject;

/**
 * Created by Krasimir on 10/10/2017.
 */

public class CreateRecipePresenter implements CreateRecipeContracts.Presenter{
    private final RemoteData<Recipe> mRemoteData;
    private final AuthenticationProvider mAuthProvider;
    private CreateRecipeContracts.View mView;

    @Inject
    public CreateRecipePresenter(AuthenticationProvider authProvider, RemoteData<Recipe> remoteData) {
        mAuthProvider = authProvider;
        mRemoteData = remoteData;
    }

    @Override
    public void subscribe(CreateRecipeContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void addRecipe(String recipeName, String recipeDescription, String recipeIngredients,
                          String cookingTime, String preparationTime, String servings, String imageUrl) {

        String recipeId = mRemoteData.getKey();

        
        Recipe recipe = new Recipe(recipeId, recipeName, recipeDescription, recipeIngredients,
                cookingTime, preparationTime, servings, imageUrl);

        mRemoteData.add(recipe);
    }
}

