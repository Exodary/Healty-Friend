package com.example.krasimir.fitness_friend.ListRecipes;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.krasimir.fitness_friend.R;
import com.example.krasimir.fitness_friend.RecipeDetails.RecipeDetailsActivity;
import com.example.krasimir.fitness_friend.base.models.Recipe;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListRecipesView extends Fragment implements ListRecipesContracts.View{

    private ArrayList<Recipe> mRecipesList;
    private RecyclerView mRecyclerView;
    private RecipesAdapter mAdapter;
    private ListRecipesContracts.Presenter mPresenter;

    public ListRecipesView() {
        // Required empty public constructor
    }

    public static ListRecipesView newInstance() {
        return new ListRecipesView();
    }

    @Override
    public void setPresenter(ListRecipesContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_recipes, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recipes_list);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        mRecipesList = new ArrayList<>();
        mAdapter = new RecipesAdapter(mRecipesList);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemListener(getActivity().getApplicationContext(), mRecyclerView,
                new RecyclerItemListener.RecyclerTouchListener() {
                    public void onClickItem(View v, int position) {
                        Intent intent = new Intent(getActivity(), RecipeDetailsActivity.class);
                        intent.putExtra("recipe_details", mRecipesList.get(position));
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
        mPresenter.unsubscribe();
        mPresenter = null;
    }

    @Override
    public void setRecipes(ArrayList<Recipe> recipes) {
        mRecipesList.clear();
        mRecipesList.addAll(recipes);
        mAdapter.notifyDataSetChanged();
    }
}