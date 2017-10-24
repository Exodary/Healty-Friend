package com.example.krasimir.fitness_friend.Exercises.ListExercises.ExercisesDetails;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.krasimir.fitness_friend.R;
import com.example.krasimir.fitness_friend.base.models.Exercise;
import com.squareup.picasso.Picasso;

/**
 * Created by Krasimir on 10/21/2017.
 */

public class ExerciseDetailsView extends Fragment implements ExerciseDetailsContracts.View {

    private ExerciseDetailsContracts.Presenter mPresenter;

    private TextView mExerciseName;
    private TextView mExerciseDescription;
    private ImageView mExerciseImage;


    public ExerciseDetailsView() {
        // Required empty public constructor
    }

    public static ExerciseDetailsView newInstance() {
        return new ExerciseDetailsView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_exercise_details, container, false);

        Exercise exercise = mPresenter.getExercise();

        mExerciseName = (TextView) view.findViewById(R.id.exercise_name);
        mExerciseImage = (ImageView) view.findViewById(R.id.exercise_image);
        mExerciseDescription = (TextView) view.findViewById(R.id.exercise_description);

        mExerciseName.setText(exercise.getName());
        Picasso.with(getContext()).load(exercise.getImageUrl()).into(mExerciseImage);
        mExerciseDescription.setText(exercise.getDescription());

        return view;
    }

    @Override
    public void setPresenter(ExerciseDetailsContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        mPresenter.subscribe(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        mPresenter.unsubscribe();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.unsubscribe();
        }
        mPresenter = null;
    }
}
