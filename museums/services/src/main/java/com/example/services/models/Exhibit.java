package com.example.services.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Author.class, parentColumns = "id", childColumns = "authorId"),
        @ForeignKey(entity = TypeOfExihibit.class, parentColumns = "id", childColumns = "typeId"),
        @ForeignKey(entity = Photo.class, parentColumns = "id", childColumns = "photoUrlId")
})

public class Exhibit {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public  int id;

    @NonNull
    @ColumnInfo(name = "typeId")
    public int typeId;

    @NonNull
    @ColumnInfo(name = "authorId")
    public int authorId;

    @NonNull
    @ColumnInfo(name = "name")
    public  String name;

    @NonNull
    @ColumnInfo(name = "photoUrlId")
    public  String photoUrlId;

    @NonNull
    @ColumnInfo(name = "description")
    public String description;

    @NonNull
    @ColumnInfo(name = "dateOfCreate")
    public String dateOfCreate;
}
