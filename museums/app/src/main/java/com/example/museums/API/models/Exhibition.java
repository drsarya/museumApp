package com.example.museums.API.models;

import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Objects;


@Entity(foreignKeys = {
        @ForeignKey(entity = Museum.class, parentColumns = "id", childColumns = "idMuseum")


}, tableName = "exhibition", indices = {@Index(value = {"idMuseum"}, unique = false)})

public class Exhibition {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public Integer id;

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


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exhibition that = (Exhibition) o;
        return id == that.id &&
                name.equals(that.name) &&
                idMuseum.equals(that.idMuseum) &&
                image.equals(that.image) &&
                description.equals(that.description) &&
                lastDate.equals(that.lastDate) &&
                firstDate.equals(that.firstDate);

    }



}
