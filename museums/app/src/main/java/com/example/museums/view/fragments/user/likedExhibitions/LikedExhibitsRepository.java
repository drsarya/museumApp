package com.example.museums.view.fragments.user.likedExhibitions;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.API.services.api.ExhibitService;
import com.example.museums.API.services.api.LikeService;
import com.example.museums.view.fragments.user.exhibits.ExhibitsRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LikedExhibitsRepository {
    private ExhibitService exhibitService;
    private static LikedExhibitsRepository repository;

    public static LikedExhibitsRepository getInstance() {
        if (repository == null) {
            repository = new LikedExhibitsRepository();
        }
        return repository;
    }

    public LikedExhibitsRepository() {
        exhibitService = RetrofitConnect.createRetrofitConnection(ExhibitService.class);
    }

    public MutableLiveData<List<ExistingExhibit>> getExhibits(Integer id) {
        MutableLiveData<List<ExistingExhibit>> newsData = new MutableLiveData<>();
        exhibitService.getLikedExhibitsByUser(id)
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
}
