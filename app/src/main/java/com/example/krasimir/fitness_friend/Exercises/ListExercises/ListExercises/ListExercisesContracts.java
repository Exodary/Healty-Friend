package com.example.krasimir.fitness_friend.Exercises.ListExercises.ListExercises;

import com.example.krasimir.fitness_friend.base.contracts.BaseContracts;
import com.example.krasimir.fitness_friend.base.models.Exercise;

import java.util.List;

/**
 * Created by Krasimir on 10/21/2017.
 */

public interface ListExercisesContracts {
    interface View extends BaseContracts.View<Presenter> {

        void setExercises(List<Exercise> exercises);
    }

    interface Presenter extends BaseContracts.Presenter<View> {
        void load();
    }
}
