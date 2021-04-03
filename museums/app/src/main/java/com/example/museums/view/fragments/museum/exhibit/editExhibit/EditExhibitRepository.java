package com.example.museums.view.fragments.museum.exhibit.editExhibit;

import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.author.Author;
import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.API.services.BitmapConverter;
import com.example.museums.API.services.api.AuthorService;
import com.example.museums.API.services.api.ExhibitService;
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

public class EditExhibitRepository {
    private static EditExhibitRepository editExhibitRepository;
    private static FileService fileService;
    private AuthorService authorService;
    public static EditExhibitRepository getInstance() {
        if (editExhibitRepository == null) {
            editExhibitRepository = new EditExhibitRepository();
        }
        return editExhibitRepository;
    }

    private ExhibitService service;

    public EditExhibitRepository() {
        service = RetrofitConnect.createRetrofitConnection(ExhibitService.class);
        fileService = RetrofitConnect.createRetrofitConnection(FileService.class);
        authorService = RetrofitConnect.createRetrofitConnection(AuthorService.class);
    }

    public MutableLiveData<ExistingExhibit> updateExhibit(ExistingExhibit exhibit, Bitmap bitmap) throws IOException {
        MutableLiveData<ExistingExhibit> newsData = new MutableLiveData<>();
        File file = BitmapConverter.convertBitmapToFile(bitmap);
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("upload", file.getName(), reqFile);

        fileService.uploadImage(body)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            exhibit.setImageUrl(response.body());
                            service.updateExhibit(exhibit)
                                    .enqueue(new Callback<ExistingExhibit>() {
                                        @Override
                                        public void onResponse(Call<ExistingExhibit> call, Response<ExistingExhibit> response) {
                                            if (response.isSuccessful()) {
                                                newsData.setValue(response.body());
                                            }
                                        }
                                        @Override
                                        public void onFailure(Call<ExistingExhibit> call, Throwable t) {
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
    public MutableLiveData<List<Author>> getAllAuthors() {

        MutableLiveData<List<Author>> newsData = new MutableLiveData<>();
        authorService.getAuthors()
                .enqueue(new Callback<List<Author>>() {
                    @Override
                    public void onResponse(Call<List<Author>> call, Response<List<Author>> response) {
                        if (response.isSuccessful()) {
                            newsData.setValue(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Author>> call, Throwable t) {
                        newsData.setValue(null);
                    }
                });
        return newsData;
    }
}