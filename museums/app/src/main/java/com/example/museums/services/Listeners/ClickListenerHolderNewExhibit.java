package com.example.museums.services.Listeners;

import android.os.CountDownTimer;
import android.view.View;

import com.example.museums.services.MethodsWithFragment;
import com.example.museums.services.Timers.CountDownTimerHideInfo;
import com.example.museums.services.recyclerViews.NewExhibitsRecyclerViewAdapter;

public class ClickListenerHolderNewExhibit implements View.OnClickListener {
    public ClickListenerHolderNewExhibit(View view) {
        this.view = view;
    }

    private CountDownTimer ctimte = null;
    private MethodsWithFragment mth = new MethodsWithFragment();
    private View view;

    @Override
    public void onClick(View v) {
        System.out.println("");
        ctimte = new CountDownTimerHideInfo(3000, 3000, view);
        ctimte.start();
    }

}