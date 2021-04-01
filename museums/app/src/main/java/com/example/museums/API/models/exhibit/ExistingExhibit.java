package com.example.museums.API.models.exhibit;

import com.example.museums.API.models.author.Author;

import lombok.Data;

@Data
public class ExistingExhibit extends BaseExhibit{

    Integer id ;

    public ExistingExhibit(Author author, String name, String imageUrl, String description, String dateOfCreate, Integer exhibitionId, Integer id) {
        super(author, name, imageUrl, description, dateOfCreate, exhibitionId);
        this.id = id;
    }
}
