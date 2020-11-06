package com.example.museums.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.museums.R;
import com.example.museums.services.recyclerViews.ExhibitViewPagerAdapter;
import com.example.services.models.Exhibit;

import java.util.List;

public class ExhibitViewPager extends Fragment {


    private ViewPager mPager;
    private List<Exhibit> mDataset;
    private PagerAdapter pagerAdapter;

    public ExhibitViewPager(List<Exhibit> mDataset) {
        this.mDataset = mDataset;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.setRetainInstance(true);
        View rootView =
                inflater.inflate(R.layout.activity_screen_slide, container, false);
        return rootView;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        super.setRetainInstance(true);
        mPager = (ViewPager) getActivity().findViewById(R.id.exhibits_from_exhbtn_pager);
        pagerAdapter = new ExhibitViewPagerAdapter(getChildFragmentManager(), mDataset);
        mPager.setAdapter(pagerAdapter);

    }


}
