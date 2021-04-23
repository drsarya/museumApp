package com.example.museums.API.models.like;

import com.example.museums.API.models.enums.TypeOfArtEnum;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserLike extends BaseLike {
    Integer userId;


    public UserLike(Integer artId, TypeOfArtEnum type, Integer userId) {
        super(artId, type);
        this.userId = userId;
    }
}
