package com.example.museums.API.services.api;


import com.example.museums.API.models.museum.BaseMuseum;
import com.example.museums.API.models.museum.Museum;
import com.example.museums.API.models.museum.UpdatableMuseum;


import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface MuseumService {
    @POST("museum")
    Single<Museum> createMuseum(@Body BaseMuseum baseMuseum);

    @GET("museum")
    Flowable<List<Museum>> getAllMuseums();

    @PUT("museum")
    Single<Museum> updateMuseum(@Body UpdatableMuseum baseMuseum);

    @GET("museum/{id}")
    Single<Museum> getMuseumByWorkerId(@Path("id") Integer id);

}
