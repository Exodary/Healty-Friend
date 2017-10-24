package com.example.krasimir.fitness_friend.Challanges.ListChallanges;

import com.example.krasimir.fitness_friend.base.data.RemoteChallangesData;
import com.example.krasimir.fitness_friend.base.models.Challange;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Krasimir on 10/21/2017.
 */

public class ListChallangesPresenter implements ListChallangesContracts.Presenter {

    private final RemoteChallangesData<Challange> mRemoteChallangesData;
    private ListChallangesContracts.View mView;

    @Inject
    ListChallangesPresenter(RemoteChallangesData<Challange> data) {
        mRemoteChallangesData = data;
    }

    @Override
    public void load() {
        Observable<List<Challange>> observable = mRemoteChallangesData.getAll();


        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Challange>>() {
                    @Override
                    public void accept(List<Challange> challanges) throws Exception {
                        mView.setChallanges(challanges);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void subscribe(ListChallangesContracts.View view) {
        mView = view;
        load();
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }
}
