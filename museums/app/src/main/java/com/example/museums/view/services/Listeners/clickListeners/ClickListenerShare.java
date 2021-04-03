package com.example.museums.view.services.Listeners.clickListeners;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;


import java.util.Calendar;

public class ClickListenerShare implements View.OnClickListener {
    private Activity activity;
    private String newExhibitModel;
    private Bitmap image;

    public ClickListenerShare(Activity activity, String newExhibitModel, Bitmap image) {
        this.activity = activity;
        this.newExhibitModel = newExhibitModel;
        this.image = image;
    }

    public ClickListenerShare(Activity activity, String newExhibitModel) {
        this.activity = activity;
        this.newExhibitModel = newExhibitModel;

    }

    public ClickListenerShare() {


    }


    @Override
    public void onClick(View v) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        System.out.println(newExhibitModel);
        sendIntent.putExtra(Intent.EXTRA_TEXT, newExhibitModel);
        if (image != null) {
            String bitmapPath = MediaStore.Images.Media.insertImage(activity.getContentResolver(), image, "IMG_" + Calendar.getInstance().getTime(), null);
            Uri bitmapUri = Uri.parse(bitmapPath);
            sendIntent.putExtra(Intent.EXTRA_STREAM, bitmapUri);
            sendIntent.setType("image/jpeg");
            sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            sendIntent.setType("text/plain");
        }
        if (sendIntent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivity(sendIntent);
        }
    }

    public void setInfo(Activity activity, String newExhibitModel) {
        this.activity = activity;
        this.newExhibitModel = newExhibitModel;
    }


}
