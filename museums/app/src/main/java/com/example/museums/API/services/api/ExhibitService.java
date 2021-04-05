package com.example.museums.API.services.api;


import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.exhibit.BaseExhibit;
import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.API.models.exhibition.ExistingExhibition;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ExhibitService {
    @GET("exhibits")
    Call<List<ExistingExhibit>> getAllExhibits();

    @GET("exhibits/museumId/{museumId}")
    Call<List<ExistingExhibit>> getExhibitsByMuseumId(@Path("museumId") Integer id);

    @GET("exhibits/exhibition/{exhibitionId}")
    Call<List<ExistingExhibit>> getExhibitsByExhibitionId(@Path("exhibitionId") Integer id);

    @DELETE("exhibits/{id}")
    Call<AnswerModel> deleteExhibit(@Path("id") Integer id);

    @POST("exhibits")
    Call<ExistingExhibit> createExhibit(@Body BaseExhibit exhibit);
    @Multipart
    @PUT("exhibits")
    Call<ExistingExhibit> updateExhibit(@Part MultipartBody.Part image, @Part("exhibit") ExistingExhibit exhibit);

}
