package com.example.krasimir.fitness_friend.SignUp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Krasimir on 10/10/2017.
 */

@Module
public class SignUpModule {
    @Provides
    SignUpContracts.Presenter provideSignUpPresenter() {
        return new SignUpPresenter();
    }
}
