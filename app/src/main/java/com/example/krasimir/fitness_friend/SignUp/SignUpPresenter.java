package com.example.krasimir.fitness_friend.SignUp;

import com.example.krasimir.fitness_friend.base.authentication.AuthenticationProvider;
import com.google.android.gms.common.data.DataBufferObserver;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Krasimir on 10/10/2017.
 */

public class SignUpPresenter implements SignUpContracts.Presenter {

    private SignUpContracts.View mView;
    private AuthenticationProvider mAuthenticationProvider;

    @Inject
    public SignUpPresenter() {
    }

    @Override
    public Observable<Boolean> signUpWithEmail(String email, String password, String displayName) {
       return mAuthenticationProvider.signUpWithEmail(email, password, displayName);
    }

    @Override
    public void setAuth(AuthenticationProvider authProvider) {
        mAuthenticationProvider = authProvider;
    }

    @Override
    public void subscribe(SignUpContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }
}
