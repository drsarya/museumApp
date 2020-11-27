package com.example.museums.API.interfaces;

import com.example.museums.API.models.Author;

import java.util.List;

public interface AuthorFacade {
    void getAuthors();
    void getAuthorByName(String name);

    void insertAuthor(String author);
    void insertAuthors(List<Author> author);

}
