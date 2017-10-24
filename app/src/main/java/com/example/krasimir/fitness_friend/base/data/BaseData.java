package com.example.krasimir.fitness_friend.base.data;

import com.example.krasimir.fitness_friend.base.models.Exercise;
import com.example.krasimir.fitness_friend.base.models.Recipe;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Krasimir on 10/10/2017.
 */

public abstract class BaseData<T> {

    public abstract Observable<List<T>> getAll();

    public abstract void add(T item);

}