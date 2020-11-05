package com.example.museums.services.Listeners;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.museums.activities.Authorization;

public class OnTouchListenerGestureTurnBack implements View.OnTouchListener {

    private GestureDetector mDetector;
    public OnTouchListenerGestureTurnBack( GestureDetector mDetector  ) {

        this.mDetector = mDetector;
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {


        boolean b = mDetector.onTouchEvent(event);


        if (b) {


        }
        return mDetector.onTouchEvent(event);

    }
}
