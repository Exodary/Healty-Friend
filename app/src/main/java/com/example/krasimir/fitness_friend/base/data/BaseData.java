package com.example.krasimir.fitness_friend.base.data;

import com.example.krasimir.fitness_friend.base.models.Recipe;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by Krasimir on 10/10/2017.
 */

public abstract class BaseData<T> {

    public abstract Observable<ArrayList<Recipe>> getAll();

    public abstract void add(T item);

}