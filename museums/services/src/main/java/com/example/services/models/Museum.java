package com.example.services.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class Museum {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public   int id;

    @ColumnInfo(name = "name")
    @NonNull
    public  String name;

    @ColumnInfo(name = "country")
    @NonNull
    public  String country;

    @ColumnInfo(name = "city")
    @NonNull
    public  String city;

    @ColumnInfo(name = "street")
    @NonNull
    public   String street;

    @ColumnInfo(name = "build")
    @NonNull
    public  String build;

    @ColumnInfo(name = "description")
    @NonNull
    public  String description;
}
