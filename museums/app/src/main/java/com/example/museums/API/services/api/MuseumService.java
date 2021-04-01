package com.example.museums.API.services.api;


import com.example.museums.API.models.OkModel;
import com.example.museums.API.models.museum.BaseMuseum;
import com.example.museums.API.models.museum.ExistingMuseum;
import com.example.museums.API.models.museum.UpdatableMuseum;


import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface MuseumService {
    @POST("museum/{login}")
    Call<OkModel> createMuseum(@Body BaseMuseum baseMuseum, @Path("login") String login);

    @GET("museum")
    Call<List<ExistingMuseum>> getAllMuseums();

    @PUT("museum")
    Call<OkModel> updateMuseum(@Body UpdatableMuseum baseMuseum);

    @GET("museum/{id}")
    Call<ExistingMuseum> getMuseumByWorkerId(@Path("id") Integer id);

}
