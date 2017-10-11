package com.example.krasimir.fitness_friend.UserProfile;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.widget.Toast;

import com.example.krasimir.fitness_friend.NavMain.NavMainActivity;
import com.example.krasimir.fitness_friend.R;
import com.example.krasimir.fitness_friend.SignIn.SignInActivity;
import com.example.krasimir.fitness_friend.base.authentication.AuthenticationProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;

import javax.inject.Inject;


public class UserProfileActivity extends NavMainActivity {

    @Inject
    UserProfileContracts.Presenter mPresenter;

    @Inject
    AuthenticationProvider mAuthProvider;

    private UserBasicProfileView mUserBasicProfileView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mPresenter.setAuth(mAuthProvider);

                mUserBasicProfileView = UserBasicProfileView.newInstance();
                mUserBasicProfileView.setPresenter(mPresenter);

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_container, mUserBasicProfileView)
                        .commit();
    }
}