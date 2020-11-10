package com.example.museums.services.Listeners;

import android.os.Build;
import android.os.CountDownTimer;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.museums.activities.MuseumTab;
import com.example.museums.fragments.DetailedExhibitWithListeners;
import com.example.museums.services.MethodsWithFragment;
import com.example.museums.services.Timers.CountDownTimerHideInfo;
import com.example.museums.services.recyclerViews.NewExhibitsRecyclerViewAdapter;

public class ClickListenerHolderNewExhibit implements View.OnClickListener {
    public ClickListenerHolderNewExhibit(NewExhibitsRecyclerViewAdapter.NewExhibitsViewHolder holder) {
        this.holder = holder;
    }

    private CountDownTimer ctimte = null;
    private NewExhibitsRecyclerViewAdapter.NewExhibitsViewHolder holder;
    private MethodsWithFragment mth = new MethodsWithFragment();

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onClick(View v) {
        if (holder.optionalPanel.getVisibility() == View.VISIBLE) {
            Fragment myFragment = new DetailedExhibitWithListeners();
            MuseumTab activity = (MuseumTab) v.getContext();
            mth.replaceFragment(myFragment, v, activity);
        } else {
            ctimte = new CountDownTimerHideInfo(3000, 3000, holder.optionalPanel);
            ctimte.start();
        }

    }

}