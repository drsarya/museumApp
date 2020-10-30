package com.example.services.models;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

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

    @Nullable
    @ColumnInfo(name = "patronymic")
    public String patronymic;


}
