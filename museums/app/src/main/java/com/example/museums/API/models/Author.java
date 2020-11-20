package com.example.museums.API.models;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "author")

public class Author {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public   int id;

    @NonNull
    @ColumnInfo(name = "lastName")
    public String lastName;

    @NonNull
    @ColumnInfo(name = "firstName")
    public String firstName;




}
