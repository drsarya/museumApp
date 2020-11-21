package com.example.museums.view.fragments.admin;

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
import com.example.museums.API.models.Museum;
import com.example.museums.R;
import com.example.museums.view.services.recyclerViews.ExhibitsRecyclerViewAdapter;
import com.example.museums.view.services.recyclerViews.MuseumsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class AllMuseums extends Fragment {

    private RecyclerView recyclerView;
    private androidx.recyclerview.widget.RecyclerView.Adapter mAdapter;
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
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.admin_all_museums_recyclerView);

        List<Museum> in = new ArrayList<>();
        in.add(new Museum());
        in.add(new Museum());
        in.add(new Museum());
        in.add(new Museum());
        in.add(new Museum());
        in.add(new Museum());
        in.add(new Museum());
        in.add(new Museum());
        in.add(new Museum());
        in.add(new Museum());
        mAdapter = new MuseumsRecyclerViewAdapter(in);
        recyclerView.setAdapter(mAdapter);


    }
}
