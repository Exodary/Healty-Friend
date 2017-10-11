package com.example.krasimir.fitness_friend.base.dagger;

import android.app.Application;

import com.example.krasimir.fitness_friend.FitnessFriendApplication;
import com.example.krasimir.fitness_friend.base.authentication.AuthenticationModule;
import com.example.krasimir.fitness_friend.base.data.DataModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Krasimir on 10/10/2017.
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class,
        AuthenticationModule.class,
        DataModule.class,

})

public interface AppComponent extends AndroidInjector<DaggerApplication> {
    void inject(FitnessFriendApplication application);

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}