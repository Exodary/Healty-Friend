package com.example.krasimir.fitness_friend.Challanges.ChallangesDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.krasimir.fitness_friend.Exercises.ListExercises.ExercisesDetails.ExerciseDetailsActivity;
import com.example.krasimir.fitness_friend.Exercises.ListExercises.ListExercises.ListExercisesActivity;
import com.example.krasimir.fitness_friend.R;
import com.example.krasimir.fitness_friend.base.models.Challange;
import com.squareup.picasso.Picasso;

/**
 * Created by Krasimir on 10/22/2017.
 */

public class ChallangeDetailsView extends Fragment implements ChallangeDetailsContracts.View {

    private ChallangeDetailsContracts.Presenter mPresenter;

    private TextView mChallangeName;
    private TextView mChallangeDescription;
    private TextView mChallangeExercises;
    private TextView mChallangeRepetition;
    private ImageView mChallangeImage;
    private Button mChallangeButton;


    public ChallangeDetailsView() {
        // Required empty public constructor
    }

    public static ChallangeDetailsView newInstance() {
        return new ChallangeDetailsView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_challange_details, container, false);

        Challange challange = mPresenter.getChallange();

        mChallangeName = (TextView) view.findViewById(R.id.challange_name);
        mChallangeExercises = (TextView) view.findViewById(R.id.challange_exercises);
        mChallangeRepetition = (TextView) view.findViewById(R.id.challange_repetition);
        mChallangeImage = (ImageView) view.findViewById(R.id.challange_image);
        mChallangeDescription = (TextView) view.findViewById(R.id.challange_description);
        mChallangeButton = (Button) view.findViewById(R.id.challange_button);

        mChallangeName.setText(challange.getName());
        mChallangeExercises.setText("Exercises: " + challange.getExercises());
        mChallangeRepetition.setText("Repeat this workout: " + challange.getRepetition() + " Times");
        Picasso.with(getContext()).load(challange.getImageUrl()).into(mChallangeImage);
        mChallangeDescription.setText(challange.getDescription());

        mChallangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(),ListExercisesActivity.class);
                startActivity(i);
            }
        });

        return view;
    }

    @Override
    public void setPresenter(ChallangeDetailsContracts.Presenter presenter) {
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
        mPresenter.unsubscribe();
        mPresenter = null;
    }
}
