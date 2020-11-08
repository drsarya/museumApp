package com.example.museums.fragments.museum;

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

import com.example.museums.R;
import com.example.museums.services.Listeners.OnToucListenerScrollViewSwipeLeftRight;
import com.example.museums.services.recyclerViews.ExhibitsRecyclerViewAdapter;
import com.example.services.models.Exhibit;

import java.util.ArrayList;
import java.util.List;

public class Exhibits extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    public LinearLayout ll;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_main_exhibits, container, false);
        return rootView;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view_exhibits);
        ll = (LinearLayout) getActivity().findViewById(R.id.main_exhibits_linear_layout);

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
     }
}
