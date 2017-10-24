package com.example.krasimir.fitness_friend.Challanges.ChallangesDetails;

import com.example.krasimir.fitness_friend.base.authentication.AuthenticationProvider;
import com.example.krasimir.fitness_friend.base.data.RemoteChallangesData;
import com.example.krasimir.fitness_friend.base.models.Challange;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Krasimir on 10/22/2017.
 */
@Module
public class ChallangeDetailsModule {
    @Provides
    ChallangeDetailsContracts.Presenter provideChallangeDetailsPresenter(AuthenticationProvider authProvider, RemoteChallangesData<Challange> data) {
        return new ChallangeDetailsPresenter(authProvider, data);
    }
}
