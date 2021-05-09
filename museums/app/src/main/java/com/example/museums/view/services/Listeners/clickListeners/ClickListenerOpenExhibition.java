package com.example.museums.view.services.Listeners.clickListeners;

import android.os.Build;
import android.os.CountDownTimer;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.museums.API.models.exhibition.ExistingExhibition;
import com.example.museums.view.activities.tabs.MuseumTab;
import com.example.museums.view.fragments.common.detailedExhibition.DetailedExhibition;
import com.example.museums.view.services.MethodsWithFragment;
import com.example.museums.view.services.Timers.CountDownTimerHideInfo;

public class ClickListenerOpenExhibition implements View.OnClickListener {
    private View view;
    private ExistingExhibition exhibitionWithMuseumName;
    private MethodsWithFragment mth = new MethodsWithFragment();
    private CountDownTimer ctimte = null;

    public ClickListenerOpenExhibition(View view, ExistingExhibition exhibitionWithMuseumName) {
        this.view = view;
        this.exhibitionWithMuseumName = exhibitionWithMuseumName;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onClick(View v) {
        Fragment myFragment;
        if (view.getVisibility() == View.VISIBLE) {
            if (exhibitionWithMuseumName.getFirstDate().isEmpty()) {
                myFragment = new DetailedExhibition().newInstance(exhibitionWithMuseumName.getId(), exhibitionWithMuseumName.getMuseum().getId(), -1, exhibitionWithMuseumName.getImageUrl(), exhibitionWithMuseumName.getName(),
                        "", exhibitionWithMuseumName.getDescription());
            } else {
                myFragment = new DetailedExhibition().newInstance(exhibitionWithMuseumName.getId(), exhibitionWithMuseumName.getMuseum().getId(), -1, exhibitionWithMuseumName.getImageUrl(), exhibitionWithMuseumName.getName(),
                        exhibitionWithMuseumName.getFirstDate() + " - " + exhibitionWithMuseumName.getLastDate(), exhibitionWithMuseumName.getDescription());
            }
            MuseumTab activity = (MuseumTab) v.getContext();
            mth.replaceFragment(myFragment, activity);
        } else {
            ctimte = new CountDownTimerHideInfo(3000, 3000, view);
            ctimte.start();
        }
    }
}
