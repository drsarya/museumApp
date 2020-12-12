package com.example.museums.view.services.Listeners.clickListeners;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import androidx.core.content.FileProvider;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class ClickListenerShareExhibit implements View.OnClickListener {
    private Activity actibity;
    private String newExhibitModel;
    private Bitmap image;

    public ClickListenerShareExhibit(Activity actibity, String newExhibitModel, Bitmap image) {
        this.actibity = actibity;
        this.newExhibitModel = newExhibitModel;
        this.image = image;
    }

    public ClickListenerShareExhibit(Activity actibity, String newExhibitModel) {
        this.actibity = actibity;
        this.newExhibitModel = newExhibitModel;

    }


    @Override
    public void onClick(View v) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, newExhibitModel);
        if (image != null) {
            String bitmapPath = MediaStore.Images.Media.insertImage(actibity.getContentResolver(), image,  "IMG_" + Calendar.getInstance().getTime(), null   );
            Uri bitmapUri = Uri.parse(bitmapPath);
            sendIntent.putExtra(Intent.EXTRA_STREAM, bitmapUri);
            sendIntent.setType("image/jpeg");
            sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            sendIntent.setType("text/plain");
        }
        if (sendIntent.resolveActivity(actibity.getPackageManager()) != null) {
            actibity.startActivity(sendIntent);
        }
    }
}
