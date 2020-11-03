package com.example.museums.services;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.AbsListView;

import java.sql.SQLOutput;

public class MyGestureListenerHideLike extends GestureDetector.SimpleOnGestureListener {



    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (Math.abs(distanceY) > Math.abs(distanceX)) {
            if (distanceY < 0)
                return true;
        }
        return false;
    }
}
