package com.example.services.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "User")
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public  int id;

    @ColumnInfo(name = "login")
    @NonNull
    public  String login;

    @ColumnInfo(name = "password")
    @NonNull
    public String password;
}
