package com.example.museums.API.models.exhibition;

import com.example.museums.API.models.exhibit.BaseExhibit;
import com.example.museums.API.models.museum.ShortInfoMuseum;

import java.util.Objects;

import io.reactivex.annotations.Nullable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExistingExhibition extends BaseExhibition {
    Integer id;

    public ExistingExhibition(ShortInfoMuseum shortInfoMuseum, String name, String description, @Nullable String firstDate, @Nullable String lastDate, Integer id) {
        super(shortInfoMuseum, name, description, firstDate, lastDate);
        this.id = id;
    }


}
