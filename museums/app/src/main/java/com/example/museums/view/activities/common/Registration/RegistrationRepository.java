package com.example.museums.view.activities.common.Registration;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.OkModel;
import com.example.museums.API.models.enums.RoleEnum;
import com.example.museums.API.models.user.NewUser;
import com.example.museums.API.services.api.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationRepository {
    private static RegistrationRepository registrationRepository;

    public static RegistrationRepository getInstance() {
        if (registrationRepository == null) {
            registrationRepository = new RegistrationRepository();
        }
        return registrationRepository;
    }

    private UserService userService;

    public RegistrationRepository() {
        userService = RetrofitConnect.createRetrofitConnection(UserService.class);
    }

    public MutableLiveData<OkModel> createUser(String login, String password) {
        MutableLiveData<OkModel> newsData = new MutableLiveData<>();
        userService.createUser(new NewUser(login, password, RoleEnum.USER))
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
