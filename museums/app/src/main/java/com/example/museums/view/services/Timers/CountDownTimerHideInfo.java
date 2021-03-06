package com.example.museums.view.services.Timers;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.example.museums.view.services.Handlers.HandlerTimerCountDown;

public class CountDownTimerHideInfo extends CountDownTimer {

    public CountDownTimerHideInfo(long millisInFuture, long countDownInterval, View view) {
        super(millisInFuture, countDownInterval);
        this.view = view;
    }

    private View view;

    @SuppressLint("HandlerLeak")
    @Override
    public void onTick(long millisUntilFinished) {
        HandlerTimerCountDown mHandler = new HandlerTimerCountDown(view);
        Message message = mHandler.obtainMessage();
        message.arg1 = 20;
        message.sendToTarget();
    }

    @Override
    public void onFinish() {
        HandlerTimerCountDown mHandler = new HandlerTimerCountDown(view);
        Message message = mHandler.obtainMessage();
        message.arg1 = 50;
        message.sendToTarget();
    }
}
