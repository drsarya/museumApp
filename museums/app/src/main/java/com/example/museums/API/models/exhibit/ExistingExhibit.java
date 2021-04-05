package com.example.museums.API.models.exhibit;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.museums.API.models.author.Author;

import java.util.Objects;

import lombok.Data;

@Data
public class ExistingExhibit extends BaseExhibit{
    Integer id ;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExistingExhibit that = (ExistingExhibit) o;
        return Objects.equals(author, that.author) &&
                Objects.equals(name, that.name) &&
                Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(id, that.id) &&
                Objects.equals(description, that.description) &&
                Objects.equals(dateOfCreate, that.dateOfCreate) &&
                Objects.equals(exhibitionId, that.exhibitionId);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id,author, name, imageUrl, description, dateOfCreate, exhibitionId);
    }



    public ExistingExhibit(Author author, String name, String imageUrl, String description, String dateOfCreate, Integer exhibitionId, Integer id) {
        super(author, name, imageUrl, description, dateOfCreate, exhibitionId);
        this.id = id;
    }
    public ExistingExhibit(Author author, String name,   String description, String dateOfCreate , Integer id) {
        super(author, name,   description, dateOfCreate );
        this.id = id;
    }




}
