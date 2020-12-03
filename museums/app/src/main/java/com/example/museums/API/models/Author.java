package com.example.museums.API.models;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "author", indices = {@Index(value = {"fullName"}, unique = true)})

public class Author {
    @ColumnInfo(name = "id_author")
    @PrimaryKey(autoGenerate = true)
    public Integer id_author;

    @NonNull
    @ColumnInfo(name = "fullName")
    public String fullName;

}
