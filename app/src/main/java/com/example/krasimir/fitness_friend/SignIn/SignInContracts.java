package com.example.krasimir.fitness_friend.SignIn;

import com.example.krasimir.fitness_friend.base.authentication.AuthenticationProvider;
import com.example.krasimir.fitness_friend.base.contracts.BaseContracts;

import io.reactivex.Observable;

/**
 * Created by Krasimir on 10/10/2017.
 */

public interface SignInContracts {

    interface View extends BaseContracts.View<Presenter> {

    }

    interface Presenter extends BaseContracts.Presenter<View> {

        Observable<Boolean> signInWithEmail(String email, String password);

        void setAuth(AuthenticationProvider mAuthProvider);
    }
}
