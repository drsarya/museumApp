package com.example.museums.services.recyclerViews;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.museums.R;
import com.example.museums.fragments.DetailedExhibitWithoutListeners;
import com.example.services.models.Exhibit;

import java.util.List;

public class ExhibitViewPagerAdapter extends FragmentStatePagerAdapter  {

    private List<Exhibit> mDataset;


    public ExhibitViewPagerAdapter(@NonNull FragmentManager fm, List<Exhibit> mDataset) {
        super(fm);
        this.mDataset =mDataset;
    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public Fragment getItem(int position) {

        DetailedExhibitWithoutListeners dtn = new DetailedExhibitWithoutListeners();
        Bundle arguments = new Bundle();
        arguments.putString(dtn.PAINT_DESCRIPTIONS, Integer.toString(position)+"название");

    //    arguments.putString(dtn.PAINT_DESCRIPTIONS, mDataset.get(position) );


        dtn.setArguments(arguments);


         return dtn;
    }


    @Override
    public int getCount() {
        return mDataset.size();
    }
}
