package com.example.krasimir.fitness_friend.Challanges.ListChallanges;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Krasimir on 10/21/2017.
 */

public class RecyclerItemListener implements RecyclerView.OnItemTouchListener  {

    private RecyclerItemListener.RecyclerTouchListener mRecyclerListener;
    private GestureDetector gd;

    public interface RecyclerTouchListener {
        public void onClickItem(View v, int position) ;
    }

    public RecyclerItemListener(Context ctx, final RecyclerView rv, final RecyclerItemListener.RecyclerTouchListener listener) {
        this.mRecyclerListener = listener;
        gd = new GestureDetector(ctx,
                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        View v = rv.findChildViewUnder(e.getX(), e.getY());

                        listener.onClickItem(v, rv.getChildAdapterPosition(v));
                        return true;
                    }
                });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        return ( child != null && gd.onTouchEvent(e));
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
