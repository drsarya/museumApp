package com.example.museums.view.fragments.common.museumInfo;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.museum.ExistingMuseum;
import com.example.museums.API.services.ErrorParser;
import com.example.museums.API.services.api.MuseumService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainInfoMuseumRepository {
    private static MainInfoMuseumRepository registrationRepository;

    public static MainInfoMuseumRepository getInstance() {
        if (registrationRepository == null) {
            registrationRepository = new MainInfoMuseumRepository();
        }
        return registrationRepository;
    }

    private MuseumService museumService;

    public MainInfoMuseumRepository() {
        museumService = RetrofitConnect.createRetrofitConnection(MuseumService.class);
    }

    public MutableLiveData<ExistingMuseum> getMuseumInfo(Integer id) {
        MutableLiveData<ExistingMuseum> newsData = new MutableLiveData<>();
        museumService.getMuseumById(id)
                .enqueue(new Callback<ExistingMuseum>() {
                    @Override
                    public void onResponse(Call<ExistingMuseum> call, Response<ExistingMuseum> response) {
                        if (response.isSuccessful()) {
                            newsData.setValue(response.body());
                        } else {
                            newsData.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ExistingMuseum> call, Throwable t) {
                        newsData.setValue(null);
                    }
                });
        return newsData;
    }
}
