package com.example.museums.view.fragments.admin.editMuseum;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.OkModel;
import com.example.museums.API.models.museum.BaseMuseum;
import com.example.museums.API.models.museum.UpdatableMuseum;
import com.example.museums.API.services.api.MuseumService;
import com.example.museums.view.fragments.admin.createMuseum.CreateMuseumRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogEditMuseumRepository {
    private static DialogEditMuseumRepository allMuseumsRepository;
    private MuseumService museumService;

    public static DialogEditMuseumRepository getInstance() {
        if (allMuseumsRepository == null) {
            allMuseumsRepository = new DialogEditMuseumRepository();
        }
        return allMuseumsRepository;
    }

    public DialogEditMuseumRepository() {
        museumService = RetrofitConnect.createRetrofitConnection(MuseumService.class);
    }

    public MutableLiveData<OkModel> editMuseum(String name, String address, String id) {
        MutableLiveData<OkModel> newsData = new MutableLiveData<>();
        UpdatableMuseum updatableMuseum = new UpdatableMuseum();
        updatableMuseum.setId(Integer.parseInt(id));
        updatableMuseum.setAddress(address);
        updatableMuseum.setNameMuseum(name);
        museumService.updateMuseum(updatableMuseum)
                .enqueue(new Callback<OkModel>() {
                    @Override
                    public void onResponse(Call<OkModel> call, Response<OkModel> response) {
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
