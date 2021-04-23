package com.example.museums.API.models.exhibit;


import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.museums.API.models.author.Author;

import java.util.Objects;

import lombok.Data;

@Data
public class BaseExhibit {
    Author author;

    String name;

    String imageUrl;

    String description;

    String dateOfCreate;

    Integer exhibitionId;

    public BaseExhibit() {
    }

    public BaseExhibit(Author author, String name, String imageUrl, String description, String dateOfCreate, Integer exhibitionId) {
        this.author = author;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.dateOfCreate = dateOfCreate;
        this.exhibitionId = exhibitionId;
    }

    public BaseExhibit(Author author, String name, String description, String dateOfCreate) {
        this.author = author;
        this.name = name;
        this.description = description;
        this.dateOfCreate = dateOfCreate;

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseExhibit that = (BaseExhibit) o;
        return Objects.equals(author, that.author) &&
                Objects.equals(name, that.name) &&
                Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(description, that.description) &&
                Objects.equals(dateOfCreate, that.dateOfCreate) &&
                Objects.equals(exhibitionId, that.exhibitionId);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(author, name, imageUrl, description, dateOfCreate, exhibitionId);
    }

    public BaseExhibit(Author author, String name, String description, String dateOfCreate, Integer exhibitionId) {
        this.author = author;
        this.name = name;
        this.description = description;
        this.dateOfCreate = dateOfCreate;
        this.exhibitionId = exhibitionId;
    }

}
