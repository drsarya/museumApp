package com.example.museums.view.fragments.user.exhibits;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.API.models.museum.ExistingMuseum;
import com.example.museums.API.services.api.ExhibitService;
import com.example.museums.API.services.api.MuseumService;
import com.example.museums.view.fragments.user.exhibitions.ExhibitionsRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExhibitsRepository {
    private ExhibitService exhibitService;
    private MuseumService museumService;
    private static ExhibitsRepository repository;

    public static ExhibitsRepository getInstance() {
        if (repository == null) {
            repository = new ExhibitsRepository();
        }
        return repository;
    }

    public ExhibitsRepository() {
        museumService = RetrofitConnect.createRetrofitConnection(MuseumService.class);
        exhibitService = RetrofitConnect.createRetrofitConnection(ExhibitService.class);
    }

    public MutableLiveData<List<ExistingExhibit>> getExhibits() {
        MutableLiveData<List<ExistingExhibit>> newsData = new MutableLiveData<>();
        exhibitService.getAllExhibits()
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

    public MutableLiveData<List<ExistingExhibit>> getMuseumExhibits(Integer id) {
        MutableLiveData<List<ExistingExhibit>> newsData = new MutableLiveData<>();
        exhibitService.getExhibitsByMuseumId(id)
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

    public MutableLiveData<List<ExistingMuseum>> getMuseums() {
        MutableLiveData<List<ExistingMuseum>> newsData = new MutableLiveData<>();
        museumService.getAllMuseums()
                .enqueue(new Callback<List<ExistingMuseum>>() {
                    @Override
                    public void onResponse(Call<List<ExistingMuseum>> call, Response<List<ExistingMuseum>> response) {
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
