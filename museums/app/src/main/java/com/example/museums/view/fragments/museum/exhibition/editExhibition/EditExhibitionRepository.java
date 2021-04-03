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

    public MutableLiveData<ExistingExhibition> editExhibition(ExistingExhibition exhibition, Bitmap bitmap) throws IOException {
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
                            exhibitionService.updateExhibition(exhibition)
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
