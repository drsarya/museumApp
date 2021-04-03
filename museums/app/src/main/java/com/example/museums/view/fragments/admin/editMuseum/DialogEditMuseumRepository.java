package com.example.museums.view.fragments.admin.editMuseum;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.museum.ExistingMuseum;
import com.example.museums.API.models.museum.UpdatableMuseum;
import com.example.museums.API.models.museum.UpdatableMuseumAdmin;
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
        UpdatableMuseumAdmin updatableMuseum = new UpdatableMuseumAdmin();
        updatableMuseum.setId(id);
        updatableMuseum.setAddress(address);
        updatableMuseum.setNameMuseum(name);
        museumService.updateMuseumByAdmin(updatableMuseum)
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

    public MutableLiveData<ExistingMuseum> getMuseum(Integer id) {
        MutableLiveData<ExistingMuseum> newsData = new MutableLiveData<>();

        museumService.getMuseumById(id)
                .enqueue(new Callback<ExistingMuseum>() {
                    @Override
                    public void onResponse(Call<ExistingMuseum> call, Response<ExistingMuseum> response) {
                        if (response.isSuccessful()) {
                            newsData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<ExistingMuseum> call, Throwable t) {
                        newsData.setValue(null);
                    }
                });
        return newsData;
    }

    public MutableLiveData<AnswerModel> deleteMuseum(Integer id) {
        MutableLiveData<AnswerModel> newsData = new MutableLiveData<>();
        museumService.deleteMuseum(id)
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

    public MutableLiveData<AnswerModel> lockMuseum(Integer id) {
        MutableLiveData<AnswerModel> newsData = new MutableLiveData<>();

        museumService.lockMuseum(id)
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

    public MutableLiveData<AnswerModel> getOwner(Integer id) {
        MutableLiveData<AnswerModel> newsData = new MutableLiveData<>();

        museumService.getOwnerByMuseumId(id)
                .enqueue(new Callback<AnswerModel>() {
                    @Override
                    public void onResponse(Call<AnswerModel> call, Response<AnswerModel> response) {
                        if (response.isSuccessful()) {
                            newsData.setValue(response.body() );
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
