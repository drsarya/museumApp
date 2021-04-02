package com.example.museums.API.models.exhibition;

import io.reactivex.annotations.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseExhibition {
    Integer museumId;

    String name;

    String description;

    @Nullable
    String firstDate;

    @Nullable
    String lastDate;

    String imageUrl;

    public BaseExhibition(Integer museumId, String name, String description, @Nullable String firstDate, @Nullable String lastDate) {
        this.museumId = museumId;
        this.name = name;
        this.description = description;
        this.firstDate = firstDate;
        this.lastDate = lastDate;
    }
}
