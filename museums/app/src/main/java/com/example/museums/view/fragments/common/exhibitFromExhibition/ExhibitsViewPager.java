package com.example.museums.view.fragments.common.exhibitFromExhibition;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.R;
import com.example.museums.view.services.recyclerViews.ExhibitViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ExhibitsViewPager extends Fragment {
    private ViewPager mPager;
    private List<ExistingExhibit> mDataset;
    private ExhibitViewPagerAdapter pagerAdapter;
    private Integer userId;

    public ExhibitsViewPager(List<ExistingExhibit> mDataset, Integer userId) {
        this.mDataset = mDataset;
        this.userId = userId;
    }

    public void setNewData(List<ExistingExhibit> mDataset) {
        //  if(pagerAdapter)
        System.out.println("3333333333333333333");
        if (pagerAdapter != null) {
            pagerAdapter.setNewData(mDataset);
        } else {
            this.mDataset = new ArrayList<>();
            this.mDataset.addAll(mDataset);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.setRetainInstance(true);
        System.out.println("111111111111111111111111");
        View rootView =
                inflater.inflate(R.layout.fragment_screen_slide_exhibits_from_exhbtn, container, false);
        initViews(rootView);
        return rootView;
    }


    private void initViews(View rootView) {
        mPager = rootView.findViewById(R.id.exhibits_from_exhbtn_pager);
       // pagerAdapter = new ExhibitViewPagerAdapter(getChildFragmentManager(), mDataset, userId);
        pagerAdapter = new ExhibitViewPagerAdapter(getChildFragmentManager(), mDataset, userId );

        mPager.setAdapter(pagerAdapter);
        if(!mDataset.isEmpty()){
            pagerAdapter.setNewData(mDataset);
        }
    }
}
