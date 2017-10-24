package com.example.krasimir.fitness_friend.SignUp;

import android.os.Bundle;

import com.example.krasimir.fitness_friend.NavMain.NavMainActivity;
import com.example.krasimir.fitness_friend.R;
import com.example.krasimir.fitness_friend.base.authentication.AuthenticationProvider;

import javax.inject.Inject;


public class SignUpActivity extends NavMainActivity {

    private SignUpView mView;

    @Inject
    public SignUpContracts.Presenter mPresenter;

    @Inject
    AuthenticationProvider mAuthProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mView = SignUpView.newInstance();

        mView.setPresenter(mPresenter);

        mAuthProvider.setActivity(this);
        mPresenter.setAuth(mAuthProvider);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_container, mView)
                .commit();
    }
}