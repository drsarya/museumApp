package com.example.museums.API.services.api;




import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.exhibition.BaseExhibition;
import com.example.museums.API.models.exhibition.ExistingExhibition;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ExhibitionService {
    @GET("exhibitions")
    Call<List<ExistingExhibition>> getAllExhibitions();

    @DELETE("exhibitions/{id}")
    Call<AnswerModel> deleteExhibition(@Path("id") int id);

    @POST("exhibitions")
    Call<ExistingExhibition> createExhibition(BaseExhibition exhibition);

    @PUT("exhibitions")
    Call<ExistingExhibition> updateExhibition(ExistingExhibition exhibition);

    @GET("exhibitions/{id}")
    Call<List<ExistingExhibition>> getExhibitionsByMuseumId(@Path("id") int id);

}
