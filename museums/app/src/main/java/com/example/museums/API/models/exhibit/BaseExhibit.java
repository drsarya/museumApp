package com.example.museums.API.models.exhibit;


import com.example.museums.API.models.author.Author;

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

    public BaseExhibit(Author author, String name, String description, String dateOfCreate, Integer exhibitionId) {
        this.author = author;
        this.name = name;
        this.description = description;
        this.dateOfCreate = dateOfCreate;
        this.exhibitionId = exhibitionId;
    }
}
