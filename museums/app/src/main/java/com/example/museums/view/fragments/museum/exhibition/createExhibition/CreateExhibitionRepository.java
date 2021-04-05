package com.example.museums.view.fragments.museum.exhibition.createExhibition;


import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.exhibition.ExistingExhibition;
import com.example.museums.API.services.api.ExhibitionService;
import com.example.museums.API.services.api.FileService;
import com.example.museums.API.models.exhibition.BaseExhibition;
import java.io.File;

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

    public MutableLiveData<ExistingExhibition> createExhibition(BaseExhibition exhibition, File file)   {
        MutableLiveData<ExistingExhibition> newsData = new MutableLiveData<>();
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("imageUpload", file.getName(), requestFile);
        fileService.uploadImage(filePart)
                .enqueue(new Callback<AnswerModel>() {
                    @Override
                    public void onResponse(Call<AnswerModel> call, Response<AnswerModel> response) {
                        if (response.isSuccessful()) {
                            exhibition.setImageUrl(response.body().getMessage());
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
                    public void onFailure(Call<AnswerModel> call, Throwable t) {
                        newsData.setValue(null);
                    }
                });
        return newsData;
    }
}
