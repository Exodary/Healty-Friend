package com.example.krasimir.fitness_friend.ListRecipes;

import com.example.krasimir.fitness_friend.base.data.RemoteData;
import com.example.krasimir.fitness_friend.base.models.Recipe;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Krasimir on 10/10/2017.
 */

@Module()
public class ListRecipesModule {
    @Provides
    ListRecipesContracts.Presenter provideListRecipesPresenter(RemoteData<Recipe> data) {
        return new ListRecipesPresenter(data);
    }
}
