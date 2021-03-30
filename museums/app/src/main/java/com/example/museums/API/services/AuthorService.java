package com.example.museums.API.services;


import com.example.museums.API.models.author.Author;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface AuthorService {
    @GET("author")
    Flowable<List<Author>> getAuthors();
}
