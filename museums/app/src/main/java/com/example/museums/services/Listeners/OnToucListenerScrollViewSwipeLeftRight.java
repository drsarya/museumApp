package com.example.museums.services.Listeners;

import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;

public class OnToucListenerScrollViewSwipeLeftRight implements View.OnTouchListener {
    private Float downy = null;
    private FragmentActivity activity;
    private boolean trof;
    private LinearLayout ll;
    private Float downx = null;

    public OnToucListenerScrollViewSwipeLeftRight(FragmentActivity activity, boolean trof) {
        this.activity = activity;
        this.trof = trof;

    }

    public OnToucListenerScrollViewSwipeLeftRight(FragmentActivity activity, LinearLayout ll) {
        this.activity = activity;
        this.trof = trof;
        this.ll = ll;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                downy = event.getY();
                downx = event.getX();
                break;
            }
            case MotionEvent.ACTION_UP: {

                if (downy != null && downx != null) {
                    float upY = event.getY();
                    float upX = event.getX();
                    float deltaY = downy - upY;
                    if (upY - downy > 0) {
                        if (ll.getVisibility() != View.VISIBLE) {
                            TranslateAnimation animate = new TranslateAnimation(0, 0, ll.getHeight(), 0);
                            animate.setDuration(500);
                            animate.setFillAfter(false);
                            ll.startAnimation(animate);
                            ll.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (ll.getVisibility() != View.GONE) {
                            TranslateAnimation animate = new TranslateAnimation(0, 0, 0, ll.getHeight());
                            animate.setDuration(500);
                            animate.setFillAfter(false);
                            ll.startAnimation(animate);
                            ll.setVisibility(View.GONE);
                        }
                    }
                    downy = null;
                    break;

                }
            }
        }
        return false;
    }


}
