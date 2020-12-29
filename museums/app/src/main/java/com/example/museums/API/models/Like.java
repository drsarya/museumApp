package com.example.museums.API.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "like", indices = {@Index(value = {"id_user_fk", "idExhb", "type"}, unique = true)})
public class Like {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public Integer id;

    @ColumnInfo(name = "id_user_fk")
    @NonNull
    public Integer idUserFk;


    @ColumnInfo(name = "idExhb")
    @NonNull
    public String idExhb;

    //true - экпонат
    //false- выставка
    @ColumnInfo(name = "type")
    @NonNull
    public boolean type;

}
