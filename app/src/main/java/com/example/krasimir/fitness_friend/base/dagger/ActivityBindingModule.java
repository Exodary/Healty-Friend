package com.example.krasimir.fitness_friend.base.dagger;

import com.example.krasimir.fitness_friend.Challanges.ChallangesDetails.ChallangeDetailsActivity;
import com.example.krasimir.fitness_friend.Challanges.ChallangesDetails.ChallangeDetailsModule;
import com.example.krasimir.fitness_friend.Challanges.ListChallanges.ListChallangesActivity;
import com.example.krasimir.fitness_friend.Challanges.ListChallanges.ListChallangesModule;
import com.example.krasimir.fitness_friend.Recipes.CreateRecipe.CreateRecipeActivity;
import com.example.krasimir.fitness_friend.Recipes.CreateRecipe.CreateRecipeModule;
import com.example.krasimir.fitness_friend.Exercises.ListExercises.ExercisesDetails.ExerciseDetailsActivity;
import com.example.krasimir.fitness_friend.Exercises.ListExercises.ExercisesDetails.ExerciseDetailsModule;
import com.example.krasimir.fitness_friend.Exercises.ListExercises.ListExercises.ListExercisesActivity;
import com.example.krasimir.fitness_friend.Exercises.ListExercises.ListExercises.ListExercisesModule;
import com.example.krasimir.fitness_friend.Recipes.ListRecipes.ListRecipesActivity;
import com.example.krasimir.fitness_friend.Recipes.ListRecipes.ListRecipesModule;
import com.example.krasimir.fitness_friend.NavMain.NavMainActivity;
import com.example.krasimir.fitness_friend.Recipes.RecipeDetails.RecipeDetailsActivity;
import com.example.krasimir.fitness_friend.Recipes.RecipeDetails.RecipeDetailsModule;
import com.example.krasimir.fitness_friend.SignIn.SignInActivity;
import com.example.krasimir.fitness_friend.SignIn.SignInModule;
import com.example.krasimir.fitness_friend.SignUp.SignUpActivity;
import com.example.krasimir.fitness_friend.SignUp.SignUpModule;
import com.example.krasimir.fitness_friend.UserProfile.UserProfileActivity;
import com.example.krasimir.fitness_friend.UserProfile.UserProfileModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Krasimir on 10/10/2017.
 */

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = { })
    abstract NavMainActivity NavMainActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {UserProfileModule.class})
    abstract UserProfileActivity userProfileActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = { SignInModule.class})
    abstract SignInActivity signInActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {SignUpModule.class})
    abstract SignUpActivity signUpActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {CreateRecipeModule.class})
    abstract CreateRecipeActivity createRecipeActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {ListRecipesModule.class})
    abstract ListRecipesActivity listRecipesActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {RecipeDetailsModule.class})
    abstract RecipeDetailsActivity recipeDetailsActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {ListExercisesModule.class})
    abstract ListExercisesActivity listExercisesActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {ExerciseDetailsModule.class})
    abstract ExerciseDetailsActivity exerciseDetailsActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {ListChallangesModule.class})
    abstract ListChallangesActivity listChallangesActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {ChallangeDetailsModule.class})
    abstract ChallangeDetailsActivity challangeDetailsActivity();
}
