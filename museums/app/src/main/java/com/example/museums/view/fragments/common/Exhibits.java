package com.example.museums.view.fragments.common;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.R;
import com.example.museums.view.services.recyclerViews.ExhibitsRecyclerViewAdapter;
import com.example.museums.view.services.recyclerViews.TagsRecyclerViewAdapter;
import com.example.museums.API.models.Exhibit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exhibits extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter horizontalAdapter;
    public RecyclerView listView;
    public static final String LOGIN_KEY_USER = "login_key";
    private String login;
    List<String> names = Arrays.asList("Природа", "Скульптура", "Графика", "Животные", "Живопись", "Люди");


    public Exhibits newInstance(String login) {
        final Exhibits myFragment = new Exhibits();
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
                inflater.inflate(R.layout.fragment_main_exhibits, container, false);
        getArgumentsFromBundle();
         return rootView;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view_exhibits);
        listView = (RecyclerView) getActivity().findViewById(R.id.main_exhibits_recycler_view);

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
        mAdapter = new ExhibitsRecyclerViewAdapter(in);
        recyclerView.setAdapter(mAdapter);

        horizontalAdapter = new TagsRecyclerViewAdapter(names);
        listView.setAdapter(horizontalAdapter);

    }
}
