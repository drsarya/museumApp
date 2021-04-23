package com.example.museums.API.models.like;

import com.example.museums.API.models.enums.TypeOfArtEnum;

import lombok.Data;

@Data
public class BaseLike {
    Integer artId ;

    TypeOfArtEnum type ;

    public BaseLike(Integer artId, TypeOfArtEnum type) {
        this.artId = artId;
        this.type = type;
    }
    public BaseLike( ) {
    }
}
