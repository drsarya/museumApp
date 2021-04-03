package com.example.museums.view.fragments.admin.allMuseums;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.museum.ExistingMuseum;
import com.example.museums.API.services.api.MuseumService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllMuseumsRepository {
    private static AllMuseumsRepository allMuseumsRepository;
    private MuseumService museumService;

    public static AllMuseumsRepository getInstance() {
        if (allMuseumsRepository == null) {
            allMuseumsRepository = new AllMuseumsRepository();
        }
        return allMuseumsRepository;
    }

    public AllMuseumsRepository() {
        museumService = RetrofitConnect.createRetrofitConnection(MuseumService.class);
    }

    public MutableLiveData<List<ExistingMuseum>> allMuseums() {
        MutableLiveData<List<ExistingMuseum>> newsData = new MutableLiveData<>();
        museumService.getAllMuseums()
                .enqueue(new Callback<List<ExistingMuseum>>() {
                    @Override
                    public void onResponse(Call<List<ExistingMuseum>> call,
                                           Response<List<ExistingMuseum>> response) {
                        if (response.isSuccessful()) {
                            newsData.setValue(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<ExistingMuseum>> call, Throwable t) {
                        newsData.setValue(null);
                    }
                });
        return newsData;
    }
}
