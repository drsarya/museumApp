package com.example.museums.dop;


import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageSwitcher;

import androidx.annotation.NonNull;

import com.example.museums.activities.Authorization;
import com.example.museums.activities.SplashScreen;

public class MyHandler extends Handler {

    public MyHandler(ImageSwitcher imageSwitcher, int[] listImages, SplashScreen splashScreen) {
        this.imageSwitcher = imageSwitcher;
        this.listImages = listImages;
        this.splashScreen = splashScreen;
    }

    private int ind = 0;
    private ImageSwitcher imageSwitcher;
    private int[] listImages;
    private SplashScreen splashScreen;

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        if(msg.arg1 == 50){
            Intent intent = new Intent(splashScreen.getApplicationContext(), Authorization.class);
            splashScreen.startActivity(intent);
        }else{
        imageSwitcher.setImageResource(listImages[ind % listImages.length]);
        ind++;}
    }
}
