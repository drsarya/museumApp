package com.example.museums.view.fragments.museum.createExhibition;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.API.models.Museum;
import com.example.museums.R;
import com.example.museums.view.activities.tabs.MuseumTab;
import com.example.museums.view.fragments.admin.allMuseums.QueryAllMuseums;
import com.example.museums.view.services.MethodsWithFragment;
import com.example.museums.view.services.recyclerViews.NewExhibitsRecyclerViewAdapter;
import com.example.museums.API.models.Exhibit;

import java.util.ArrayList;
import java.util.List;

public class CreateExhibition extends Fragment {
    private RecyclerView recyclerView;
    private ImageButton plusExhbt;
    private MethodsWithFragment mth = new MethodsWithFragment();
    private NewExhibitsRecyclerViewAdapter mAdapter;
    public static final String LOGIN_USER_KEY = "login_key";

    public  List<NewExhibitModel> exhibits = new ArrayList<NewExhibitModel>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_create_exhibition, container, false);
        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        plusExhbt = (ImageButton) getActivity().findViewById(R.id.create_new_exhibition_image_btn);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.create_exhibition_recycler_view);

        plusExhbt.setOnClickListener(v -> {
            Fragment myFragment = new CreateExhibit();
            myFragment.setTargetFragment(CreateExhibition.this, 0);
            MuseumTab activity = (MuseumTab) v.getContext();
            mth.replaceFragment(myFragment, v, activity);
        });

        mAdapter = new NewExhibitsRecyclerViewAdapter(exhibits, this);
        recyclerView.setAdapter(mAdapter);

    }
public void addNewExhibit(NewExhibitModel exhibit){
    exhibits.add(exhibit);
    mAdapter.updateAll(exhibits);
}
    public void updateExhibit(int poisition, NewExhibitModel exhibit){
        exhibits.remove(poisition);
        exhibits.add(poisition, exhibit);

        mAdapter.updateAll(exhibits);
    }



}
