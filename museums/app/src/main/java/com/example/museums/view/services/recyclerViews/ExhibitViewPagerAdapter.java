package com.example.museums.view.services.recyclerViews;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.museums.API.models.NewExhibitModel;
import com.example.museums.view.fragments.common.DetailedExhibitWithoutListeners;
import com.example.museums.API.models.Exhibit;

import java.util.ArrayList;
import java.util.List;

public class ExhibitViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<NewExhibitModel> mDataset;

    public ExhibitViewPagerAdapter(@NonNull FragmentManager fm, List<NewExhibitModel> mDataset) {
        super(fm);
        this.mDataset = mDataset;
    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public Fragment getItem(int position) {
        DetailedExhibitWithoutListeners dtn = new DetailedExhibitWithoutListeners().newInstance(mDataset.get(position).photo,
                mDataset.get(position).name, mDataset.get(position).author, mDataset.get(position).dateOfCreate, mDataset.get(position).description);
        return dtn;
    }

    public void setNewData(List<NewExhibitModel> mDataset) {
        this.mDataset = new ArrayList<>();
        this.mDataset.addAll(mDataset);
        this.notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return mDataset.size();
    }
}
