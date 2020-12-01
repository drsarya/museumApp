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

    @Override
    public String toString() {
        return "ExhibitWithAuthor{" +
                "id=" + id +
                ", id_author=" + id_author +
                ", fullName='" + fullName + '\'' +
                ", name='" + name + '\'' +
                ", photo=" + photo +
                ", description='" + description + '\'' +
                ", dateOfCreate='" + dateOfCreate + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        ExhibitWithAuthor that = (ExhibitWithAuthor) o;
        return id == that.id &&
                id_author == that.id_author &&
                fullName.equals(that.fullName) &&
                name.equals(that.name) &&

                description.equals(that.description) &&
                dateOfCreate.equals(that.dateOfCreate) &&
                tags.equals(that.tags);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id, id_author, fullName, name, photo, description, dateOfCreate, tags);
    }


}
