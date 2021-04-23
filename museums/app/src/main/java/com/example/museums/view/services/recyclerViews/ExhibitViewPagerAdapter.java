package com.example.museums.view.services.recyclerViews;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.view.fragments.common.detailedExhibitWithoutListeners.DetailedExhibitWithoutListenersBackPressed;

import java.util.ArrayList;
import java.util.List;

public class ExhibitViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<ExistingExhibit> mDataSet;
    private Integer userId;

    public ExhibitViewPagerAdapter(@NonNull FragmentManager fm, List<ExistingExhibit> mDataSet, Integer userId) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mDataSet = mDataSet;
        this.userId = userId;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new DetailedExhibitWithoutListenersBackPressed().newInstance(mDataSet.get(position).getId(), userId, mDataSet.get(position).getImageUrl(),
                mDataSet.get(position).getName(), mDataSet.get(position).getAuthor().fullName, mDataSet.get(position).getDateOfCreate(), mDataSet.get(position).getDescription());
    }

    public void setNewData(List<ExistingExhibit> mDataset) {
        this.mDataSet = new ArrayList<>();
        this.mDataSet.addAll(mDataset);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return mDataSet.size();
    }
}
