package com.example.krasimir.fitness_friend.base.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;

import com.example.krasimir.fitness_friend.R;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by Krasimir on 10/22/2017.
 */

public class LoadingIndicator {
    public static void showLoadingIndicator(LoadingView fragment) {

        AVLoadingIndicatorView spinner = new AVLoadingIndicatorView(fragment.getContext());
        spinner.setBackgroundColor(ContextCompat.getColor(fragment.getContext(), R.color.primary));
        spinner.setIndicator("BallTrianglePathIndicator");
        spinner.setPadding(0, 200, 0, 0);
        spinner.show();

        ViewGroup.LayoutParams layoutParams =
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                );

        spinner.setLayoutParams(layoutParams);

        fragment.getLoadingContainer().removeAllViews();
        fragment.getLoadingContainer().addView(spinner);

        fragment.getLoadingContainer().setVisibility(View.VISIBLE);
        fragment.getContentContainer().setVisibility(View.GONE);
    }

    public static void hideLoadingIndicator(LoadingView fragment) {
        fragment.getLoadingContainer().setVisibility(View.GONE);
        fragment.getContentContainer().setVisibility(View.VISIBLE);
    }

    public interface LoadingView {
        View getContentContainer();

        ViewGroup getLoadingContainer();

        Context getContext();

        void showLoading();

        void hideLoading();
    }
}