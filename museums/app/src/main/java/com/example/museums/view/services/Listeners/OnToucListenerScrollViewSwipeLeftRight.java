package com.example.museums.view.services.Listeners;

import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;

public class OnToucListenerScrollViewSwipeLeftRight implements View.OnTouchListener {
    private Float downy = null;
    private FragmentActivity activity;

    private LinearLayout ll;
    private Float downx = null;

    private boolean trof;

    public OnToucListenerScrollViewSwipeLeftRight(FragmentActivity activity, LinearLayout ll, boolean trof) {
        this.activity = activity;
        this.ll = ll;
        this.trof = trof;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                downy = event.getY();
                System.out.println("нажала");
                downx = event.getX();

                break;
            }
            case MotionEvent.ACTION_UP: {
                System.out.println("отпустила");
                if (downy != null) {
                    float upY = event.getY();
                    float upX = event.getX();
                    float deltaY = downy - upY;
                    System.out.println(deltaY);
                    if (upY - downy > 0) {
                        if (ll.getVisibility() != View.VISIBLE) {
                            if (trof) {
                                TranslateAnimation animate = new TranslateAnimation(0, 0, ll.getHeight(), 0);
                                animate.setDuration(500);
                                animate.setFillAfter(false);
                                ll.startAnimation(animate);
                                ll.setVisibility(View.VISIBLE);
                            } else {
//                                TranslateAnimation animate = new TranslateAnimation(0, 0,  -ll.getHeight(), 0);
//                                animate.setDuration(500);
//                                animate.setFillAfter(false);
//                                ll.startAnimation(animate);
//                                System.out.println("видно");
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
//                                TranslateAnimation animate = new TranslateAnimation(0, 0, 0, -ll.getHeight());
//                                animate.setDuration(500);
//                                animate.setFillAfter(false);
//                                ll.startAnimation(animate);
//                                System.out.println("не видно");
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
