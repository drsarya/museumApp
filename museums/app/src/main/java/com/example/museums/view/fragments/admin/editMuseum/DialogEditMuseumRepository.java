package com.example.museums.view.fragments.admin.editMuseum;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.museum.UpdatableMuseum;
import com.example.museums.API.services.api.MuseumService;

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

    public MutableLiveData<AnswerModel> editMuseum(String name, String address, Integer id) {
        MutableLiveData<AnswerModel> newsData = new MutableLiveData<>();
        UpdatableMuseum updatableMuseum = new UpdatableMuseum();
        updatableMuseum.setId( id );
        updatableMuseum.setAddress(address);
        updatableMuseum.setNameMuseum(name);
        museumService.updateMuseum(updatableMuseum)
                .enqueue(new Callback<AnswerModel>() {
                    @Override
                    public void onResponse(Call<AnswerModel> call, Response<AnswerModel> response) {
                        if (response.isSuccessful()) {
                            newsData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<AnswerModel> call, Throwable t) {
                        newsData.setValue(null);
                    }
                });
        return newsData;
    }
}
