package com.example.krasimir.fitness_friend.Exercises.ListExercises.ListExercises;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krasimir.fitness_friend.Challanges.ListChallanges.RecyclerItemListener;
import com.example.krasimir.fitness_friend.Exercises.ListExercises.ExercisesDetails.ExerciseDetailsActivity;
import com.example.krasimir.fitness_friend.R;
import com.example.krasimir.fitness_friend.base.models.Exercise;
import com.example.krasimir.fitness_friend.base.utils.LoadingIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krasimir on 10/21/2017.
 */

public class ListExercisesView extends Fragment implements ListExercisesContracts.View, LoadingIndicator.LoadingView{

    private ArrayList<Exercise> mExercisesList;
    private RecyclerView mRecyclerView;
    private ExercisesAdapter mAdapter;
    private ListExercisesContracts.Presenter mPresenter;
    private View mLoadingView;

    public ListExercisesView() {
        // Required empty public constructor
    }

    public static ListExercisesView newInstance() {
        return new ListExercisesView();
    }

    @Override
    public void setPresenter(ListExercisesContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_list_exercises, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.exercises_list);

        mLoadingView = view.findViewById(R.id.loading_container1);
        showLoading();


        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        mExercisesList = new ArrayList<>();
        mAdapter = new ExercisesAdapter(mExercisesList);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemListener(getActivity().getApplicationContext(), mRecyclerView,
                new RecyclerItemListener.RecyclerTouchListener() {
                    public void onClickItem(View v, int position) {
                        Intent intent = new Intent(getActivity(), ExerciseDetailsActivity.class);
                        intent.putExtra("exercise_details", mExercisesList.get(position));
                        startActivity(intent);
                    }
                }));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.unsubscribe();
        }
        mPresenter = null;
    }

    @Override
    public void setExercises(List<Exercise> exercises) {
        mExercisesList.clear();
        mExercisesList.addAll(exercises);
        mAdapter.notifyDataSetChanged();
        hideLoading();
    }

    @Override
    public View getContentContainer() {
        return mRecyclerView;
    }

    @Override
    public ViewGroup getLoadingContainer() {
        return (ViewGroup) mLoadingView;
    }

    @Override
    public void showLoading() {
        LoadingIndicator.showLoadingIndicator(ListExercisesView.this);
    }

    @Override
    public void hideLoading() {
        LoadingIndicator.hideLoadingIndicator(ListExercisesView.this);
    }
}
