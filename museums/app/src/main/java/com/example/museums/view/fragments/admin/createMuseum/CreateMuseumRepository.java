package com.example.museums.view.fragments.admin.createMuseum;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.ErrorModel;
import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.museum.BaseMuseum;
import com.example.museums.API.services.api.MuseumService;
import com.google.gson.Gson;

import java.util.List;

import lombok.SneakyThrows;
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

    public MutableLiveData<AnswerModel> createMuseum(String name, String address, String login) {
        MutableLiveData<AnswerModel> newsData = new MutableLiveData<>();
        museumService.createMuseum(new BaseMuseum(name, address), login)
                .enqueue(new Callback<AnswerModel>() {
                    @SneakyThrows
                    @Override
                    public void onResponse(Call<AnswerModel> call,
                                           Response<AnswerModel> response) {
                        if (response.isSuccessful()) {
                            newsData.setValue(response.body());
                        } else {
                            Gson gson = new Gson();
                            ErrorModel[] message = gson.fromJson(response.errorBody().charStream(), ErrorModel[].class);
                            String str = "";
                            for (int i = 0; i < message.length; i++) {
                                str += message[i].getMessage() + "\n";
                            }
                            newsData.setValue(new AnswerModel(str));
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
