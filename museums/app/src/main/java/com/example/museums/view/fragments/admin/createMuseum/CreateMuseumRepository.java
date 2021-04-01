package com.example.museums.view.fragments.admin.createMuseum;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.OkModel;
import com.example.museums.API.models.museum.BaseMuseum;
import com.example.museums.API.models.museum.ExistingMuseum;
import com.example.museums.API.services.api.MuseumService;
import com.example.museums.view.fragments.admin.allMuseums.AllMuseumsRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateMuseumRepository {
    private static CreateMuseumRepository allMuseumsRepository;
    private MuseumService museumService;

    public static CreateMuseumRepository getInstance() {
        if (allMuseumsRepository == null) {
            allMuseumsRepository = new CreateMuseumRepository();
        }
        return allMuseumsRepository;
    }

    public CreateMuseumRepository() {
        museumService = RetrofitConnect.createRetrofitConnection(MuseumService.class);
    }

    public MutableLiveData<OkModel> createMuseum(String name, String address, String login) {
        MutableLiveData<OkModel> newsData = new MutableLiveData<>();
        museumService.createMuseum(new BaseMuseum(name, address), login)
                .enqueue(new Callback<OkModel>() {
                    @Override
                    public void onResponse(Call<OkModel> call,
                                           Response<OkModel> response) {
                        if (response.isSuccessful()) {
                            newsData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<OkModel> call, Throwable t) {
                        newsData.setValue(null);
                    }
                });
        return newsData;
    }
}
