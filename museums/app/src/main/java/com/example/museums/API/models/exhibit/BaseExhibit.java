package com.example.museums.API.models.exhibit;

import android.graphics.Bitmap;

import com.example.museums.API.models.author.Author;
import com.example.museums.API.services.BitmapConverter;

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
}
