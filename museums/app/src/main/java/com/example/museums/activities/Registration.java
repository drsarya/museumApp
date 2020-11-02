package com.example.museums.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.museums.R;
import com.example.museums.services.MyGestureListener;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

public class Registration extends AppCompatActivity {
    ScrollView view;
    RelativeLayout relativeLayoutMuseumReg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticvity_reg);
        initViews();
        setListener(view);
        keyBoardListener();
    }

    private void initViews() {
        view = (ScrollView) findViewById(R.id.l1_reg);
        relativeLayoutMuseumReg= (RelativeLayout) findViewById(R.id.relativeLayotRegMuseum);
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
    @SuppressLint("ClickableViewAccessibility")
    private void setListener(View cd) {
        MyGestureListener ndm = new MyGestureListener();
        GestureDetector mDetector = new GestureDetector(getApplication(), ndm);

        view.setOnTouchListener((View v, MotionEvent event) -> {

            boolean b = mDetector.onTouchEvent(event);
            System.out.println(b);
            if (b) {
                System.out.println("dfdfdfdf");
                Intent intent1 = new Intent(getApplication(), Authorization.class);
                this.startActivity(intent1);
            }
            return mDetector.onTouchEvent(event);
        });
    }
}
