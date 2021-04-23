package com.example.museums.API.services.api;

import android.graphics.Bitmap;

import com.example.museums.API.models.AnswerModel;

import java.io.File;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface FileService {

    @Multipart
    @POST("image/upload")
    Call<AnswerModel> uploadImage(@Part  MultipartBody.Part file);
}
