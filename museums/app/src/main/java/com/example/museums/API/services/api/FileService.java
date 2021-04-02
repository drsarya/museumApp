package com.example.museums.API.services.api;

import android.graphics.Bitmap;

import java.io.File;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface FileService {

    @Multipart
    @POST("upload")
    Call<String> uploadImage(@Part("imageUpload") MultipartBody.Part file);
}
