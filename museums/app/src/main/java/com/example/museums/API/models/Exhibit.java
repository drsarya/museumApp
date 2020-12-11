package com.example.museums.API.models;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(

        foreignKeys = {
                @ForeignKey(entity = Author.class, parentColumns = "id_author", childColumns = "authorId")
        },

        tableName = "exhibit", indices = {@Index(value = {"authorId"}, unique = false)})

public class Exhibit {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public Integer id;

    @NonNull
    @ColumnInfo(name = "authorId")
    public Integer authorId;

    @NonNull
    @ColumnInfo(name = "name")
    public String name;

    @NonNull
    @ColumnInfo(name = "photo")
    public Bitmap photo;

    @NonNull
    @ColumnInfo(name = "description")
    public String description;

    @NonNull
    @ColumnInfo(name = "dateOfCreate")
    public String dateOfCreate;


//    @ColumnInfo(name = "tags")
//    public String tags;

}
