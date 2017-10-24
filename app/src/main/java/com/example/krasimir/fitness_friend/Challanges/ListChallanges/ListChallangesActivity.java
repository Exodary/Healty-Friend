package com.example.krasimir.fitness_friend.Challanges.ListChallanges;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.krasimir.fitness_friend.NavMain.NavMainActivity;
import com.example.krasimir.fitness_friend.R;

import javax.inject.Inject;

public class ListChallangesActivity extends NavMainActivity {

    @Inject
    ListChallangesContracts.Presenter mListChallangesPresenter;

    private ListChallangesView mListChallangesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListChallangesView = ListChallangesView.newInstance();

        mListChallangesView.setPresenter(mListChallangesPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_container, mListChallangesView)
                .commit();
    }
}
