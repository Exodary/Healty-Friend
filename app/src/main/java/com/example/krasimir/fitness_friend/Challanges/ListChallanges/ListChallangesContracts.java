package com.example.krasimir.fitness_friend.Challanges.ListChallanges;

import com.example.krasimir.fitness_friend.base.contracts.BaseContracts;
import com.example.krasimir.fitness_friend.base.models.Challange;

import java.util.List;

/**
 * Created by Krasimir on 10/21/2017.
 */

public class ListChallangesContracts  {
    interface View extends BaseContracts.View<Presenter> {

        void setChallanges(List<Challange> challanges);
    }

    interface Presenter extends BaseContracts.Presenter<View> {
        void load();
    }
}
