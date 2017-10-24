package com.example.krasimir.fitness_friend.Exercises.ListExercises.ListExercises;

import com.example.krasimir.fitness_friend.base.data.RemoteExercisesData;
import com.example.krasimir.fitness_friend.base.models.Exercise;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Krasimir on 10/21/2017.
 */

@Module
public class ListExercisesModule {
    @Provides
    ListExercisesContracts.Presenter provideListExercisesPresenter(RemoteExercisesData<Exercise> data) {
        return new ListExercisesPresenter(data);
    }
}
