package com.example.museums.API.services.api;




import com.example.museums.API.models.exhibition.BaseExhibition;
import com.example.museums.API.models.exhibition.ExistingExhibition;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ExhibitionService {
    @GET("exhibitions")
    Flowable<List<ExistingExhibition>> getAllExhibitions();

    @DELETE("exhibitions/{id}")
    Single<Boolean> deleteExhibition(@Path("id") int id);

    @POST("exhibitions")
    Single<ExistingExhibition> createExhibition(BaseExhibition exhibition);

    @PUT("exhibitions")
    Single<ExistingExhibition> updateExhibition(ExistingExhibition exhibition);

    @GET("exhibitions/{id}")
    Flowable<List<ExistingExhibition>> getExhibitionsByMuseumId(@Path("id") int id);

}
