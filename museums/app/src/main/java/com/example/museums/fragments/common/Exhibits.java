package com.example.museums.fragments.common;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.R;
import com.example.museums.services.Listeners.OnToucListenerScrollViewSwipeLeftRight;
import com.example.museums.services.recyclerViews.ExhibitsRecyclerViewAdapter;
import com.example.museums.services.recyclerViews.TagsRecyclerViewAdapter;
import com.example.services.models.Exhibit;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exhibits extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter horizontalAdapter;
    public LinearLayout ll;
    public RecyclerView listView;
    List<String> names = Arrays.asList("Природа", "Скульптура", "Графика", "Животные", "Живопись", "Люди");


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
