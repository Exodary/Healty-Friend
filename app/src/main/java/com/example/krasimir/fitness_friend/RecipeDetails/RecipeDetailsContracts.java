package com.example.krasimir.fitness_friend.RecipeDetails;

import com.example.krasimir.fitness_friend.base.contracts.BaseContracts;
import com.example.krasimir.fitness_friend.base.models.Recipe;

/**
 * Created by Krasimir on 10/10/2017.
 */

public class RecipeDetailsContracts extends BaseContracts {
    interface View extends BaseContracts.View<Presenter> {

    }

    interface Presenter extends BaseContracts.Presenter<View> {

        void setRecipe(Recipe recipe);

        Recipe getRecipe();

    }
}
