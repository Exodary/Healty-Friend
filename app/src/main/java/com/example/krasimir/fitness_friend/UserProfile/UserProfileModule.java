package com.example.krasimir.fitness_friend.UserProfile;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Krasimir on 10/10/2017.
 */

@Module
public class UserProfileModule {
    @Provides
    UserProfileContracts.Presenter provideUserProfilePresenter() {
        return new UserProfilePresenter();
    }
}
