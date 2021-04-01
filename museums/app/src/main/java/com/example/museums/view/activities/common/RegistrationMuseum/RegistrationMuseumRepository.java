package com.example.museums.view.activities.common.RegistrationMuseum;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.OkModel;
import com.example.museums.API.models.enums.RoleEnum;
import com.example.museums.API.models.user.NewUser;
import com.example.museums.API.models.user.UserMuseum;
import com.example.museums.API.services.api.UserService;
import com.example.museums.view.activities.common.Registration.RegistrationRepository;

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

    public MutableLiveData<OkModel> createMuseum(Integer idCode, String login, String password) {
        MutableLiveData<OkModel> newsData = new MutableLiveData<>();
        userService.updateMuseumUserPass(new UserMuseum(idCode, password,login  ))
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
                        newsData.setValue(null  );
                    }
                });
        return newsData;
    }
}
