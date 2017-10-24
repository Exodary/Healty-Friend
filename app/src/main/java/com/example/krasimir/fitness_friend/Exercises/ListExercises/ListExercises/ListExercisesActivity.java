package com.example.krasimir.fitness_friend.Exercises.ListExercises.ListExercises;

import android.os.Bundle;

import com.example.krasimir.fitness_friend.NavMain.NavMainActivity;
import com.example.krasimir.fitness_friend.R;

import javax.inject.Inject;

public class ListExercisesActivity extends NavMainActivity {

    @Inject
    ListExercisesContracts.Presenter mListExercisesPresenter;

    private ListExercisesView mListExercisesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListExercisesView = ListExercisesView.newInstance();

        mListExercisesView.setPresenter(mListExercisesPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_container, mListExercisesView)
                .commit();
    }
}
