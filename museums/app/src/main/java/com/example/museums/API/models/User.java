package com.example.museums.API.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "user", indices = {@Index(value = {"login"}, unique = true)})
public class User {

    @ColumnInfo(name = "id_user")
    @PrimaryKey(autoGenerate = true)
    public Integer userId;


    @NonNull
    @ColumnInfo(name = "login")
    public String login;

    @ColumnInfo(name = "password")
    @Nullable
    public String password;
    //true - admin
    //false обычный пользователь
    @ColumnInfo(name = "type")
    @NonNull
    public boolean type;
}
