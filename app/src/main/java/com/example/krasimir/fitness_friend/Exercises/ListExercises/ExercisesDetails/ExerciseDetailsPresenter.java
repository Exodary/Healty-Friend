package com.example.krasimir.fitness_friend.Exercises.ListExercises.ExercisesDetails;

import com.example.krasimir.fitness_friend.base.authentication.AuthenticationProvider;
import com.example.krasimir.fitness_friend.base.data.RemoteExercisesData;
import com.example.krasimir.fitness_friend.base.models.Exercise;

import javax.inject.Inject;

/**
 * Created by Krasimir on 10/21/2017.
 */

public class ExerciseDetailsPresenter implements ExerciseDetailsContracts.Presenter {
    private final AuthenticationProvider mAuthProvider;
    private final RemoteExercisesData<Exercise> mRemoteExercisesData;
    private Exercise mExercise;
    private ExerciseDetailsContracts.View mView;

    @Inject
    ExerciseDetailsPresenter(AuthenticationProvider authProvider, RemoteExercisesData<Exercise> remoteExercisesData){
        mAuthProvider = authProvider;
        mRemoteExercisesData = remoteExercisesData;
    }

    @Override
    public void subscribe(ExerciseDetailsContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void setExercise(Exercise exercise){
        mExercise = exercise;
    }

    @Override
    public Exercise getExercise(){
        return mExercise;
    }

}
