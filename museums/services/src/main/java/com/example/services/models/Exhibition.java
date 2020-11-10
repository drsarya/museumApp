package com.example.services.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;



@Entity(foreignKeys = {
        @ForeignKey(entity = Museum.class, parentColumns = "id", childColumns = "idMuseum")


},tableName = "exhibition", indices = {@Index(value = {"idMuseum"}, unique = false)})

public class Exhibition {
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
    @ColumnInfo(name = "photoUrlId")
    public String photoUrlId;

    @NonNull
    @ColumnInfo(name = "description")
    public String description;

    @Nullable
    @ColumnInfo(name = "firstDate")
    public  String firstDate;

    @Nullable
    @ColumnInfo(name = "lastDate")
    public String lastDate;
}
