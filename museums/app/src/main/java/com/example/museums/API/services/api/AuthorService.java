package com.example.museums.API.services.api;




import com.example.museums.API.models.author.Author;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AuthorService {
    @GET("author")
    Call<List<Author>> getAuthors();
}
