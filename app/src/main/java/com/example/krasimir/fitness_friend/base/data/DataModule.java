package com.example.krasimir.fitness_friend.base.data;

import com.example.krasimir.fitness_friend.base.models.Challange;
import com.example.krasimir.fitness_friend.base.models.Exercise;
import com.example.krasimir.fitness_friend.base.models.Recipe;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Krasimir on 10/10/2017.
 */

@Module
public class DataModule {

    @Provides
    BaseData<Recipe> provideRemoteRecipesData() {
        return new RemoteRecipesData<>();
    }

    @Provides
    BaseData<Exercise> provideRemoteExercisesData() {
        return new RemoteExercisesData<>();
    }

    @Provides
    BaseData<Challange> provideRemoteChallangesData() {
        return new RemoteChallangesData<>();
    }
}