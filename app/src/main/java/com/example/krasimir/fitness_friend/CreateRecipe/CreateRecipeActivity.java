package com.example.krasimir.fitness_friend.CreateRecipe;

import android.os.Bundle;

import com.example.krasimir.fitness_friend.NavMain.NavMainActivity;
import com.example.krasimir.fitness_friend.R;
import com.example.krasimir.fitness_friend.base.models.Recipe;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Inject;

public class CreateRecipeActivity extends NavMainActivity {

    private CreateRecipeFragment mCreateRecipeView;

    @Inject
    CreateRecipeContracts.Presenter mCreateRecipePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mCreateRecipeView = CreateRecipeFragment.newInstance();

        mCreateRecipeView.setPresenter(mCreateRecipePresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_container, mCreateRecipeView)
                .commit();
    }

    @Override
    protected void onResume() {
        mCreateRecipeView.setPresenter(mCreateRecipePresenter);
        super.onResume();
    }
}
