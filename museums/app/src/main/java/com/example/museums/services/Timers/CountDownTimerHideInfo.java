package com.example.museums.services.Timers;

import android.os.CountDownTimer;
import android.os.Message;

import com.example.museums.services.Handlers.HandlerTimerCountDown;
import com.example.museums.services.recyclerViews.ExhibitsRecyclerViewAdapter;

public class CountDownTimerHideInfo extends CountDownTimer {
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public CountDownTimerHideInfo(long millisInFuture, long countDownInterval, ExhibitsRecyclerViewAdapter.ExhibitsViewHolder holder) {
        super(millisInFuture, countDownInterval);
        this.holder = holder;
    }
   private ExhibitsRecyclerViewAdapter.ExhibitsViewHolder holder;

    @Override
    public void onTick(long millisUntilFinished) {
        HandlerTimerCountDown mHandler = new HandlerTimerCountDown(holder);
        Message message = mHandler.obtainMessage( );
        message.arg1 = 20;
        message.sendToTarget();
    }

    @Override
    public void onFinish() {
        HandlerTimerCountDown  mHandler = new HandlerTimerCountDown(holder);
        Message message = mHandler.obtainMessage( );
        message.arg1 = 50;
        message.sendToTarget();
    }
}
