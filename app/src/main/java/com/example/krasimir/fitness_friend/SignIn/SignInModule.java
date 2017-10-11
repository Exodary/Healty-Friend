package com.example.krasimir.fitness_friend.SignIn;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Krasimir on 10/10/2017.
 */

@Module
public class SignInModule {
    @Provides
    SignInContracts.Presenter provideSignInPresenter() {
        return new SignInPresenter();
    }
}