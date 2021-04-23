package com.example.museums.API.models.exhibition;


import com.example.museums.API.models.museum.ShortInfoMuseum;

import io.reactivex.annotations.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseExhibition {
    ShortInfoMuseum museum;

    String name;

    String description;

    @Nullable
    String firstDate;

    @Nullable
    String lastDate;

    String imageUrl;

    public BaseExhibition(ShortInfoMuseum museum, String name, String description, @Nullable String firstDate, @Nullable String lastDate) {
        this.museum = museum;
        this.name = name;
        this.description = description;
        this.firstDate = firstDate;
        this.lastDate = lastDate;
    }


}
