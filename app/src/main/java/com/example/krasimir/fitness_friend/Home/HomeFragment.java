package com.example.krasimir.fitness_friend.Home;

import android.content.Intent;
import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.TextView;

import com.example.krasimir.fitness_friend.ListRecipes.ListRecipesActivity;
import com.example.krasimir.fitness_friend.R;
import com.google.firebase.auth.FirebaseAuth;


/**
  * A simple {@link Fragment} subclass.
  */

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }
}
