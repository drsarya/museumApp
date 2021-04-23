package com.example.museums.API.services.api;


import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.museum.BaseMuseum;
import com.example.museums.API.models.museum.ExistingMuseum;
import com.example.museums.API.models.museum.UpdatableMuseum;
import com.example.museums.API.models.museum.UpdatableMuseumAdmin;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface MuseumService {
    @POST("museum/{login}")
    Call<AnswerModel> createMuseum(@Body BaseMuseum baseMuseum, @Path("login") String login);

    @GET("museum")
    Call<List<ExistingMuseum>> getAllMuseums();

    @PUT("museum")
    Call<AnswerModel> updateMuseum(@Body UpdatableMuseum baseMuseum);

    @PUT("museum/admin")
    Call<AnswerModel> updateMuseumByAdmin(@Body UpdatableMuseumAdmin baseMuseum);
    @GET("museum/owner/{id}")
    Call<AnswerModel> getOwnerByMuseumId(@Path("id") Integer id);

    @GET("museum/{id}")
    Call<ExistingMuseum> getMuseumById(@Path("id") Integer id);

    @PUT("museum/lock/{id}")
    Call<AnswerModel> lockMuseum(@Path("id") Integer id);

    @DELETE("museum/{id}")
    Call<AnswerModel> deleteMuseum(@Path("id") Integer id);

}
