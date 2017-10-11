package com.example.krasimir.fitness_friend.ListRecipes;

import com.example.krasimir.fitness_friend.base.contracts.BaseContracts;
import com.example.krasimir.fitness_friend.base.models.Recipe;

import java.util.ArrayList;

/**
 * Created by Krasimir on 10/10/2017.
 */

public interface ListRecipesContracts {
    interface View extends BaseContracts.View<Presenter> {

        void setRecipes(ArrayList<Recipe> recipes);
    }

    interface Presenter extends BaseContracts.Presenter<View> {
        void load();
    }
}
