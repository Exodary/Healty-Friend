package com.example.krasimir.fitness_friend.Recipes.CreateRecipe;

import com.example.krasimir.fitness_friend.base.authentication.AuthenticationProvider;
import com.example.krasimir.fitness_friend.base.data.RemoteRecipesData;
import com.example.krasimir.fitness_friend.base.models.Recipe;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Krasimir on 10/10/2017.
 */

@Module
public class CreateRecipeModule {
    @Provides
    CreateRecipeContracts.Presenter provideCreateRecipePresenter(AuthenticationProvider authProvider, RemoteRecipesData<Recipe> data){
        return new CreateRecipePresenter(authProvider, data);
    }
}
