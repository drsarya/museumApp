package com.example.museums.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.museums.R;
import com.example.museums.services.MyGestureListenerHideLike;
import com.example.museums.services.MyHandler;
import com.example.museums.services.MyHandlerHideOptionsPanel;
import com.example.museums.services.MyTimer;
import com.example.museums.services.SplashScreenTimerTask;

import java.util.Timer;
import java.util.TimerTask;

public class DetailedExhibit extends Fragment {
    ScrollView view;
    LinearLayout ll;
    ImageView like;
    boolean state;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.setRetainInstance(true);

        View rootView =
                inflater.inflate(R.layout.fragment_detailed_exhibit, container, false);
        return rootView;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        super.setRetainInstance(true);
        state = true;
        ll = (LinearLayout) getActivity().findViewById(R.id.linearLayoutPanelOptions);
        view = (ScrollView) getActivity().findViewById(R.id.scrollViewDetailedExhbt);
        like = (ImageView) getActivity().findViewById(R.id.like_image);
        setListener();
        imageListener();
    }


    private void imageListener() {
        like.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if (state) {
                    like.setColorFilter(getActivity().getResources().getColor(R.color.lightBeige));
                    state = false;
                } else {
                    like.setColorFilter(getActivity().getResources().getColor(R.color.pink));
                    state = true;
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setListener() {
        view.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (Math.abs(oldScrollX - scrollX) < Math.abs(oldScrollY - scrollY))
                    if (scrollY - oldScrollY < 0) {
                        if (ll.getVisibility() != View.VISIBLE) {
                            TranslateAnimation animate = new TranslateAnimation(0, 0, ll.getHeight(), 0);
                            animate.setDuration(500);
                            animate.setFillAfter(true);
                            ll.startAnimation(animate);
                            ll.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (ll.getVisibility() != View.GONE) {
                            TranslateAnimation animate = new TranslateAnimation(0, 0, 0, ll.getHeight());
                            animate.setDuration(500);
                            animate.setFillAfter(true);
                            ll.startAnimation(animate);
                            ll.setVisibility(View.GONE);
                        }
                    }
            }
        });
    }


}
