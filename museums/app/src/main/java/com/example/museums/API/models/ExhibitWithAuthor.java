package com.example.museums.API.models;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class ExhibitWithAuthor {
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "id_author")
     public int id_author;

    @ColumnInfo(name = "fullName")
    public String fullName;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "photo")
    public Bitmap photo;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "dateOfCreate")
    public String dateOfCreate;


    @ColumnInfo(name = "tags")
    public String tags;
}
