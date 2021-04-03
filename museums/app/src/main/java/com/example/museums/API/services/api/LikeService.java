package com.example.museums.API.services.api;

import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.API.models.exhibition.ExistingExhibition;
import com.example.museums.API.models.like.BaseLike;
import com.example.museums.API.models.like.UserLike;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LikeService {

    @POST("likes")
    Call<Integer>  getLikesByArtId(@Body BaseLike baseLike);

    @POST("likes")
    Call<BaseLike> getLikeByUser(@Body UserLike userLike);

    @DELETE("likes")
    Call<AnswerModel> deleteLikeByUser(@Body UserLike userLike);

    @POST("likes")
    Call<AnswerModel> createLike(@Body UserLike userLike);

    @GET("likes/exhibits/{idUser}")
    Call<List<ExistingExhibit>> getLikedExhibitsByUser(@Path("idUser") Integer idUser);

    @GET("likes/exhibitions/{idUser}")
    Call<List<ExistingExhibition>> getLikedExhibitionsByUser(@Path("idUser") Integer idUser);
}
