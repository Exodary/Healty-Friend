package com.example.krasimir.fitness_friend.ListRecipes;

import com.example.krasimir.fitness_friend.base.data.RemoteData;
import com.example.krasimir.fitness_friend.base.models.Recipe;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Krasimir on 10/10/2017.
 */

public class ListRecipesPresenter implements ListRecipesContracts.Presenter {

    private final RemoteData<Recipe> mRemoteData;
    private ListRecipesContracts.View mView;

    @Inject
    ListRecipesPresenter(RemoteData<Recipe> data) {
        mRemoteData = data;
    }

    @Override
    public void load() {
        Observable<ArrayList<Recipe>> observable = mRemoteData.getAll();

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<Recipe>>() {
                    @Override
                    public void accept(ArrayList<Recipe> recipes) throws Exception {
                        mView.setRecipes(recipes);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void subscribe(ListRecipesContracts.View view) {
        mView = view;
        load();
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }
}