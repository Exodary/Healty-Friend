package com.example.krasimir.fitness_friend.Recipes.CreateRecipe;

import com.example.krasimir.fitness_friend.base.contracts.BaseContracts;

/**
 * Created by Krasimir on 10/10/2017.
 */

public interface CreateRecipeContracts {
    interface View extends BaseContracts.View<Presenter> {

    }

    interface Presenter extends BaseContracts.Presenter<View> {
        void addRecipe(String recipeName, String recipeDescription, String ingredients, String preparationTime, String cookingTime,
        String servings, String imageUrl, String callories);
    }
}

