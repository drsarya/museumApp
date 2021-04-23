package com.example.museums.view.services.Listeners.clickListeners;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;


import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import java.util.Calendar;

import lombok.SneakyThrows;

public class ClickListenerShare implements View.OnClickListener {
    private Activity activity;
    private String newExhibitModel;
    private String image;

    public ClickListenerShare(Activity activity, String newExhibitModel, String image) {
        this.activity = activity;
        this.newExhibitModel = newExhibitModel;
        this.image = image;
    }

    public ClickListenerShare() {
    }


    @SuppressLint("CheckResult")
    @Override
    public void onClick(View v) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, newExhibitModel);
        if (image != null) {
            Glide.with(activity.getApplicationContext())
                    .asBitmap()
                    .load(image)
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC))
                    .into(new SimpleTarget<Bitmap>() {
                        @SneakyThrows
                        @Override
                        public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                            String bitmapPath = MediaStore.Images.Media.insertImage(activity.getContentResolver(), bitmap, "IMG_" + Calendar.getInstance().getTime(), null);
                            Uri bitmapUri = Uri.parse(bitmapPath);
                            sendIntent.putExtra(Intent.EXTRA_STREAM, bitmapUri);
                            sendIntent.setType("image/jpeg");
                            sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            if (sendIntent.resolveActivity(activity.getPackageManager()) != null) {
                                activity.startActivity(sendIntent);
                            }
                        }
                    });
        } else {
            sendIntent.setType("text/plain");
            if (sendIntent.resolveActivity(activity.getPackageManager()) != null) {
                activity.startActivity(sendIntent);
            }
        }
    }

    public void setInfo(Activity activity, String newExhibitModel) {
        this.activity = activity;
        this.newExhibitModel = newExhibitModel;
    }
}
