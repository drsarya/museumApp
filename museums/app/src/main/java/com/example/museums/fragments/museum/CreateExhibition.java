package com.example.museums.fragments.museum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.R;
import com.example.museums.activities.MuseumTab;
import com.example.museums.services.MethodsWithFragment;
import com.example.museums.services.recyclerViews.ExhibitsRecyclerViewAdapter;
import com.example.museums.services.recyclerViews.NewExhibitsRecyclerViewAdapter;
import com.example.services.models.Exhibit;

import java.util.ArrayList;
import java.util.List;

public class CreateExhibition extends Fragment {
    private RecyclerView recyclerView;
    private ImageButton plusExhbt;
    private MethodsWithFragment mth = new MethodsWithFragment();
    private RecyclerView.Adapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_create_exhibition, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        plusExhbt = (ImageButton) getActivity().findViewById(R.id.create_new_exhibition_image_btn);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.create_exhibition_recycler_view);
        List<Exhibit> in = new ArrayList<>();
        in.add(new Exhibit());
        in.add(new Exhibit());
        in.add(new Exhibit());
        in.add(new Exhibit());
        in.add(new Exhibit());
        in.add(new Exhibit());
        plusExhbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment myFragment = new CreateExhibit();
                MuseumTab activity = (MuseumTab) v.getContext();
                mth.replaceFragment(myFragment, v, activity, R.id.container_tab_museum);
            }
        });

        mAdapter = new NewExhibitsRecyclerViewAdapter(in);
        recyclerView.setAdapter(mAdapter);
    }
}
