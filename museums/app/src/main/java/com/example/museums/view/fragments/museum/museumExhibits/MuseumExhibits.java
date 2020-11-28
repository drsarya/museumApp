package com.example.museums.view.fragments.museum.museumExhibits;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.API.models.Exhibit;
import com.example.museums.R;
import com.example.museums.view.fragments.common.Exhibits;
import com.example.museums.view.services.recyclerViews.ExhibitsRecyclerViewAdapter;
import com.example.museums.view.services.recyclerViews.MuseumExhibitsRecyclerAdapter;
import com.example.museums.view.services.recyclerViews.TagsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MuseumExhibits extends Fragment {
    private RecyclerView recyclerView;
    private MuseumExhibitsRecyclerAdapter mAdapter;
     public static final String LOGIN_KEY_USER = "login_key";
    private String login;


    public MuseumExhibits newInstance(String login) {
        final MuseumExhibits myFragment = new MuseumExhibits();
        final Bundle args = new Bundle(1);
        args.putString(LOGIN_KEY_USER, login);
        myFragment.setArguments(args);
        return myFragment;
    }

    private void getArgumentsFromBundle() {
        if (getArguments() != null) {
            login = getArguments().getString(LOGIN_KEY_USER);
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_museum_exhibits, container, false);
        getArgumentsFromBundle();
        return rootView;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.museum_exhibits_recycler_view);

        List<Exhibit> in = new ArrayList<>();
        in.add(new Exhibit());
        in.add(new Exhibit());
        in.add(new Exhibit());
        in.add(new Exhibit());
        in.add(new Exhibit());
        in.add(new Exhibit());
        in.add(new Exhibit());
        in.add(new Exhibit());
        in.add(new Exhibit());
        in.add(new Exhibit());
        mAdapter = new MuseumExhibitsRecyclerAdapter(in);
        recyclerView.setAdapter(mAdapter);



    }
}
