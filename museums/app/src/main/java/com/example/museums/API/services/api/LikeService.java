package com.example.museums.API.services.api;

import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.API.models.exhibition.ExistingExhibition;
import com.example.museums.API.models.like.BaseLike;
import com.example.museums.API.models.like.UserLike;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LikeService {

    @POST("likes")
    Single<Integer>  getLikesByArtId(@Body BaseLike baseLike);

    @POST("likes")
    Single<BaseLike> getLikeByUser(@Body UserLike userLike);

    @DELETE("likes")
    Single<Boolean> deleteLikeByUser(@Body UserLike userLike);

    @POST("likes")
    Single<Boolean> createLike(@Body UserLike userLike);

    @GET("likes/exhibits/{idUser}")
    Observable<List<ExistingExhibit>> getLikedExhibitsByUser(@Path("idUser") Integer idUser);

    @GET("likes/exhibitions/{idUser}")
    Observable<List<ExistingExhibition>> getLikedExhibitionsByUser(@Path("idUser") Integer idUser);
}
