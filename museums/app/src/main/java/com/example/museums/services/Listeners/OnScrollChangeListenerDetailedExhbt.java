package com.example.museums.services.Listeners;

import android.os.Build;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.M)
public class OnScrollChangeListenerDetailedExhbt implements View.OnScrollChangeListener{
    private LinearLayout ll;

    public OnScrollChangeListenerDetailedExhbt(LinearLayout ll) {
        this.ll = ll;
    }

    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

        if (scrollY - oldScrollY < 0) {


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
