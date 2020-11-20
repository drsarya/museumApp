package com.example.museums.API.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Exhibition.class, parentColumns = "id", childColumns = "idExhibition"),
        @ForeignKey(entity = Exhibit.class, parentColumns = "id", childColumns = "idExhibit")
}, tableName = "exhibit_to_exhbtn"
        , indices = {@Index(value = {"idExhibition", "idExhibit"}, unique = true)})


public class ExhibitToExhbtn {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public int id;


    @ColumnInfo(name = "idExhibition")
    @NonNull
    public int idExhibition;

    @ColumnInfo(name = "idExhibit")
    @NonNull
    public int idExhibit;
}
