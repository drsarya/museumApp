package com.example.museums.API.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.PrimaryKey;

 public class User {



    public Integer id;


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
                "userId=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                '}';
    }
}
