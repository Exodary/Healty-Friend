package com.example.krasimir.fitness_friend.UserProfile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.krasimir.fitness_friend.NavMain.NavMainActivity;
import com.example.krasimir.fitness_friend.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserBasicProfileView extends Fragment implements UserProfileContracts.View {

    private Button changeEmailBtnTrigger;
    private Button btnChangeEmail;

    private EditText newEmail;
    private ProgressBar progressBar;

    private Button.OnClickListener btnChangeEmailListener = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            progressBar.setVisibility(View.VISIBLE);
            String email = newEmail.getText().toString();
            if (email.trim().equals("")) {
                newEmail.setError("Enter email!");
            } else {
                mPresenter.changeEmail(email);
            }
        }
    };


    private Button.OnClickListener changeEmailBtnTriggerListener = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            newEmail.setVisibility(View.VISIBLE);
            btnChangeEmail.setVisibility(View.VISIBLE);
        }
    };

    private UserProfileContracts.Presenter mPresenter;
    private TextView mDisplayName;
    private TextView mUserEmail;

    public UserBasicProfileView() {
        // Required empty public constructor
    }

    public static UserBasicProfileView newInstance() {
        return new UserBasicProfileView();
    }

    @Override
    public void setPresenter(UserProfileContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String displayName = mPresenter.getUserDisplayName();
        String userEmail = mPresenter.getUserEmail();

        View view = inflater.inflate(R.layout.fragment_user_basic_profile, container, false);

        mDisplayName = (TextView) view.findViewById(R.id.diplay_name);
        mUserEmail = (TextView) view.findViewById(R.id.user_email);

        mDisplayName.setText("Display Name: " + displayName);
        mUserEmail.setText("Your Email: " + userEmail);

        changeEmailBtnTrigger = (Button) view.findViewById(R.id.change_email_button);
        changeEmailBtnTrigger.setOnClickListener(changeEmailBtnTriggerListener);

        btnChangeEmail = (Button) view.findViewById(R.id.changeEmail);
        btnChangeEmail.setOnClickListener(btnChangeEmailListener);

        newEmail = (EditText) view.findViewById(R.id.new_email);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        setupUI();

        return view;
    }

    public void setupUI() {
        newEmail.setVisibility(View.GONE);
        btnChangeEmail.setVisibility(View.GONE);
    }
    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
        mPresenter = null;
    }
}