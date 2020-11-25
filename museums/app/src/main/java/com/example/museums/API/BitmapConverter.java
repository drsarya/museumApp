package com.example.museums.API;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BitmapConverter {
    @TypeConverter
    public static Bitmap bytesToBitmap(byte[] data) {
        if (data == null) {
            return null;
        }

        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }

    @TypeConverter
    public static byte[] bitmapToBytes(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        byte[] byteArray = stream.toByteArray();
        bitmap.recycle();
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArray;
    }
}
