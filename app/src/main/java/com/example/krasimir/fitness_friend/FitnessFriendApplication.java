package com.example.krasimir.fitness_friend;

import com.example.krasimir.fitness_friend.base.dagger.AppComponent;
import com.example.krasimir.fitness_friend.base.dagger.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by Krasimir on 10/10/2017.
 */

public class FitnessFriendApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }
}
