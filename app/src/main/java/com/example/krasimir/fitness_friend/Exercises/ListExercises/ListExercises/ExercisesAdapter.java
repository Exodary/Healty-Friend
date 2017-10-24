package com.example.krasimir.fitness_friend.Exercises.ListExercises.ListExercises;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.krasimir.fitness_friend.R;
import com.example.krasimir.fitness_friend.base.models.Exercise;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Krasimir on 10/21/2017.
 */

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.MyViewHolder> {

    private ArrayList<Exercise> mExercisesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView ExerciseName;
        public ImageView ExerciseImage;
        public TextView ExerciseDescription;

        public MyViewHolder(View view) {
            super(view);
            ExerciseName = (TextView) view.findViewById(R.id.exercise_name);
            ExerciseImage = view.findViewById(R.id.exercise_list_image);
            ExerciseDescription = (TextView) view.findViewById(R.id.exercise_list_description);
        }
    }

    public ExercisesAdapter(ArrayList<Exercise> Exercises) {
        this.mExercisesList = Exercises;
    }

    @Override
    public ExercisesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExercisesAdapter.MyViewHolder holder, int position) {
        final Exercise Exercise = mExercisesList.get(position);

        holder.ExerciseName.setText(Exercise.getName());
        Picasso.with(holder.ExerciseImage.getContext()).load(Exercise.getImageUrl()).into(holder.ExerciseImage);
        holder.ExerciseDescription.setText(Exercise.getDescription());
    }

    @Override
    public int getItemCount() {
        return mExercisesList.size();
    }
}
