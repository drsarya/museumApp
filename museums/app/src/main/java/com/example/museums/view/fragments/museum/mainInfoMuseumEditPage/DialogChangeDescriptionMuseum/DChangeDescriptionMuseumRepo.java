package com.example.museums.view.fragments.museum.mainInfoMuseumEditPage.DialogChangeDescriptionMuseum;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.museum.UpdatableMuseum;
import com.example.museums.API.services.ErrorParser;
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

    public MutableLiveData<AnswerModel> updateDescription(UpdatableMuseum updatableMuseum) {

        MutableLiveData<AnswerModel> newsData = new MutableLiveData<>();
        museumService.updateMuseum(updatableMuseum)
                .enqueue(new Callback<AnswerModel>() {
                    @Override
                    public void onResponse(Call<AnswerModel> call,
                                           Response<AnswerModel> response) {
                        if (response.isSuccessful()) {
                            newsData.setValue(response.body());
                        }else {
                            newsData.setValue(new AnswerModel(ErrorParser.getMessage(response)));
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
