package com.example.museums.fragments.museum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.R;
import com.example.museums.services.recyclerViews.ExhibitionsRecyclerViewAdapter;
import com.example.services.models.Exhibition;

import java.util.ArrayList;
import java.util.List;

public class Exhibitions extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View rootView =
                inflater.inflate(R.layout.fragment_main_exhibitions, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view_exhibitons);

        List<Exhibition> in = new ArrayList<>();
        in.add(new Exhibition());
        in.add(new Exhibition());
        in.add(new Exhibition());
        in.add(new Exhibition());
        in.add(new Exhibition());
        in.add(new Exhibition());
        in.add(new Exhibition());
        in.add(new Exhibition());
        in.add(new Exhibition());
        in.add(new Exhibition());

        mAdapter = new ExhibitionsRecyclerViewAdapter(in );
        recyclerView.setAdapter(mAdapter);
    }
}
