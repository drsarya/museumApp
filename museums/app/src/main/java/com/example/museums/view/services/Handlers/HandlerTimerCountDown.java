package com.example.museums.view.services.Handlers;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

public class HandlerTimerCountDown extends Handler {

    public HandlerTimerCountDown(View view) {
        this.view = view;
    }

    private View view;

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        if (msg.arg1 == 20) {
            view.setVisibility(View.VISIBLE);
        } else if (msg.arg1 == 50) {
            TranslateAnimation animate;
            if (view.getClass().toString().equals(LinearLayout.class.toString())) {
                animate = new TranslateAnimation(0, 0, 0, -view.getHeight());
            } else {
                animate = new TranslateAnimation(0, 0, 0, view.getHeight());
            }
            animate.setDuration(500);
            view.startAnimation(animate);
            view.setVisibility(View.GONE);
        }
    }
}
