package com.example.museums.API.services.api;


import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.API.models.exhibition.BaseExhibition;
import com.example.museums.API.models.exhibition.ExistingExhibition;

import java.util.List;

import io.reactivex.annotations.Nullable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ExhibitionService {
    @GET("exhibitions")
    Call<List<ExistingExhibition>> getAllExhibitions();

    @DELETE("exhibitions/{id}")
    Call<AnswerModel> deleteExhibition(@Path("id") Integer id);

    @POST("exhibitions")
    Call<ExistingExhibition> createExhibition(@Body BaseExhibition exhibition);

    @Multipart
    @PUT("exhibitions")
    Call<ExistingExhibition> updateExhibition(@Part MultipartBody.Part image, @Part("exhibition") ExistingExhibition exhibition);


    @GET("exhibitions/liked/{idUser}")
    Call<List<ExistingExhibition>> getLikedExhibitionsByUser(@Path("idUser") Integer idUser);

    @GET("exhibitions/{id}")
    Call<List<ExistingExhibition>> getExhibitionsByMuseumId(@Path("id") Integer id);

}
