package com.example.museums.API.models;

import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import java.util.Objects;


public class NewExhibitModel {

    @ColumnInfo(name = "fullName")
    public String author;

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public Integer exhibitId;

    @ColumnInfo(name = "authorId")
    public Integer idAuthor;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "photo")
    public Bitmap photo;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "dateOfCreate")
    public String dateOfCreate;




    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewExhibitModel that = (NewExhibitModel) o;
        return Objects.equals(author, that.author) &&
                Objects.equals(exhibitId, that.exhibitId) &&
                idAuthor.equals(that.idAuthor) &&
                name.equals(that.name) &&
                description.equals(that.description) &&
                dateOfCreate.equals(that.dateOfCreate)  ;
    }




    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public NewExhibitModel(Integer exhibitId, String dateOfCreate,   String author, String name, Bitmap photo, String description) {
        this.dateOfCreate = dateOfCreate;
        this.author = author;
        this.name = name;
        this.photo = photo;
        this.exhibitId = exhibitId;
        this.description = description;
    }


    @Override
    public String toString() {
        return "NewExhibitModel{" +
                "dateOfCreate='" + dateOfCreate + '\'' +

                ", id='" + exhibitId + '\'' +
                ", author='" + author + '\'' +
                ", idAuthor=" + idAuthor +
                ", name='" + name + '\'' +
                ", photo=" + photo +
                ", description='" + description + '\'' +
                '}';
    }
}
