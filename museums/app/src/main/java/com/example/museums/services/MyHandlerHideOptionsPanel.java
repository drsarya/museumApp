package com.example.museums.services;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageSwitcher;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.example.museums.activities.Authorization;
import com.example.museums.activities.SplashScreen;
import com.example.museums.fragments.DetailedExhibit;

public class MyHandlerHideOptionsPanel extends Handler {
    private DetailedExhibit detsc;
    private LinearLayout ll;

    public MyHandlerHideOptionsPanel(DetailedExhibit detsc, LinearLayout ll) {
        this.ll = ll;
        this.detsc = detsc;
    }


    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);

        TranslateAnimation animate = new TranslateAnimation(0, 0, ll.getHeight(), 0);
        animate.setDuration(500);
        animate.setFillAfter(true);
        ll.startAnimation(animate);
        ll.setVisibility(View.VISIBLE);

    }
}
