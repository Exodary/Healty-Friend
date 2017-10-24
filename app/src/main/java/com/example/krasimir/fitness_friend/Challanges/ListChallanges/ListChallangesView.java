package com.example.krasimir.fitness_friend.Challanges.ListChallanges;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krasimir.fitness_friend.Challanges.ChallangesDetails.ChallangeDetailsActivity;
import com.example.krasimir.fitness_friend.R;
import com.example.krasimir.fitness_friend.base.models.Challange;
import com.example.krasimir.fitness_friend.base.utils.LoadingIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krasimir on 10/21/2017.
 */

public class ListChallangesView extends Fragment implements ListChallangesContracts.View, LoadingIndicator.LoadingView{

    private ArrayList<Challange> mChallangesList;
    private RecyclerView mRecyclerView;
    private ChallangesAdapter mAdapter;
    private ListChallangesContracts.Presenter mPresenter;
    private View mLoadingView;

    public ListChallangesView() {
        // Required empty public constructor
    }

    public static ListChallangesView newInstance() {
        return new ListChallangesView();
    }

    @Override
    public void setPresenter(ListChallangesContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_list_challanges, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.challanges_list);
        mLoadingView = view.findViewById(R.id.loading_container_challanges);

        showLoading();

        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        mChallangesList = new ArrayList<>();
        mAdapter = new ChallangesAdapter(mChallangesList);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemListener(getActivity().getApplicationContext(), mRecyclerView,
                new RecyclerItemListener.RecyclerTouchListener() {
                    public void onClickItem(View v, int position) {
                        Intent intent = new Intent(getActivity(), ChallangeDetailsActivity.class);
                        intent.putExtra("challange_details", mChallangesList.get(position));
                        startActivity(intent);
                    }
                }));

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
        if (mPresenter != null) {
            mPresenter.unsubscribe();
        }
        mPresenter = null;
    }

    @Override
    public void setChallanges(List<Challange> challanges) {
        mChallangesList.clear();
        mChallangesList.addAll(challanges);
        mAdapter.notifyDataSetChanged();
        hideLoading();
    }

    @Override
    public View getContentContainer() {
        return mRecyclerView;
    }

    @Override
    public ViewGroup getLoadingContainer() {
        return (ViewGroup) mLoadingView;
    }

    @Override
    public void showLoading() {
        LoadingIndicator.showLoadingIndicator(ListChallangesView.this);
    }

    @Override
    public void hideLoading() {
        LoadingIndicator.hideLoadingIndicator(ListChallangesView.this);
    }
}
