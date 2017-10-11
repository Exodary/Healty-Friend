package com.example.krasimir.fitness_friend.RecipeDetails;

import com.example.krasimir.fitness_friend.base.authentication.AuthenticationProvider;
import com.example.krasimir.fitness_friend.base.data.RemoteData;
import com.example.krasimir.fitness_friend.base.models.Recipe;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Krasimir on 10/10/2017.
 */

@Module()
public class RecipeDetailsModule {
    @Provides
    RecipeDetailsContracts.Presenter provideRecipeDetailsPresenter(AuthenticationProvider authProvider, RemoteData<Recipe> data) {
        return new RecipeDetailsPresenter(authProvider, data);
    }
}

