package com.example.museums.services;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class MyGestureListener implements GestureDetector.OnGestureListener  {
    @Override
    public boolean onDown(MotionEvent e) {
        System.out.println("0");

        return false;
    }




    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        System.out.println("1");

        if (Math.abs(velocityX) > Math.abs(velocityY)) {
            return true;
        }
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        System.out.println("2");

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        System.out.println("3");

        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        System.out.println("4");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        System.out.println("5");

    }
}