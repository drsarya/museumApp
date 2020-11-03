package com.example.museums.services;

import android.os.Handler;
import android.os.Message;

import java.util.TimerTask;

public class MyTimer extends TimerTask {
    private MyHandlerHideOptionsPanel mHandler;

    public MyTimer(MyHandlerHideOptionsPanel mHandler) {
        this.mHandler = mHandler;
    }

    @Override
    public void run() {



            Message message = mHandler.obtainMessage();
            message.sendToTarget();
            cancel();



    }
}
