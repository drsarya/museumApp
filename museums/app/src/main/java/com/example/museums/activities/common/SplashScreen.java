package com.example.museums.activities.common;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.museums.R;
import com.example.museums.services.Handlers.HandlerSplashScreen;
import com.example.museums.services.Timers.SplashScreenTimerTask;

import java.util.Timer;


public class SplashScreen extends AppCompatActivity implements ViewSwitcher.ViewFactory {
    private int[] mImageIds = {R.drawable.image1,
            R.drawable.image2, R.drawable.image3, R.drawable.image4};
    private ImageSwitcher mImageSwitcher;
    private HandlerSplashScreen mg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setResourseImage();
        timerTask();
    }

    private void timerTask() {
        mg = new HandlerSplashScreen(mImageSwitcher, mImageIds, this);
        SplashScreenTimerTask sty = new SplashScreenTimerTask(mg);
        Timer tn = new Timer();
        tn.schedule(sty, 0, 2400);
    }

    private void setResourseImage() {
        mImageSwitcher = (ImageSwitcher) findViewById(R.id.splashScreenImageSwitcher);
        mImageSwitcher.setFactory(this);
        Animation inAnimation = new AlphaAnimation(0, 1);
        inAnimation.setDuration(2000);
        Animation outAnimation = new AlphaAnimation(1, 0);
        outAnimation.setDuration(2000);
        mImageSwitcher.setInAnimation(inAnimation);
        mImageSwitcher.setOutAnimation(outAnimation);
        mImageSwitcher.setImageResource(mImageIds[0]);
    }


    @Override
    public View makeView() {
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new
                ImageSwitcher.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        imageView.setBackgroundColor(0xFF000000);
        return imageView;
    }
}
