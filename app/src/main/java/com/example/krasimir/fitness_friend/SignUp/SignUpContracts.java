package com.example.krasimir.fitness_friend.SignUp;

import com.example.krasimir.fitness_friend.base.authentication.AuthenticationProvider;
import com.example.krasimir.fitness_friend.base.contracts.BaseContracts;

import io.reactivex.Observable;

/**
 * Created by Krasimir on 10/10/2017.
 */

public interface SignUpContracts {
    interface View extends BaseContracts.View<Presenter> {

    }

    interface Presenter extends BaseContracts.Presenter<View> {

        Observable<Boolean> signUpWithEmail(String email, String password, String displayName);

        void setAuth(AuthenticationProvider authProvider);
    }
}
