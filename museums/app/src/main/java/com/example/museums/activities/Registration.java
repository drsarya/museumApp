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
import com.example.museums.services.Listeners.OnTouchListenerGestureTurnBack;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;

public class Registration extends AppCompatActivity {
    ScrollView view;
    RelativeLayout relativeLayoutMuseumReg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticvity_reg);
        initViews();
        onTouchlistener();
        keyBoardListener();
    }

    private void initViews() {
        view = (ScrollView) findViewById(R.id.registration_scroll_view);
        relativeLayoutMuseumReg = (RelativeLayout) findViewById(R.id.registration_relative_layout);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void keyBoardListener() {


        KeyboardVisibilityEvent.setEventListener(
                this,
                new KeyboardListenerHideOptionalBlock(relativeLayoutMuseumReg));
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
}
