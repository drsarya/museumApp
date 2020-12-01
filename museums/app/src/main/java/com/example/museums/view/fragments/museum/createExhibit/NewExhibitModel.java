package com.example.museums.view.fragments.museum.createExhibit;

import android.graphics.Bitmap;


public class NewExhibitModel {

    public String dateOfCreate;

    public String tags;
    public String author;

    public Long getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Long idAuthor) {
        this.idAuthor = idAuthor;
    }

    public Long idAuthor;

    public String name;

    public Bitmap photo;

    public String description;

    public NewExhibitModel(String dateOfCreate, String tags, String author, String name, Bitmap photo, String description) {
        this.dateOfCreate = dateOfCreate;
        this.tags = tags;
        this.author = author;
        this.name = name;
        this.photo = photo;
        this.description = description;
    }

    @Override
    public String toString() {
        return "NewExhibitModel{" +
                "dateOfCreate='" + dateOfCreate + '\'' +
                ", tags='" + tags + '\'' +
                ", author='" + author + '\'' +
                ", idAuthor=" + idAuthor +
                ", name='" + name + '\'' +
                ", photo=" + photo +
                ", description='" + description + '\'' +
                '}';
    }
}
