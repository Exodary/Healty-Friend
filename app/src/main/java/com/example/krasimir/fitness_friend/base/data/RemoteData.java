package com.example.krasimir.fitness_friend.base.data;

import com.example.krasimir.fitness_friend.base.models.Recipe;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by Krasimir on 10/10/2017.
 */

public class RemoteData<T> extends BaseData<T> implements ValueEventListener{

    private final DatabaseReference mDatabase;
    private final ArrayList<Recipe> mRecipesList;
    private String mKey;

    @Inject
    RemoteData(){
        mDatabase = FirebaseDatabase.getInstance().getReference("recipes");
        mRecipesList = new ArrayList<Recipe>();
        mDatabase.addValueEventListener(this);
    }

    @Override
    public Observable<ArrayList<Recipe>> getAll() {
        return Observable.create(new ObservableOnSubscribe<ArrayList<Recipe>>() {
            @Override
            public void subscribe(final ObservableEmitter<ArrayList<Recipe>> emitter) throws Exception {
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mRecipesList.clear();
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                            Recipe recipe = postSnapshot.getValue(Recipe.class);
                            mRecipesList.add(recipe);
                        }
                        emitter.onNext(mRecipesList);
                        emitter.onComplete();
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                        mDatabase.removeEventListener(this);
                    }
                });
            }
        });
    }

    public String getKey(){
        mKey = mDatabase.push().getKey();
        return mKey;
    }

    @Override
    public void add(T item) {
        mDatabase.child(mKey).setValue(item);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        mRecipesList.clear();

        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
            Recipe recipe = postSnapshot.getValue(Recipe.class);
            mRecipesList.add(recipe);
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}