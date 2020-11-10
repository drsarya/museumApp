package com.example.museums.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.museums.R;
import com.example.museums.services.Listeners.GestureDetectorTurnBack;
import com.example.museums.services.Listeners.KeyboardListenerHideOptionalBlock;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;

public class RegistrationMuseum extends AppCompatActivity {
    ScrollView view;
    RelativeLayout relativeLayoutMuseumReg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_museum);
        initViews();
        setListeners();
    }

    private void initViews() {
        relativeLayoutMuseumReg = (RelativeLayout) findViewById(R.id.registration_museum_relative_layout);
        view = (ScrollView) findViewById(R.id.registration_museum_scroll_view);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void onTouchlistener() {
        GestureDetectorTurnBack ndm = new GestureDetectorTurnBack();
        GestureDetector mDetector = new GestureDetector(getApplication(), ndm);

        view.setOnTouchListener((View v, MotionEvent event) -> {

            boolean b = mDetector.onTouchEvent(event);
            System.out.println(b);
            if (b) {
                Intent intent1 = new Intent(getApplication(), Authorization.class);
                this.startActivity(intent1);
            }
            return mDetector.onTouchEvent(event);
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setListeners() {
        onTouchlistener();

    }
}
