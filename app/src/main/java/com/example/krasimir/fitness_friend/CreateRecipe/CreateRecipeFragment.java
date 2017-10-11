package com.example.krasimir.fitness_friend.CreateRecipe;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.krasimir.fitness_friend.ListRecipes.ListRecipesActivity;
import com.example.krasimir.fitness_friend.NavMain.NavMainActivity;
import com.example.krasimir.fitness_friend.R;

/**
 * A simple {@link Fragment} subclass.
 */
    public class CreateRecipeFragment extends Fragment implements CreateRecipeContracts.View {

        private EditText mInputRecipeName;
        private EditText mInputRecipeDescription;
        private EditText mInputRecipeIngredients;
        private EditText mInputRecipeCookingTime;
        private EditText mInputRecipePreparationTime;
        private EditText mInputRecipeServings;
        private EditText mInputRecipeImageUrl;

        private ProgressBar mProgressBar;


        private Button mBtnCreateRecipe;


        private Button.OnClickListener mCreateRecipeBtnListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String recipeName = mInputRecipeName.getText().toString().trim();
                String recipeDescription = mInputRecipeDescription.getText().toString().trim();
                String recipeIngredients = mInputRecipeIngredients.getText().toString().trim();
                String recipeCookingTime = mInputRecipeCookingTime.getText().toString().trim();
                String recipePreparationTime = mInputRecipePreparationTime.getText().toString().trim();
                String recipeServings = mInputRecipeServings.getText().toString().trim();
                String recipeImageUrl = mInputRecipeImageUrl.getText().toString().trim();

                mProgressBar.setVisibility(View.VISIBLE);

                mPresenter.addRecipe(recipeName, recipeDescription, recipeIngredients, recipeCookingTime,
                        recipePreparationTime, recipeServings, recipeImageUrl);

                Intent intent = new Intent(getActivity(), ListRecipesActivity.class);
                startActivity(intent);
            }
        };

        private CreateRecipeContracts.Presenter mPresenter;

        public CreateRecipeFragment() {
            // Required empty public constructor
        }

        public static CreateRecipeFragment newInstance() {
            return new CreateRecipeFragment();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view =  inflater.inflate(R.layout.fragment_create_recipe, container, false);

            mInputRecipeName = (EditText) view.findViewById(R.id.input_recipe_name);
            mInputRecipePreparationTime = (EditText) view.findViewById(R.id.input_recipe_preparationTime);
            mInputRecipeCookingTime = (EditText) view.findViewById(R.id.input_recipe_cookingTime);
            mInputRecipeIngredients = (EditText) view.findViewById(R.id.input_recipe_ingredietns);
            mInputRecipeServings = (EditText) view.findViewById(R.id.input_recipe_servings);
            mInputRecipeImageUrl = (EditText) view.findViewById(R.id.input_recipe_imageUrl);
            mInputRecipeDescription = (EditText) view.findViewById(R.id.input_recipe_description);

            mBtnCreateRecipe = (Button) view.findViewById(R.id.btn_create_recipe);
            mBtnCreateRecipe.setOnClickListener(mCreateRecipeBtnListener);

            mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            return view;
        }


        @Override
        public void onResume() {
            mPresenter.subscribe(this);
            super.onResume();
        }

        @Override
        public void onPause() {
            mPresenter.unsubscribe();
            super.onPause();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            mPresenter.unsubscribe();
            mPresenter = null;
        }

        @Override
        public void setPresenter(CreateRecipeContracts.Presenter presenter) {
            mPresenter = presenter;
        }
    }
