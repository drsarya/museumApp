package com.example.museums.view.services.Listeners.clickListeners;

import android.os.Build;
import android.os.CountDownTimer;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.museums.view.activities.tabs.MuseumTab;
import com.example.museums.view.fragments.common.DetailedExhibitWithListeners;
import com.example.museums.view.services.MethodsWithFragment;
import com.example.museums.view.services.Timers.CountDownTimerHideInfo;
import com.example.museums.view.services.recyclerViews.NewExhibitsRecyclerViewAdapter;

public class ClickListenerHolderNewExhibit implements View.OnClickListener {
    public ClickListenerHolderNewExhibit(View view) {
        this.view = view;
    }

    private CountDownTimer ctimte = null;
    private View view;
    private MethodsWithFragment mth = new MethodsWithFragment();

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onClick(View v) {
        if ( view.getVisibility() == View.VISIBLE) {
            Fragment myFragment = new DetailedExhibitWithListeners();
            MuseumTab activity = (MuseumTab) v.getContext();
            mth.replaceFragment(myFragment, v, activity);
        } else {
            ctimte = new CountDownTimerHideInfo(3000, 3000, view);
            ctimte.start();
        }

    }

}