package com.example.museums.API.interfaces;


public interface AuthorFacade {
    void getAuthors();

    void getAuthorByName(String name);

    void insertAuthor(String author);

}
