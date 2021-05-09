package com.example.museums.view.services.Listeners.onTouchListeners;

import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;

public class OnTouchListenerScrollViewSwipeLeftRight implements View.OnTouchListener {
    private Float downy = null;
    private LinearLayout ll;
    private boolean trof;

    public OnTouchListenerScrollViewSwipeLeftRight(LinearLayout ll, boolean trof) {

        this.ll = ll;
        this.trof = trof;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                downy = event.getY();
                break;
            }
            case MotionEvent.ACTION_UP: {
                if (downy != null) {
                    float upY = event.getY();
                    if (upY - downy > 0) {
                        if (ll.getVisibility() != View.VISIBLE) {
                            if (trof) {
                                TranslateAnimation animate = new TranslateAnimation(0, 0, ll.getHeight(), 0);
                                animate.setDuration(500);
                                animate.setFillAfter(false);
                                ll.startAnimation(animate);
                                ll.setVisibility(View.VISIBLE);
                            } else {
                                ll.setVisibility(View.VISIBLE);
                            }
                        }
                    } else {
                        if (ll.getVisibility() != View.GONE) {
                            if (trof) {
                                TranslateAnimation animate = new TranslateAnimation(0, 0, 0, ll.getHeight());
                                animate.setDuration(500);
                                animate.setFillAfter(false);
                                ll.startAnimation(animate);
                                ll.setVisibility(View.GONE);
                            } else {
                                ll.setVisibility(View.GONE);
                            }
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
