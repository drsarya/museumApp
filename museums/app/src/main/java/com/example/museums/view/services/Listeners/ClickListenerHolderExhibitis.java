package com.example.museums.view.services.Listeners;

import android.os.Build;
import android.os.CountDownTimer;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.museums.view.fragments.common.DetailedExhibitWithListeners;
import com.example.museums.view.services.MethodsWithFragment;
import com.example.museums.view.services.Timers.CountDownTimerHideInfo;
import com.example.museums.view.services.recyclerViews.ExhibitsRecyclerViewAdapter;


public class ClickListenerHolderExhibitis implements View.OnClickListener {
    public ClickListenerHolderExhibitis(ExhibitsRecyclerViewAdapter.ExhibitsViewHolder holder) {
        this.holder = holder;
    }

    private CountDownTimer ctimte = null;
    private MethodsWithFragment mth = new MethodsWithFragment();
    private ExhibitsRecyclerViewAdapter.ExhibitsViewHolder holder;


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onClick(View v) {
        if (holder.textView.getVisibility() == View.VISIBLE) {
            Fragment myFragment = new DetailedExhibitWithListeners();
            mth.replaceFragment(myFragment, v, (AppCompatActivity) v.getContext());
        } else {
            ctimte = new CountDownTimerHideInfo(3000, 3000, holder.textView);
            ctimte.start();

        }
    }
}
