package com.example.museums.view.activities.common.Authorization;

import androidx.lifecycle.MutableLiveData;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.user.ExistingUser;
import com.example.museums.API.models.user.NewUser;
import com.example.museums.API.services.api.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorizationRepository {
    private static AuthorizationRepository authorizationRepository;
    private UserService userService;

    public static AuthorizationRepository getInstance() {
        if (authorizationRepository == null) {
            authorizationRepository = new AuthorizationRepository();
        }
        return authorizationRepository;
    }

    public AuthorizationRepository() {
        userService = RetrofitConnect.createRetrofitConnection(UserService.class);
    }

    public MutableLiveData<ExistingUser> getUser(String login, String password) {
        MutableLiveData<ExistingUser> newsData = new MutableLiveData<>();
        userService.getUser(new NewUser(login, password))
                .enqueue(new Callback<ExistingUser>() {
                    @Override
                    public void onResponse(Call<ExistingUser> call,
                                           Response<ExistingUser> response) {
                        if (response.isSuccessful()) {
                            newsData.setValue(response.body());
                        } else {
                            newsData.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ExistingUser> call, Throwable t) {
                        newsData.setValue(null);
                    }
                });
        return newsData;
    }

}
