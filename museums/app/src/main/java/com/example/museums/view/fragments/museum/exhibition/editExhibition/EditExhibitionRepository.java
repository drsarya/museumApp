package com.example.museums.view.fragments.museum.exhibition.editExhibition;

import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.API.models.exhibition.ExistingExhibition;
import com.example.museums.API.services.BitmapConverter;
import com.example.museums.API.services.api.ExhibitService;
import com.example.museums.API.services.api.ExhibitionService;
import com.example.museums.API.services.api.FileService;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditExhibitionRepository {
    private static EditExhibitionRepository createExhibitionRepository;

    private ExhibitionService exhibitionService;
    private ExhibitService exhibitService;
    private static FileService fileService;

    public static EditExhibitionRepository getInstance() {
        if (createExhibitionRepository == null) {
            createExhibitionRepository = new EditExhibitionRepository();
        }
        return createExhibitionRepository;
    }

    public EditExhibitionRepository() {
        exhibitionService = RetrofitConnect.createRetrofitConnection(ExhibitionService.class);
        fileService = RetrofitConnect.createRetrofitConnection(FileService.class);
        exhibitService = RetrofitConnect.createRetrofitConnection(ExhibitService.class);
    }

    public MutableLiveData<ExistingExhibition> editExhibition(ExistingExhibition exhibition, File file) {
        MutableLiveData<ExistingExhibition> newsData = new MutableLiveData<>();
        String name = "";
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), "");
        if (file != null) {
            name = "image";
            requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        }
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("imageUpload", name, requestFile);
        exhibitionService.updateExhibition(filePart, exhibition)
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


        return newsData;
    }


    public MutableLiveData<List<ExistingExhibit>> getExhibitsFromExhibition(Integer idExhibition) {
        MutableLiveData<List<ExistingExhibit>> newsData = new MutableLiveData<>();
        exhibitService.getExhibitsByExhibitionId(idExhibition)
                .enqueue(new Callback<List<ExistingExhibit>>() {
                    @Override
                    public void onResponse(Call<List<ExistingExhibit>> call, Response<List<ExistingExhibit>> response) {
                        if (response.isSuccessful()) {
                            newsData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ExistingExhibit>> call, Throwable t) {
                        newsData.setValue(null);
                    }
                });
        return newsData;
    }

    public MutableLiveData<AnswerModel> deleteExhibit(Integer idExhibition) {
        MutableLiveData<AnswerModel> newsData = new MutableLiveData<>();

        exhibitService.deleteExhibit(idExhibition)
                .enqueue(new Callback<AnswerModel>() {
                    @Override
                    public void onResponse(Call<AnswerModel> call, Response<AnswerModel> response) {
                        if (response.isSuccessful()) {
                            newsData.setValue(response.body());
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
