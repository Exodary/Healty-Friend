package com.example.krasimir.fitness_friend.SignIn;

import com.example.krasimir.fitness_friend.base.authentication.AuthenticationProvider;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Krasimir on 10/10/2017.
 */

class SignInPresenter implements SignInContracts.Presenter {

    private SignInContracts.View mView;
    private AuthenticationProvider mAuthenticationProvider;

    @Inject
    public SignInPresenter() {
    }

    @Override
    public void subscribe(SignInContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void setAuth(AuthenticationProvider authProvider) {
        mAuthenticationProvider = authProvider;
    }

    @Override
    public Observable<Boolean> signInWithEmail(String email, String password) {
        return mAuthenticationProvider.signInWithEmail(email, password);
    }
}
