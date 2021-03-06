package com.example.krasimir.fitness_friend.SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.krasimir.fitness_friend.NavMain.NavMainActivity;
import com.example.krasimir.fitness_friend.R;
import com.example.krasimir.fitness_friend.SignIn.SignInActivity;
import com.example.krasimir.fitness_friend.UserProfile.UserProfileActivity;
import com.example.krasimir.fitness_friend.base.utils.LoadingIndicator;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class SignUpView extends Fragment implements SignUpContracts.View, LoadingIndicator.LoadingView {

    private Button mBtnSignIn;
    private Button mBtnSignUp;
    private EditText mInputEmail;
    private EditText mInputPassword;
    private EditText mInputFirstName;
    private EditText mInputLastName;
    private ProgressBar mProgressBar;

    private SignUpContracts.Presenter mPresenter;

    private Button.OnClickListener mBtnSignInListener = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), SignInActivity.class);
            startActivity(intent);
        }
    };

    private Button.OnClickListener mBtnSignUpListener = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            String email = mInputEmail.getText().toString().trim();
            String password = mInputPassword.getText().toString().trim();
            String firstName = mInputFirstName.getText().toString().trim();
            String lastName = mInputLastName.getText().toString().trim();
            String displayName = firstName + " " + lastName;

            if (TextUtils.isEmpty(email)) {
                mInputEmail.setError("Enter email address!");
            } else if (TextUtils.isEmpty(password)) {
                mInputPassword.setError("Enter password!");
            } else if (password.length() < 6) {
                mInputPassword.setError("Password too short, enter minimum 6 characters!");
            } else {
                showLoading();

                mPresenter
                        .signUpWithEmail(email, password, displayName)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean isSuccessful) throws Exception {
                                if(isSuccessful){
                                    Intent intent = new Intent(getActivity(), NavMainActivity.class);
                                    startActivity(intent);
                                }
                                else{
                                    Intent intent = new Intent(getActivity(), SignUpActivity.class);
                                    startActivity(intent);
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                throwable.printStackTrace();
                            }
                        });
            }
        }
    };

    public SignUpView() {
        // Required empty public constructor
    }


    public static SignUpView newInstance() {
        return new SignUpView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        mInputEmail = (EditText) view.findViewById(R.id.input_email);
        mInputPassword = (EditText) view.findViewById(R.id.input_password);
        mInputFirstName = (EditText) view.findViewById(R.id.input_firstname);
        mInputLastName = (EditText) view.findViewById(R.id.input_lastname);

        mBtnSignIn = (Button) view.findViewById(R.id.btn_sign_in);
        mBtnSignUp = (Button) view.findViewById(R.id.btn_sign_up);

        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        mBtnSignIn.setOnClickListener(mBtnSignInListener);
        mBtnSignUp.setOnClickListener(mBtnSignUpListener);

        return view;
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

    @Override
    public void setPresenter(SignUpContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public View getContentContainer() {
        return getView().findViewById(R.id.content_container);
    }

    @Override
    public ViewGroup getLoadingContainer() {
        return getView().findViewById(R.id.loading_container);
    }

    @Override
    public void showLoading() {
        LoadingIndicator.showLoadingIndicator(SignUpView.this);
    }

    @Override
    public void hideLoading() {
        LoadingIndicator.hideLoadingIndicator(SignUpView.this);
    }
}