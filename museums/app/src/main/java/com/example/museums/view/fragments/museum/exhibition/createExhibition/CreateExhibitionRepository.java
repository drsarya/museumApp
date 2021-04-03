package com.example.museums.view.fragments.museum.exhibition.createExhibition;

import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.exhibition.BaseExhibition;
import com.example.museums.API.models.exhibition.ExistingExhibition;
import com.example.museums.API.services.BitmapConverter;
import com.example.museums.API.services.api.ExhibitionService;
import com.example.museums.API.services.api.FileService;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateExhibitionRepository {
    private static CreateExhibitionRepository createExhibitionRepository;

    private ExhibitionService exhibitionService;
    private static FileService fileService;

    public static CreateExhibitionRepository getInstance() {
        if (createExhibitionRepository == null) {
            createExhibitionRepository = new CreateExhibitionRepository();
        }
        return createExhibitionRepository;
    }

    public CreateExhibitionRepository() {
        exhibitionService = RetrofitConnect.createRetrofitConnection(ExhibitionService.class);
        fileService = RetrofitConnect.createRetrofitConnection(FileService.class);
    }

    public MutableLiveData<ExistingExhibition> createExhibition(BaseExhibition exhibition, Bitmap bitmap) throws IOException {
        MutableLiveData<ExistingExhibition> newsData = new MutableLiveData<>();
        File file = BitmapConverter.convertBitmapToFile(bitmap);
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("upload", file.getName(), reqFile);

        fileService.uploadImage(body)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            exhibition.setImageUrl(response.body());
                            exhibitionService.createExhibition(exhibition)
                                    .enqueue(new Callback<ExistingExhibition>() {
                                        @Override
                                        public void onResponse(Call<ExistingExhibition> call, Response<ExistingExhibition> response) {
                                            if (response.isSuccessful()) {
                                                newsData.setValue(response.body());
                                            }
                                        }
                                        @Override
                                        public void onFailure(Call<ExistingExhibition> call, Throwable t) {
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
