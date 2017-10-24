package com.example.krasimir.fitness_friend.Challanges.ChallangesDetails;

import com.example.krasimir.fitness_friend.base.contracts.BaseContracts;
import com.example.krasimir.fitness_friend.base.models.Challange;

/**
 * Created by Krasimir on 10/22/2017.
 */

public class ChallangeDetailsContracts extends BaseContracts {
        interface View extends BaseContracts.View<Presenter> {

        }

        interface Presenter extends BaseContracts.Presenter<View> {

            void setChallange(Challange challange);

            Challange getChallange();

        }
}
