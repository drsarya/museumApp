package com.example.services.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "user")
public class User {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "login")
    public  String login;

    @ColumnInfo(name = "password")
    @NonNull
    public String password;
}
