package com.example.museums.API.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "museum")

public class Museum {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "login")
    @NonNull
    public String login;

    @ColumnInfo(name = "name")
    @NonNull
    public String name;

    @ColumnInfo(name = "country")
    @NonNull
    public String country;

    @ColumnInfo(name = "city")
    @NonNull
    public String city;

    @ColumnInfo(name = "street")
    @NonNull
    public String street;

    @ColumnInfo(name = "build")
    @NonNull
    public String build;

    @ColumnInfo(name = "description")
    public String description;
    @ColumnInfo(name = "image")
    public String image;

}
