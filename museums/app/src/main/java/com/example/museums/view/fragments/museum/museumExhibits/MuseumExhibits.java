package com.example.museums.view.fragments.museum.museumExhibits;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.API.models.ExhibitWithAuthor;
import com.example.museums.R;
import com.example.museums.view.services.oop.IDeletePosition;
import com.example.museums.view.services.recyclerViews.MuseumExhibitsRecyclerAdapter;
import java.util.List;

public class MuseumExhibits extends Fragment implements IDeletePosition {
    private RecyclerView recyclerView;
    private MuseumExhibitsRecyclerAdapter mAdapter = new MuseumExhibitsRecyclerAdapter(this);
    public static final String LOGIN_KEY_USER = "login_key";
    private String login;
    public ProgressBar progressBar;
    private QueryDeleteMuseumExhibit queryDeleteMuseumExhibit;
    private static QueryListMuseumExhibits queryListMuseumExhibits;

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
        System.out.println(savedInstanceState);
        initView(rootView);
        return rootView;
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }


    private void initView(View rootView) {

        recyclerView = rootView.findViewById(R.id.museum_exhibits_recycler_view);
        progressBar = rootView.findViewById(R.id.museum_exhibits_recycler_progress_bar);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        queryListMuseumExhibits = new QueryListMuseumExhibits(this);
        queryDeleteMuseumExhibit = new QueryDeleteMuseumExhibit(this);
        queryListMuseumExhibits.getQuery(login);
    }

    public void refreshAllList(List<ExhibitWithAuthor> exhibitWithAuthors) {
        mAdapter.submitList(exhibitWithAuthors);
    }

    public void refreshAllList() {
        queryListMuseumExhibits.getQuery(login);
    }

    @Override
    public void deletePosition(int position, Integer id) {
        queryDeleteMuseumExhibit.getQuery(id);
    }
}
