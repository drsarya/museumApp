package com.example.museums.view.fragments.museum.mainInfoMuseumEditPage.DialogChangeImageMuseum;

import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.OkModel;
import com.example.museums.API.models.museum.BaseMuseum;
import com.example.museums.API.models.museum.UpdatableMuseum;
import com.example.museums.API.services.BitmapConverter;
import com.example.museums.API.services.api.FileService;
import com.example.museums.API.services.api.MuseumService;

import java.io.File;
import java.io.IOException;

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

    public MutableLiveData<OkModel> updateMuseumImage(Bitmap image, Integer museumId) throws IOException {
        MutableLiveData<OkModel> newsData = new MutableLiveData<>();

        File file = BitmapConverter.convertBitmapToFile(image);
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("upload", file.getName(), reqFile);

        fileService.uploadImage(body)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            UpdatableMuseum updatableMuseum = new UpdatableMuseum();
                            updatableMuseum.setId(museumId);
                            updatableMuseum.setImageUrl(response.body());
                            museumService.updateMuseum(updatableMuseum)
                                    .enqueue(new Callback<OkModel>() {
                                        @Override
                                        public void onResponse(Call<OkModel> call, Response<OkModel> response) {
                                            if (response.isSuccessful()) {
                                                newsData.setValue(response.body());
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<OkModel> call, Throwable t) {
                                            newsData.setValue(null);
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        newsData.setValue(null);
                    }
                });
        return newsData;
    }
}
