package com.example.krasimir.fitness_friend.base.authentication;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Krasimir on 10/10/2017.
 */

public class AuthenticationProvider implements FirebaseAuth.AuthStateListener {

    private FirebaseUser mUser;
    private FirebaseAuth mAuth;

    private Context mContext;
    private Activity mActivity;

    @Inject
    AuthenticationProvider(Context context) {
        mContext = context;

        mAuth = FirebaseAuth.getInstance();

        mAuth.addAuthStateListener(this);
    }

    public void setActivity(Activity activity) {
        mActivity = activity;
    }

    public FirebaseUser getUser() {
        return mAuth.getCurrentUser();
    }

    public String getUserDisplayName() {
        return mAuth.getCurrentUser().getDisplayName();
    }

    public String getUserEmail() { return mAuth.getCurrentUser().getEmail();}

    public Observable<Boolean> updateProfile(final String displayName) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(final ObservableEmitter<Boolean> emitter) throws Exception {
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest
                        .Builder()
                        .setDisplayName(displayName).build();

                mUser.updateProfile(profileUpdates)
                        .addOnCompleteListener(mActivity, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                emitter.onNext(true);
                            }
                        });
            }
        });
    }

    public Observable<Boolean> signUpWithEmail(final String email, final String password, final String displayName) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(final ObservableEmitter<Boolean> emitter) throws Exception {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(mContext, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                                    updateProfile(displayName)
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new Consumer<Boolean>() {
                                                @Override
                                                public void accept(Boolean isSuccessful) throws Exception {
                                                    if (isSuccessful) {
                                                        emitter.onNext(true);
                                                    }
                                                }
                                            }, new Consumer<Throwable>() {
                                                @Override
                                                public void accept(Throwable throwable) throws Exception {
                                                    throwable.printStackTrace();
                                                }
                                            });

                                } else if (!task.isSuccessful()) {
                                    Toast.makeText(mContext, "Authentication failed." + task.getException(), Toast.LENGTH_LONG).show();
                                    emitter.onNext(false);
                                }
                            }
                        });
            }
        });
    }

    public Observable<Boolean> signInWithEmail(final String email, final String password) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(final ObservableEmitter<Boolean> emitter) throws Exception {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(mContext, "signInWithEmail:success", Toast.LENGTH_SHORT).show();
                                    emitter.onNext(true);
                                } else {
                                    Toast.makeText(mContext, "signInWithEmail:failure", Toast.LENGTH_SHORT).show();
                                    emitter.onNext(false);
                                }
                            }
                        });
            }
        });
    }

    public void changeEmail(String newEmail) {
        mUser.updateEmail(newEmail.trim())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(mContext, "Email address is updated. Please sign in with the new email address!", Toast.LENGTH_LONG).show();
                            signOut();
                        } else {
                            Toast.makeText(mContext, "Failed to update email!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    public void signOut() {
        mAuth.signOut();
        Toast.makeText(mContext, "Signed Out", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        mUser = firebaseAuth.getCurrentUser();
    }
}