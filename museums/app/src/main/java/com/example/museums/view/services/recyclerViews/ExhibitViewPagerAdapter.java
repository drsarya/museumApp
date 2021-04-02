package com.example.museums.view.services.recyclerViews;

import android.annotation.SuppressLint;

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
        super(fm);
        this.mDataSet = mDataSet;
        this.userId = userId;
    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public Fragment getItem(int position) {
        DetailedExhibitWithoutListenersBackPressed dtn = new DetailedExhibitWithoutListenersBackPressed().newInstance(mDataSet.get(position).getId(), userId, mDataSet.get(position).getImageUrl(),
                mDataSet.get(position).getName(), mDataSet.get(position).getAuthor().fullName, mDataSet.get(position).getDateOfCreate(), mDataSet.get(position).getDescription());
        return dtn;
    }

    public void setNewData(List<ExistingExhibit> mDataset) {
        this.mDataSet = new ArrayList<>();
        this.mDataSet.addAll(mDataset);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDataSet.size();
    }
}
