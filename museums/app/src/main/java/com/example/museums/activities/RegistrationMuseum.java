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
import com.example.museums.services.MyGestureListener;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

public class RegistrationMuseum extends AppCompatActivity {
    ScrollView view;
    RelativeLayout relativeLayoutMuseumReg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_museum);
        initViews();
        touchListener();
        keyBoardListener();
    }

    private void initViews() {
        relativeLayoutMuseumReg = (RelativeLayout) findViewById(R.id.relativeLayotRegMuseum);
        view = (ScrollView) findViewById(R.id.l1_reg_museum);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void touchListener() {

        MyGestureListener ndm = new MyGestureListener();
        GestureDetector mDetector = new GestureDetector(getApplication(), ndm);
        view.setOnTouchListener((View v, MotionEvent event) -> {
            boolean b = mDetector.onTouchEvent(event);
            if (b) {
                Intent intent1 = new Intent(getApplication(), Authorization.class);
                this.startActivity(intent1);
            }
            return mDetector.onTouchEvent(event);
        });
    }

    private void keyBoardListener() {

        KeyboardVisibilityEvent.setEventListener(
                this,
                new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen) {
                        if (isOpen) {
                            relativeLayoutMuseumReg.setVisibility(View.GONE);
                        } else {
                            relativeLayoutMuseumReg.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }
}
