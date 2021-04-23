package com.example.museums.view.services.Listeners.onTouchListeners;

import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;

public class OnTouchlistenerScrollViewSwipeLeftRightBack implements View.OnTouchListener {
    private Float downx = null;
    private Float downy = null;
    private FragmentActivity activity;
    private boolean trof;
    private LinearLayout ll;

    public OnTouchlistenerScrollViewSwipeLeftRightBack(FragmentActivity activity, boolean trof) {
        this.activity = activity;
        this.trof = trof;
    }

    public OnTouchlistenerScrollViewSwipeLeftRightBack(FragmentActivity activity, boolean trof, LinearLayout ll) {
        this.activity = activity;
        this.trof = trof;
        this.ll = ll;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                downx = event.getX();
                downy = event.getY();
                break;
            }
            case MotionEvent.ACTION_UP: {
                if (downy != null && downx != null) {
                    float upX = event.getX();
                    float upY = event.getY();
                    float deltaX = downx - upX;
                    float deltaY = downy - upY;
                    if (Math.abs(deltaX) > Math.abs(deltaY) && Math.abs(deltaX) > 250) {
                        activity.onBackPressed();
                    } else {
                        if (trof) {
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
                        }
                    }
                    downx = null;
                    downy = null;
                    break;
                }
            }
        }
        return false;
    }
}
