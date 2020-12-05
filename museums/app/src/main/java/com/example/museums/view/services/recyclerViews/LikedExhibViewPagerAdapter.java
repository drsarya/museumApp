package com.example.museums.view.services.recyclerViews;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.museums.API.models.Exhibit;
import com.example.museums.API.models.Exhibition;
import com.example.museums.view.fragments.user.likedExhibits.LikedExhbtns;
import com.example.museums.view.fragments.user.likedExhibitions.LikedExhibits;

import java.util.List;

public class LikedExhibViewPagerAdapter extends FragmentStatePagerAdapter {
    public LikedExhibViewPagerAdapter(@NonNull FragmentManager fm, Integer userid) {
        super(fm);
        this.userId = userid;
    }

    private Integer userId;


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;

        if (position == 0) {
            fragment = LikedExhibits.newInstance(userId);
            ;

        } else {
            fragment = LikedExhbtns.newInstance(userId);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
