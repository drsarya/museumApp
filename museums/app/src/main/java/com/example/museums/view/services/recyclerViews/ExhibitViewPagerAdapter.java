package com.example.museums.view.services.recyclerViews;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.museums.view.fragments.common.DetailedExhibitWithoutListeners;
import com.example.museums.API.models.Exhibit;

import java.util.List;

public class ExhibitViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Exhibit> mDataset;
    public ExhibitViewPagerAdapter(@NonNull FragmentManager fm, List<Exhibit> mDataset) {
        super(fm);
        this.mDataset = mDataset;
    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public Fragment getItem(int position) {

        DetailedExhibitWithoutListeners dtn = new DetailedExhibitWithoutListeners();
        Bundle arguments = new Bundle();
        arguments.putString(dtn.PAINT_DESCRIPTIONS, Integer.toString(position) + "название");
        dtn.setArguments(arguments);
        return dtn;
    }


    @Override
    public int getCount() {
        return mDataset.size();
    }
}
