package com.example.museums.view.fragments.common.exhibitFromExhibition;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.museums.API.models.NewExhibitModel;
import com.example.museums.R;
import com.example.museums.view.services.recyclerViews.ExhibitViewPagerAdapter;
import com.example.museums.API.models.Exhibit;

import java.util.ArrayList;
import java.util.List;

public class ExhibitViewPager extends Fragment {
    private ViewPager mPager;
    private List<NewExhibitModel> mDataset;
    private ExhibitViewPagerAdapter pagerAdapter;

    public ExhibitViewPager(List<NewExhibitModel> mDataset) {
        this.mDataset = mDataset;
    }

    public void setNewData(List<NewExhibitModel> mDataset) {

        pagerAdapter.setNewData(mDataset);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.setRetainInstance(true);
        View rootView =
                inflater.inflate(R.layout.fragment_screen_slide_exhibits_from_exhbtn, container, false);
        initViews(rootView);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initViews(View rootView) {
        pagerAdapter = new ExhibitViewPagerAdapter(getChildFragmentManager(), mDataset);

        mPager = rootView.findViewById(R.id.exhibits_from_exhbtn_pager);
        mPager.setAdapter(pagerAdapter);
    }


}
