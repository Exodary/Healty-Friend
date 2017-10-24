package com.example.krasimir.fitness_friend.Exercises.ListExercises.ListExercises;

import com.example.krasimir.fitness_friend.base.data.RemoteExercisesData;
import com.example.krasimir.fitness_friend.base.models.Exercise;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Krasimir on 10/21/2017.
 */

public class ListExercisesPresenter implements ListExercisesContracts.Presenter {

    private final RemoteExercisesData<Exercise> mRemoteExercisesData;
    private ListExercisesContracts.View mView;

    @Inject
    ListExercisesPresenter(RemoteExercisesData<Exercise> data) {
        mRemoteExercisesData = data;
    }

    @Override
    public void load() {
        Observable<List<Exercise>> observable = mRemoteExercisesData.getAll();


        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Exercise>>() {
                    @Override
                    public void accept(List<Exercise> exercises) throws Exception {
                        mView.setExercises(exercises);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void subscribe(ListExercisesContracts.View view) {
        mView = view;
        load();
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }
}
