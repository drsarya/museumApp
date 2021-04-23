package com.example.museums.view.fragments.user.likedExhibitions;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.exhibition.ExistingExhibition;
import com.example.museums.API.services.api.ExhibitionService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LikedExhibitionsRepository {
    private ExhibitionService exhibitService;
    private static LikedExhibitionsRepository repository;

    public static LikedExhibitionsRepository getInstance() {
        if (repository == null) {
            repository = new LikedExhibitionsRepository();
        }
        return repository;
    }

    public LikedExhibitionsRepository() {
        exhibitService = RetrofitConnect.createRetrofitConnection(ExhibitionService.class);
    }

    public MutableLiveData<List<ExistingExhibition>> getExhibitions(Integer id) {
        MutableLiveData<List<ExistingExhibition>> newsData = new MutableLiveData<>();
        exhibitService.getLikedExhibitionsByUser(id)
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
