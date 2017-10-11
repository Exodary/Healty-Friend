package com.example.krasimir.fitness_friend.UserProfile;

import com.example.krasimir.fitness_friend.base.authentication.AuthenticationProvider;
import com.example.krasimir.fitness_friend.base.contracts.BaseContracts;

/**
 * Created by Krasimir on 10/10/2017.
 */

public class UserProfileContracts {
    interface View extends BaseContracts.View<Presenter> {

    }

    interface Presenter extends BaseContracts.Presenter<View> {
        void setAuth(AuthenticationProvider authProvider);

        void changeEmail(String newEmail);

        String getUserDisplayName();

        String getUserEmail();
    }
}
