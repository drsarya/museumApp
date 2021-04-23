package com.example.museums.view.services.Listeners.clickListeners;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.museums.API.models.exhibition.ExistingExhibition;
import com.example.museums.view.fragments.common.detailedExhibition.DetailedExhibition;
import com.example.museums.view.services.MethodsWithFragment;

public class ClickOnListenerHolderExhbtn implements View.OnClickListener {
    private MethodsWithFragment mth = new MethodsWithFragment();
    private ExistingExhibition exhibitionWithMuseumName;
    private Integer userId;

    public ClickOnListenerHolderExhbtn(ExistingExhibition exhibitionWithMuseumName, Integer userId) {
        this.exhibitionWithMuseumName = exhibitionWithMuseumName;
        this.userId = userId;

    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onClick(View v) {
        Fragment myFragment;
        if (exhibitionWithMuseumName.getFirstDate().isEmpty()) {
            myFragment = new DetailedExhibition().newInstance(exhibitionWithMuseumName.getId(),  exhibitionWithMuseumName.getMuseum().getId() , userId, exhibitionWithMuseumName.getImageUrl(), exhibitionWithMuseumName.getName(),
                    "", exhibitionWithMuseumName.getDescription());
        } else {
            myFragment = new DetailedExhibition().newInstance(exhibitionWithMuseumName.getId(),  exhibitionWithMuseumName.getMuseum().getId() , userId, exhibitionWithMuseumName.getImageUrl(), exhibitionWithMuseumName.getName(),
                    exhibitionWithMuseumName.getFirstDate() + " - " + exhibitionWithMuseumName.getLastDate(), exhibitionWithMuseumName.getDescription());
        }

        mth.replaceFragment(myFragment, (AppCompatActivity) v.getContext());
    }
}
