package com.example.museums.services.Listeners;

import android.view.View;

import androidx.fragment.app.FragmentActivity;

public class ClickListenerTurnBack implements View.OnClickListener {
    private FragmentActivity activity;

    public ClickListenerTurnBack(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {

        activity.onBackPressed();
    }
}
