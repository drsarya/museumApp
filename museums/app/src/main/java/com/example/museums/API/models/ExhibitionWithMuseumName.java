package com.example.museums.API.models;

import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import java.util.Objects;

public class ExhibitionWithMuseumName {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "name")
    public String name;
    @NonNull
    @ColumnInfo(name = "idMuseum")
    public String idMuseum;
    @NonNull
    @ColumnInfo(name = "image")
    public Bitmap image;

    @NonNull
    @ColumnInfo(name = "description")
    public String description;

    @Nullable
    @ColumnInfo(name = "firstDate")
    public String firstDate;
    @Nullable
    @ColumnInfo(name = "lastDate")
    public String lastDate;

    @ColumnInfo(name = "nameMuseum")
    @NonNull
    public String nameMuseum;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExhibitionWithMuseumName that = (ExhibitionWithMuseumName) o;
        return id == that.id &&
                name.equals(that.name) &&
                idMuseum.equals(that.idMuseum) &&
                 description.equals(that.description) &&
                Objects.equals(firstDate, that.firstDate) &&
                Objects.equals(lastDate, that.lastDate) &&
                nameMuseum.equals(that.nameMuseum);
    }


}
