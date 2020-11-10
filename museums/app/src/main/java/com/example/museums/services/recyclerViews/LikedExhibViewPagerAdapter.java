package com.example.museums.services.recyclerViews;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.museums.fragments.museum.Exhibitions;
import com.example.museums.fragments.museum.Exhibits;
import com.example.services.models.Exhibit;
import com.example.services.models.Exhibition;

import java.util.List;

public class LikedExhibViewPagerAdapter extends FragmentStatePagerAdapter {
    public LikedExhibViewPagerAdapter(@NonNull FragmentManager fm, List<Exhibit> mDataset, List<Exhibition> mExhbn) {
        super(fm);
        this.mDataset = mDataset;
        this.mExhbn = mExhbn;
    }

    private List<Exhibition> mExhbn;
    private List<Exhibit> mDataset;

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Fragment fragment;
        if (position == 0) {
            fragment = new Exhibits();

        } else {
            fragment = new Exhibitions();

        }


        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
