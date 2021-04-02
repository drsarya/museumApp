package com.example.museums.API.models.exhibition;

import com.example.museums.API.models.exhibit.BaseExhibit;

import io.reactivex.annotations.Nullable;
import lombok.Data;

@Data
public class ExistingExhibition extends BaseExhibition {
    Integer id;

    public ExistingExhibition(Integer museumId, String name, String description, @Nullable String firstDate, @Nullable String lastDate, Integer id) {
        super(museumId, name, description, firstDate, lastDate);
        this.id = id;
    }
}
