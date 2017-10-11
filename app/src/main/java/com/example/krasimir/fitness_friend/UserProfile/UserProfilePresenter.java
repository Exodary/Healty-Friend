package com.example.krasimir.fitness_friend.UserProfile;

import com.example.krasimir.fitness_friend.base.authentication.AuthenticationProvider;

import javax.inject.Inject;

/**
 * Created by Krasimir on 10/10/2017.
 */

public class UserProfilePresenter implements UserProfileContracts.Presenter {

    private AuthenticationProvider mAuthProvider;
    private UserProfileContracts.View mView;

    @Inject
    UserProfilePresenter(){
    }

    @Override
    public void setAuth(AuthenticationProvider authProvider) {
        mAuthProvider = authProvider;
    }

    @Override
    public void changeEmail(String newEmail) {
        mAuthProvider.changeEmail(newEmail);
    }

    @Override
    public String getUserDisplayName() {
        return mAuthProvider.getUserDisplayName();
    }

    @Override
    public String getUserEmail() {
        return mAuthProvider.getUserEmail();
    }

    @Override
    public void subscribe(UserProfileContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }
}
