package com.example.museums.API.models.museum;

import lombok.Data;

@Data
public class UpdatableMuseum {
    Long id;
    String nameMuseum;
    String address;
    String description;
    String imageUrl;
}
