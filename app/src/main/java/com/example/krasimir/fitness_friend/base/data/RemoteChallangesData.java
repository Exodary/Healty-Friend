package com.example.krasimir.fitness_friend.base.data;

import com.example.krasimir.fitness_friend.base.models.Challange;
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

public class RemoteChallangesData<T> extends BaseData<T> implements ValueEventListener {

    private final DatabaseReference mDatabase;
    private final ArrayList<T> mChallangesList;
    private String mKey;

    @Inject
    RemoteChallangesData(){
        mDatabase = FirebaseDatabase.getInstance().getReference("challanges");
        mChallangesList = new ArrayList<>();
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
                        mChallangesList.clear();
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                            Challange challange = postSnapshot.getValue(Challange.class);
                            mChallangesList.add((T) challange);
                        }
                        emitter.onNext(mChallangesList);
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
        mChallangesList.clear();

        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
            Challange challange = postSnapshot.getValue(Challange.class);
            mChallangesList.add((T) challange);
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

}
