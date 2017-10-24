package com.example.krasimir.fitness_friend.base.data;

import com.example.krasimir.fitness_friend.base.models.Exercise;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by Krasimir on 10/21/2017.
 */

public class RemoteExercisesData <T> extends BaseData<T> implements ValueEventListener {

    private final DatabaseReference mDatabase;
    private final ArrayList<T> mExercisesList;
    private String mKey;

    @Inject
    RemoteExercisesData(){
        mDatabase = FirebaseDatabase.getInstance().getReference("exercises");
        mExercisesList = new ArrayList<>();
        mDatabase.addValueEventListener(this);
    }

    @Override
    public Observable<List<T>> getAll() {
        return Observable.create(new ObservableOnSubscribe<List<T>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<T>> emitter) throws Exception {
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mExercisesList.clear();
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                            Exercise exercise = postSnapshot.getValue(Exercise.class);
                            mExercisesList.add((T) exercise);
                        }
                        emitter.onNext(mExercisesList);
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
        mExercisesList.clear();

        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
            Exercise exercise = postSnapshot.getValue(Exercise.class);
            mExercisesList.add((T) exercise);
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
