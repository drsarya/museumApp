package com.example.museums.API.services.api;


import com.example.museums.API.models.exhibit.BaseExhibit;
import com.example.museums.API.models.exhibit.ExistingExhibit;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ExhibitService {
    @GET("exhibits")
    Observable<List<ExistingExhibit>> getAllExhibits();

    @GET("exhibits/{museumId}")
    Observable<List<ExistingExhibit>> getExhibitsByMuseumId(@Path("museumId") Integer id);

    @DELETE("exhibits/{id}")
    Single<Boolean> deleteExhibit(@Path("id")  int id);

    @POST("exhibits")
    Single<ExistingExhibit> createExhibit(BaseExhibit exhibit);

    @PUT("exhibits")
    Single<ExistingExhibit> updateExhibit(ExistingExhibit exhibit);
}
