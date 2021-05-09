package com.example.museums.view.fragments.museum.museumExhibitions;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.ErrorModel;
import com.example.museums.API.models.exhibition.ExistingExhibition;
import com.example.museums.API.services.ErrorParser;
import com.example.museums.API.services.api.ExhibitionService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MuseumExhibitionsRepository {
    private static MuseumExhibitionsRepository allMuseumsRepository;
    private ExhibitionService exhibitionService;

    public static MuseumExhibitionsRepository getInstance() {
        if (allMuseumsRepository == null) {
            allMuseumsRepository = new MuseumExhibitionsRepository();
        }
        return allMuseumsRepository;
    }

    public MuseumExhibitionsRepository() {
        exhibitionService = RetrofitConnect.createRetrofitConnection(ExhibitionService.class);
    }

    public MutableLiveData<List<ExistingExhibition>> getExhibitions(Integer id) {
        MutableLiveData<List<ExistingExhibition>> newsData = new MutableLiveData<>();
        exhibitionService.getExhibitionsByMuseumId(id)
                .enqueue(new Callback<List<ExistingExhibition>>() {
                    @Override
                    public void onResponse(Call<List<ExistingExhibition>> call, Response<List<ExistingExhibition>> response) {
                        if (response.isSuccessful()) {
                            newsData.setValue(response.body());
                        } else {
                            Gson gson = new Gson();
                            ErrorModel[] message = gson.fromJson(response.errorBody().charStream(), ErrorModel[].class);
                            String str = "";
                            for (int i = 0; i < message.length; i++) {
                                str += message[i].getMessage() + "\n";
                            }
                            System.out.println(str);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ExistingExhibition>> call, Throwable t) {
                        newsData.setValue(null);
                    }
                });
        return newsData;
    }

    public MutableLiveData<AnswerModel> deleteExhibition(Integer id) {
        MutableLiveData<AnswerModel> newsData = new MutableLiveData<>();
        exhibitionService.deleteExhibition(id)
                .enqueue(new Callback<AnswerModel>() {
                    @Override
                    public void onResponse(Call<AnswerModel> call,
                                           Response<AnswerModel> response) {
                        if (response.isSuccessful()) {
                            newsData.setValue(response.body());

                        } else {
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
