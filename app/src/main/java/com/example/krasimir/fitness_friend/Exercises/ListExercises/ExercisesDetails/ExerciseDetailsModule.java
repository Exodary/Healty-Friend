package com.example.krasimir.fitness_friend.Exercises.ListExercises.ExercisesDetails;

import com.example.krasimir.fitness_friend.base.authentication.AuthenticationProvider;
import com.example.krasimir.fitness_friend.base.data.RemoteExercisesData;
import com.example.krasimir.fitness_friend.base.models.Exercise;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Krasimir on 10/21/2017.
 */

@Module()
public class ExerciseDetailsModule {
    @Provides
    ExerciseDetailsContracts.Presenter provideExerciseDetailsPresenter(AuthenticationProvider authProvider, RemoteExercisesData<Exercise> data) {
        return new ExerciseDetailsPresenter(authProvider, data);
    }
}
