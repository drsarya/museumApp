package com.example.museums.view.fragments.user;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.API.models.Exhibition;
import com.example.museums.R;
import com.example.museums.view.services.recyclerViews.ExhibitionsRecyclerViewAdapter;
import com.example.museums.view.services.recyclerViews.ExhibitsRecyclerViewAdapter;
import com.example.museums.view.services.recyclerViews.TagsRecyclerViewAdapter;
import com.example.museums.API.models.Exhibit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LikedExhbtns extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_liked_exhibitions, container, false);
        return rootView;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view_liked_exhibitons);

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
