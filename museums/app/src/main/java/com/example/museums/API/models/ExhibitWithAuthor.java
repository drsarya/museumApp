package com.example.museums.API.models;

import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import java.util.Objects;

public class ExhibitWithAuthor {
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "id_author")
     public int id_author;

    @ColumnInfo(name = "fullName")
    public String fullName;

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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExhibitWithAuthor that = (ExhibitWithAuthor) o;
        return id == that.id &&
                id_author == that.id_author &&
                Objects.equals(fullName, that.fullName) &&
                Objects.equals(name, that.name) &&
                Objects.equals(photo, that.photo) &&
                Objects.equals(description, that.description) &&
                Objects.equals(dateOfCreate, that.dateOfCreate) &&
                Objects.equals(tags, that.tags);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id, id_author, fullName, name, photo, description, dateOfCreate, tags);
    }


}
