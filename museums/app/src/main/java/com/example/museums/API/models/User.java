package com.example.museums.API.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

 public class User {


    @PrimaryKey(autoGenerate = true)
    public Integer userId;


    @NonNull

    public String login;


    @Nullable
    public String password;
    //true - admin
    //false обычный пользователь

    @NonNull
    public boolean type;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                '}';
    }
}
