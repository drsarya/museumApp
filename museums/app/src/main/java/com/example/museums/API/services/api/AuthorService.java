package com.example.museums.API.services.api;


import com.example.museums.API.models.author.Author;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface AuthorService {
    @GET("author")
    Observable<List<Author>> getAuthors();
}
