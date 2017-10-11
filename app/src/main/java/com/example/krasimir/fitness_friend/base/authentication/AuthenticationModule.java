package com.example.krasimir.fitness_friend.base.authentication;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Krasimir on 10/10/2017.
 */

@Module
public class AuthenticationModule {
    @Provides
    AuthenticationProvider providesAuthentication(Context context) {
        return new AuthenticationProvider(context);
    }
}
