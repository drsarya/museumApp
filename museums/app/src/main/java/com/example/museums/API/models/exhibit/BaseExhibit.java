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
}
