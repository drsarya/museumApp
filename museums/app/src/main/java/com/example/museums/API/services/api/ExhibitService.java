package com.example.museums.API.services.api;


import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.exhibit.BaseExhibit;
import com.example.museums.API.models.exhibit.ExistingExhibit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ExhibitService {
    @GET("exhibits")
    Call<List<ExistingExhibit>> getAllExhibits();

    @GET("exhibits/{museumId}")
    Call<List<ExistingExhibit>> getExhibitsByMuseumId(@Path("museumId") Integer id);

    @GET("exhibits/exhibition/{exhibitionId}")
    Call<List<ExistingExhibit>> getExhibitsByExhibitionId(@Path("exhibitionId") Integer id);

    @DELETE("exhibits/{id}")
    Call<AnswerModel> deleteExhibit(@Path("id") int id);

    @POST("exhibits")
    Call<ExistingExhibit> createExhibit(BaseExhibit exhibit);

    @PUT("exhibits")
    Call<ExistingExhibit> updateExhibit(ExistingExhibit exhibit);
}
