package com.example.krasimir.fitness_friend.Challanges.ListChallanges;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.krasimir.fitness_friend.R;
import com.example.krasimir.fitness_friend.base.models.Challange;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Krasimir on 10/21/2017.
 */

public class ChallangesAdapter extends RecyclerView.Adapter<ChallangesAdapter.MyViewHolder> {

    private ArrayList<Challange> mChallangesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView ChallangeName;
        public ImageView ChallangeImage;
        public TextView ChallangeType;

        public MyViewHolder(View view) {
            super(view);
            ChallangeName = (TextView) view.findViewById(R.id.challange_name);
            ChallangeImage = view.findViewById(R.id.challange_list_image);
            ChallangeType = (TextView) view.findViewById(R.id.challange_type);
        }
    }

    public ChallangesAdapter(ArrayList<Challange> Challanges) {
        this.mChallangesList = Challanges;
    }

    @Override
    public ChallangesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.challange_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChallangesAdapter.MyViewHolder holder, int position) {
        final Challange Challange = mChallangesList.get(position);

        holder.ChallangeName.setText(Challange.getName());
        Picasso.with(holder.ChallangeImage.getContext()).load(Challange.getImageUrl()).into(holder.ChallangeImage);
        holder.ChallangeType.setText("(" + Challange.getType() + ")");
    }

    @Override
    public int getItemCount() {
        return mChallangesList.size();
    }
}