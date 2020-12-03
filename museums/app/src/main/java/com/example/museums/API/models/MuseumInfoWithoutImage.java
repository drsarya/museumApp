package com.example.museums.API.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class MuseumInfoWithoutImage {
    @ColumnInfo(name = "id")
    public Integer id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "address")
    public String address;
    @ColumnInfo(name = "description")
    public String description;
}
