package com.example.museums.API.models.museum;

import lombok.Data;

@Data
public class ShortInfoMuseum {
    Integer id;
    String name;

    public ShortInfoMuseum(Integer id) {
        this.id = id;
    }
}
