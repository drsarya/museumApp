package com.example.museums.API.models;

import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Objects;

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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Museum museum = (Museum) o;
        return Objects.equals(id, museum.id) &&
                login.equals(museum.login) &&
                nameMuseum.equals(museum.nameMuseum) &&
                address.equals(museum.address) &&
                Objects.equals(description, museum.description)
               ;
    }


}
