package com.example.museums.view.fragments.museum.createExhibition;

import android.graphics.Bitmap;



public class NewExhibitModel {

    public String dateOfCreate;

    public String tags;
    public String author;

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









}
