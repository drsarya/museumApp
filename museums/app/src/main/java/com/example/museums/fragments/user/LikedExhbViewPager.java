package com.example.museums.fragments.user;

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
import com.example.museums.services.recyclerViews.LikedExhibViewPagerAdapter;
import com.example.services.models.Exhibit;
import com.example.services.models.Exhibition;

import java.util.List;

public class LikedExhbViewPager extends Fragment {

    private ViewPager mPager;
    private List<Exhibit> mDataset;
    private List<Exhibition> mExhbn;

    private PagerAdapter pagerAdapter;

    public LikedExhbViewPager(List<Exhibit> mDataset, List<Exhibition> mExhbn) {
        this.mDataset = mDataset;
        this.mExhbn = mExhbn;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.setRetainInstance(true);
        View rootView =
                inflater.inflate(R.layout.fragment_screen_slide_liked_user, container, false);
        return rootView;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        super.setRetainInstance(true);


        mPager = (ViewPager) getActivity().findViewById(R.id.exhib_screen_slide_liked_user);
        pagerAdapter = new LikedExhibViewPagerAdapter(getChildFragmentManager(), mDataset, mExhbn);
        mPager.setAdapter(pagerAdapter);

    }
}
