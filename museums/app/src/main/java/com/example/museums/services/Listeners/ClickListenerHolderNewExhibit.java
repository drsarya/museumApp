package com.example.museums.services.Listeners;

import android.os.CountDownTimer;
import android.view.View;

import com.example.museums.services.Timers.CountDownTimerHideInfo;
import com.example.museums.services.recyclerViews.NewExhibitsRecyclerViewAdapter;

public class ClickListenerHolderNewExhibit implements View.OnClickListener {
    public ClickListenerHolderNewExhibit(NewExhibitsRecyclerViewAdapter.NewExhibitsViewHolder holder) {
        this.holder = holder;
    }

    private CountDownTimer ctimte = null;
    private NewExhibitsRecyclerViewAdapter.NewExhibitsViewHolder holder;

    @Override
    public void onClick(View v) {
        ctimte = new CountDownTimerHideInfo(3000, 3000, holder.optionalPanel);
        ctimte.start();
    }

}