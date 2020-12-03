package com.example.museums.API.models;

import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;
import static androidx.room.ForeignKey.NO_ACTION;

@Entity(foreignKeys = {
        @ForeignKey(onDelete = CASCADE,  entity = Exhibition.class, parentColumns = "id", childColumns = "idExhibition"),
        @ForeignKey(onDelete = CASCADE,  entity = Exhibit.class, parentColumns = "id", childColumns = "idExhibit")
}, tableName = "exhibit_to_exhbtn"
        , indices = {@Index(value = {"idExhibition", "idExhibit"}, unique = true)})


public class ExhibitToExhbtn {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public Integer id;

    @ColumnInfo(name = "idExhibition")
    @NonNull
    public Integer idExhibition;

    @ColumnInfo(name = "idExhibit")
    @NonNull
    public Integer idExhibit;
}
