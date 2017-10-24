package com.example.krasimir.fitness_friend.Exercises.ListExercises.ExercisesDetails;

import com.example.krasimir.fitness_friend.base.contracts.BaseContracts;
import com.example.krasimir.fitness_friend.base.models.Exercise;

/**
 * Created by Krasimir on 10/21/2017.
 */

public class ExerciseDetailsContracts extends BaseContracts {
    public interface View extends BaseContracts.View<Presenter> {

    }

    public interface Presenter extends BaseContracts.Presenter<View> {

        void setExercise(Exercise exercise);

        Exercise getExercise();

    }
}
