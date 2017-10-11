package com.example.krasimir.fitness_friend.base.dagger;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Krasimir on 10/10/2017.
 */

@Module
public abstract class ApplicationModule {
    /**
     * Binding the application context
     * @param application the application
     * @return the ApplicationContext
     */
    @Binds
    abstract Context bindContext(Application application);
}
