package com.example.krasimir.fitness_friend.Exercises.ListExercises.ExercisesDetails;

import android.os.Bundle;

import com.example.krasimir.fitness_friend.NavMain.NavMainActivity;
import com.example.krasimir.fitness_friend.R;
import com.example.krasimir.fitness_friend.base.models.Exercise;

import javax.inject.Inject;

public class ExerciseDetailsActivity extends NavMainActivity {

    private Exercise mExercise;

    @Inject
    ExerciseDetailsContracts.Presenter mExerciseDetailsPresenter;

    private ExerciseDetailsView mExerciseDetailsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mExerciseDetailsView = ExerciseDetailsView.newInstance();

        mExercise = (Exercise) getIntent().getParcelableExtra("exercise_details");
        mExerciseDetailsPresenter.setExercise(mExercise);

        mExerciseDetailsView.setPresenter(mExerciseDetailsPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_container, mExerciseDetailsView)
                .commit();
    }

    @Override
    protected void onResume() {
        mExerciseDetailsView.setPresenter(mExerciseDetailsPresenter);
        super.onResume();
    }
}