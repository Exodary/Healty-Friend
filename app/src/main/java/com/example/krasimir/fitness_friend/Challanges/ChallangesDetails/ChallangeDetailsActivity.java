package com.example.krasimir.fitness_friend.Challanges.ChallangesDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.krasimir.fitness_friend.NavMain.NavMainActivity;
import com.example.krasimir.fitness_friend.R;
import com.example.krasimir.fitness_friend.base.models.Challange;

import javax.inject.Inject;

public class ChallangeDetailsActivity extends NavMainActivity {

    private Challange mChallange;

    @Inject
    ChallangeDetailsContracts.Presenter mChallangeDetailsPresenter;

    private ChallangeDetailsView mChallangeDetailsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChallangeDetailsView = ChallangeDetailsView.newInstance();

        mChallange = (Challange) getIntent().getParcelableExtra("challange_details");
        mChallangeDetailsPresenter.setChallange(mChallange);

        mChallangeDetailsView.setPresenter(mChallangeDetailsPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_container, mChallangeDetailsView)
                .commit();
    }

    @Override
    protected void onResume() {
        mChallangeDetailsView.setPresenter(mChallangeDetailsPresenter);
        super.onResume();
    }
}
