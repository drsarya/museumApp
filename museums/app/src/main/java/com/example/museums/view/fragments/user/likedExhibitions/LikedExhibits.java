package com.example.museums.view.fragments.user.likedExhibitions;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.API.models.NewExhibitModel;
import com.example.museums.R;
import com.example.museums.view.services.recyclerViews.ExhibitsRecyclerViewAdapter;

import java.util.List;

public class LikedExhibits extends Fragment {

    private RecyclerView recyclerView;
    public static final String LOGIN_KEY_USER = "login_key";

    private ExhibitsRecyclerViewAdapter mAdapter = new ExhibitsRecyclerViewAdapter();
    private Integer userId;

    public static LikedExhibits newInstance(Integer idUser) {
        final LikedExhibits myFragment = new LikedExhibits();
        final Bundle args = new Bundle(1);
        args.putInt(LOGIN_KEY_USER, idUser);
        myFragment.setArguments(args);
        return myFragment;
    }

    private void getArgumentsFromBundle() {
        if (getArguments() != null) {
            userId = getArguments().getInt(LOGIN_KEY_USER);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_exhibits_liked, container, false);
        return rootView;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        getArgumentsFromBundle();
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view_liked_exhibits);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setUserId(userId);
        QueryLikedExhibits queryLikedExhibits = new QueryLikedExhibits(this);
        queryLikedExhibits.getQuery(userId);

    }

    public void refreshAllList(List<NewExhibitModel> exhibitModels) {
        mAdapter.submitList(exhibitModels);
    }
}
