package com.example.museums.view.fragments.admin.allMuseums;

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

import com.example.museums.API.models.Museum;
import com.example.museums.R;
import com.example.museums.view.services.recyclerViews.MuseumsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class AllMuseums extends Fragment {
    private QueryAllMuseums queryAllMuseums;

    public ProgressBar progressBar;
    private MuseumsRecyclerViewAdapter mAdapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.fragment_admin_all_museums, container, false);
        return rootView;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        recyclerView = getActivity().findViewById(R.id.admin_all_museums_recyclerView);
        progressBar = getActivity().findViewById(R.id.admin_all_museums_progress_bar);
        List<Museum> museums = new ArrayList<>();
        mAdapter = new MuseumsRecyclerViewAdapter(museums, this);
        recyclerView.setAdapter(mAdapter);
        queryAllMuseums = new QueryAllMuseums(this);
        queryAllMuseums.getQuery();
    }


    public void refreshAllList(List<Museum> museums) {
        mAdapter.updateAll(museums);
    }
}
