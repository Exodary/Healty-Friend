package com.example.krasimir.fitness_friend.Challanges.ListChallanges;

import com.example.krasimir.fitness_friend.base.data.RemoteChallangesData;
import com.example.krasimir.fitness_friend.base.models.Challange;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Krasimir on 10/21/2017.
 */
@Module
public class ListChallangesModule  {
        @Provides
        ListChallangesContracts.Presenter provideListChallangesPresenter(RemoteChallangesData<Challange> data) {
        return new ListChallangesPresenter(data);
    }
}
