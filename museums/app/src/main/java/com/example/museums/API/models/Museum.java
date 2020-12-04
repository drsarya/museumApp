package com.example.museums.API.models;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "museum", indices = {@Index(value = {"login"}, unique = true),
        @Index(value = {"nameMuseum"}, unique = true), @Index(value = {"address"}, unique = true)})

public class Museum {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public Integer id;

    @ColumnInfo(name = "login")
    @NonNull
    public String login;

    @ColumnInfo(name = "nameMuseum")
    @NonNull
    public String nameMuseum;


    @ColumnInfo(name = "address")
    @NonNull
    public String address;

    @ColumnInfo(name = "description")
    public String description;
    @ColumnInfo(name = "image")
    public Bitmap image;

}
