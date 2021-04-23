package com.example.museums.view.fragments.user.exhibitions;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.API.models.exhibition.ExistingExhibition;
import com.example.museums.API.services.api.ExhibitService;
import com.example.museums.API.services.api.ExhibitionService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExhibitionsRepository {
    private ExhibitionService exhibitService;
    private static ExhibitionsRepository repository;

    public static ExhibitionsRepository getInstance() {
        if (repository == null) {
            repository = new ExhibitionsRepository();
        }
        return repository;
    }

    public ExhibitionsRepository() {
        exhibitService = RetrofitConnect.createRetrofitConnection(ExhibitionService.class);
    }

    public MutableLiveData<List<ExistingExhibition>> getExhibitions() {
        MutableLiveData<List<ExistingExhibition>> newsData = new MutableLiveData<>();
        exhibitService.getAllExhibitions()
                .enqueue(new Callback<List<ExistingExhibition>>() {
                    @Override
                    public void onResponse(Call<List<ExistingExhibition>> call, Response<List<ExistingExhibition>> response) {
                        if (response.isSuccessful()) {
                            newsData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ExistingExhibition>> call, Throwable t) {
                        newsData.setValue(null);
                    }
                });
        return newsData;
    }
}
