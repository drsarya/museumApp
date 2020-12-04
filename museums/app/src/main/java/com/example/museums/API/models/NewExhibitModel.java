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
    public Integer exhibitId  ;

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


    @ColumnInfo(name = "tags")
    public String tags;


//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        NewExhibitModel that = (NewExhibitModel) o;
//        return
//
//                Objects.equals(author, that.author) &&
//                name.equals(that.name) &&
//                description.equals(that.description) &&
//                dateOfCreate.equals(that.dateOfCreate) &&
//                Objects.equals(tags, that.tags);
//    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        System.out.println("ssdsdsdsdsdsdssdsssssssssssssssssssssssss");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewExhibitModel that = (NewExhibitModel) o;
        return Objects.equals(author, that.author) &&
                Objects.equals(exhibitId, that.exhibitId) &&
                idAuthor.equals(that.idAuthor) &&
                name.equals(that.name) &&
                description.equals(that.description) &&
                dateOfCreate.equals(that.dateOfCreate) &&
                Objects.equals(tags, that.tags);
    }


    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public NewExhibitModel(Integer exhibitId, String dateOfCreate, String tags, String author, String name, Bitmap photo, String description) {
        // модель для только что созданного экпоната create_exhibit
        this.dateOfCreate = dateOfCreate;
        this.tags = tags;
        this.author = author;
        this.name = name;
        this.photo = photo;
        this.exhibitId = exhibitId;
        this.description = description;
    }

    public NewExhibitModel(int exhibitId, int authorId, String dateOfCreate, String tags, String author, String name, Bitmap photo, String description) {
        // модель для уже созданных экпонатов edit_exhibition
        this.dateOfCreate = dateOfCreate;
        this.tags = tags;
        this.author = author;
        this.name = name;
        this.photo = photo;
        this.exhibitId = exhibitId;
        this.idAuthor = authorId;
        this.description = description;
    }


    @Override
    public String toString() {
        return "NewExhibitModel{" +
                "dateOfCreate='" + dateOfCreate + '\'' +
                ", tags='" + tags + '\'' +
                ", id='" + exhibitId + '\'' +
                ", author='" + author + '\'' +
                ", idAuthor=" + idAuthor +
                ", name='" + name + '\'' +
                ", photo=" + photo +
                ", description='" + description + '\'' +
                '}';
    }
}
