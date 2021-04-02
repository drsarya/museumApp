package com.example.museums.view.fragments.museum.mainInfoMuseumEditPage.DialogChangeDescriptionMuseum;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.OkModel;
import com.example.museums.API.models.museum.BaseMuseum;
import com.example.museums.API.models.museum.UpdatableMuseum;
import com.example.museums.API.services.api.MuseumService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DChangeDescriptionMuseumRepo {
    private static DChangeDescriptionMuseumRepo museumRepo;
    private MuseumService museumService;

    public static DChangeDescriptionMuseumRepo getInstance() {
        if (museumRepo == null) {
            museumRepo = new DChangeDescriptionMuseumRepo();
        }
        return museumRepo;
    }

    public DChangeDescriptionMuseumRepo() {
        museumService = RetrofitConnect.createRetrofitConnection(MuseumService.class);
    }

    public MutableLiveData<OkModel> updateDescription(UpdatableMuseum updatableMuseum) {

        MutableLiveData<OkModel> newsData = new MutableLiveData<>();
        museumService.updateMuseum(updatableMuseum)
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
