package com.example.museums.API.models.exhibition;

import io.reactivex.annotations.Nullable;
import lombok.Data;

@Data
public class BaseExhibition {
    String name;

    String imageUrl;

    String description;

    @Nullable
    String firstDate;

    @Nullable
    String lastDate;

    Integer museumId;
}
