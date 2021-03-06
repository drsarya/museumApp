package com.example.museums.view.fragments.museum.exhibit.editExhibit;


import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.author.Author;
import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.API.services.api.AuthorService;
import com.example.museums.API.services.api.ExhibitService;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditExhibitRepository {
    private static EditExhibitRepository editExhibitRepository;
    private AuthorService authorService;
    private ExhibitService service;

    public static EditExhibitRepository getInstance() {
        if (editExhibitRepository == null) {
            editExhibitRepository = new EditExhibitRepository();
        }
        return editExhibitRepository;
    }


    public EditExhibitRepository() {
        service = RetrofitConnect.createRetrofitConnection(ExhibitService.class);
        authorService = RetrofitConnect.createRetrofitConnection(AuthorService.class);
    }


    public MutableLiveData<ExistingExhibit> updateExhibit(ExistingExhibit exhibit, File file) {
        MutableLiveData<ExistingExhibit> newsData = new MutableLiveData<>();
        String name = "";
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), "");
        if (file != null) {
            name = "image";
            requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        }
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("imageUpload", name, requestFile);
        service.updateExhibit(filePart, exhibit)
                .enqueue(new Callback<ExistingExhibit>() {
                    @Override
                    public void onResponse(Call<ExistingExhibit> call, Response<ExistingExhibit> response) {
                        if (response.isSuccessful()) {
                            newsData.setValue(response.body());
                        } else {

                            System.out.println(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<ExistingExhibit> call, Throwable t) {
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