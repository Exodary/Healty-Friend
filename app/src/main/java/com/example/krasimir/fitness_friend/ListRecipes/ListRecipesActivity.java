package com.example.krasimir.fitness_friend.ListRecipes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.krasimir.fitness_friend.NavMain.NavMainActivity;
import com.example.krasimir.fitness_friend.R;

import javax.inject.Inject;

public class ListRecipesActivity extends NavMainActivity {

    @Inject
    ListRecipesContracts.Presenter mListRecipesPresenter;

    private ListRecipesView mListRecipesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListRecipesView = ListRecipesView.newInstance();

        mListRecipesView.setPresenter(mListRecipesPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_container, mListRecipesView)
                .commit();
    }
}

