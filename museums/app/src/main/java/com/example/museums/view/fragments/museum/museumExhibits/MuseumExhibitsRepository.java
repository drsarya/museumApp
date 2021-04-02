package com.example.museums.view.fragments.museum.museumExhibits;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.OkModel;
import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.API.models.museum.BaseMuseum;
import com.example.museums.API.services.api.ExhibitService;
import com.example.museums.API.services.api.MuseumService;
import com.example.museums.view.fragments.admin.createMuseum.CreateMuseumRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MuseumExhibitsRepository {
    private static MuseumExhibitsRepository allMuseumsRepository;
    private ExhibitService exhibitService;

    public static MuseumExhibitsRepository getInstance() {
        if (allMuseumsRepository == null) {
            allMuseumsRepository = new MuseumExhibitsRepository();
        }
        return allMuseumsRepository;
    }

    public MuseumExhibitsRepository() {
        exhibitService = RetrofitConnect.createRetrofitConnection(ExhibitService.class);
    }

    public MutableLiveData<List<ExistingExhibit>> getExhibits(Integer id) {
        MutableLiveData<List<ExistingExhibit>> newsData = new MutableLiveData<>();
        exhibitService.getExhibitsByMuseumId(id)
                .enqueue(new Callback<List<ExistingExhibit>>() {
                    @Override
                    public void onResponse(Call<List<ExistingExhibit>> call,
                                           Response<List<ExistingExhibit>> response) {
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

    public MutableLiveData<OkModel> deleteExhibit(Integer id) {
        MutableLiveData<OkModel> newsData = new MutableLiveData<>();
        exhibitService.deleteExhibit(id)
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
