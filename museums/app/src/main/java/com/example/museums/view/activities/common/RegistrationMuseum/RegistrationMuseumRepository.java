package com.example.museums.view.activities.common.RegistrationMuseum;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.user.UserMuseum;
import com.example.museums.API.services.ErrorParser;
import com.example.museums.API.services.api.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationMuseumRepository {
    private static RegistrationMuseumRepository registrationRepository;

    public static RegistrationMuseumRepository getInstance() {
        if (registrationRepository == null) {
            registrationRepository = new RegistrationMuseumRepository();
        }
        return registrationRepository;
    }

    private UserService userService;

    public RegistrationMuseumRepository() {
        userService = RetrofitConnect.createRetrofitConnection(UserService.class);
    }

    public MutableLiveData<AnswerModel> createMuseum(Integer idCode, String login, String password) {
        MutableLiveData<AnswerModel> newsData = new MutableLiveData<>();
        userService.updateMuseumUserPass(new UserMuseum(idCode, password,login  ))
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
                        newsData.setValue(null  );
                    }
                });
        return newsData;
    }
}
