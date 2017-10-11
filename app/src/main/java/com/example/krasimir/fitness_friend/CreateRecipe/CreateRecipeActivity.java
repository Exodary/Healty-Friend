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

    private CreateRecipeFragment mCreateEventView;

    @Inject
    CreateRecipeContracts.Presenter mCreateEventPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mCreateEventView = CreateRecipeFragment.newInstance();

        mCreateEventView.setPresenter(mCreateEventPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_container, mCreateEventView)
                .commit();
    }

    @Override
    protected void onResume() {
        mCreateEventView.setPresenter(mCreateEventPresenter);
        super.onResume();
    }
}
