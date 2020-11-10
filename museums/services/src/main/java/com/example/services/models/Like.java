package com.example.services.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "like", foreignKeys = {
        @ForeignKey(entity = User.class, parentColumns = "login", childColumns = "login"),

},  indices = {@Index(value = {"login" }, unique = true)})
public class Like {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public   int id;

    @ColumnInfo(name = "login")
    @NonNull
    public  String login;


    @ColumnInfo(name = "idExhb")
    @NonNull
    public  String idExhb;
}
