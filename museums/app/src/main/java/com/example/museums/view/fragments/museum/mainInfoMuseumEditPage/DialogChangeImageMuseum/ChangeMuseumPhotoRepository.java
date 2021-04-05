package com.example.museums.view.fragments.museum.mainInfoMuseumEditPage.DialogChangeImageMuseum;

import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.museum.UpdatableMuseum;
import com.example.museums.API.services.BitmapConverter;
import com.example.museums.API.services.ErrorParser;
import com.example.museums.API.services.api.FileService;
import com.example.museums.API.services.api.MuseumService;

import java.io.File;
import java.io.IOException;

import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeMuseumPhotoRepository {
    private static ChangeMuseumPhotoRepository repository;
    private MuseumService museumService;
    private FileService fileService;

    public static ChangeMuseumPhotoRepository getInstance() {
        if (repository == null) {
            repository = new ChangeMuseumPhotoRepository();
        }
        return repository;
    }

    public ChangeMuseumPhotoRepository() {
        museumService = RetrofitConnect.createRetrofitConnection(MuseumService.class);
        fileService = RetrofitConnect.createRetrofitConnection(FileService.class);
    }

    public MutableLiveData<AnswerModel> updateMuseumImage(File file, Integer museumId) {
        MutableLiveData<AnswerModel> newsData = new MutableLiveData<>();
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("imageUpload", file.getName(), requestFile);
        fileService.uploadImage(filePart)
                .enqueue(new Callback<AnswerModel>() {
                    @Override
                    public void onResponse(Call<AnswerModel> call, Response<AnswerModel> response) {
                        if (response.isSuccessful()) {
                            UpdatableMuseum updatableMuseum = new UpdatableMuseum();
                            updatableMuseum.setId(museumId);
                            updatableMuseum.setImageUrl(response.body().getMessage());
                            museumService.updateMuseum(updatableMuseum)
                                    .enqueue(new Callback<AnswerModel>() {
                                        @Override
                                        public void onResponse(Call<AnswerModel> call, Response<AnswerModel> response) {
                                            if (!response.isSuccessful()) {
                                                newsData.setValue(new AnswerModel(ErrorParser.getMessage(response)));
                                            } else {
                                                newsData.setValue(response.body());
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<AnswerModel> call, Throwable t) {
                                            newsData.setValue(null);
                                        }
                                    });
                        } else {
                            newsData.setValue(new AnswerModel(ErrorParser.getMessage(response)));
                        }
                    }

                    @Override
                    public void onFailure(Call<AnswerModel> call, Throwable t) {
                        newsData.setValue(null);
                    }
                });
        return newsData;
    }
}
