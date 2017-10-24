package com.example.krasimir.fitness_friend.Challanges.ChallangesDetails;

import com.example.krasimir.fitness_friend.base.authentication.AuthenticationProvider;
import com.example.krasimir.fitness_friend.base.data.RemoteChallangesData;
import com.example.krasimir.fitness_friend.base.models.Challange;

import javax.inject.Inject;

/**
 * Created by Krasimir on 10/22/2017.
 */

public class ChallangeDetailsPresenter implements ChallangeDetailsContracts.Presenter {
    private final AuthenticationProvider mAuthProvider;
    private final RemoteChallangesData<Challange> mRemoteChallangesData;
    private Challange mChallange;
    private ChallangeDetailsContracts.View mView;

    @Inject
    ChallangeDetailsPresenter(AuthenticationProvider authProvider, RemoteChallangesData<Challange> remoteChallangesData) {
        mAuthProvider = authProvider;
        mRemoteChallangesData = remoteChallangesData;
    }

    @Override
    public void subscribe(ChallangeDetailsContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void setChallange(Challange challange) {
        mChallange = challange;
    }

    @Override
    public Challange getChallange() {
        return mChallange;
    }

}
