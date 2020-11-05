package com.example.museums.services.Timers;

import android.os.Handler;
import android.os.Message;


import java.util.TimerTask;

public class SplashScreenTimerTask extends TimerTask {

    private Handler mHandler;

    public SplashScreenTimerTask(Handler mHandler) {
        this.mHandler = mHandler;
    }

    private int i = 0;

    @Override
    public void run() {
        i++;
        if (i >= 3) {
            try {
                Thread.sleep(1000);
                Message message = mHandler.obtainMessage();
                message.arg1 = 50;
                message.sendToTarget();
                this.cancel();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            Message message = mHandler.obtainMessage();
            message.sendToTarget();
        }
    }
}
