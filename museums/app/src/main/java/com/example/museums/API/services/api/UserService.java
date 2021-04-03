package com.example.museums.API.services.api;

import com.example.museums.API.models.AnswerModel;
import com.example.museums.API.models.user.ExistingUser;
import com.example.museums.API.models.user.NewUser;
import com.example.museums.API.models.user.UserMuseum;
import com.example.museums.API.models.user.UserUpdate;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserService {

    @POST("users/get")
    Call<ExistingUser> getUser(@Body NewUser user);

    @PUT("users")
    Call<AnswerModel> updateUserPassword(@Body UserUpdate user);

    @POST("users")
    Call<AnswerModel> createUser(@Body NewUser user);

    @PUT("users/museum")
    Call<AnswerModel> updateMuseumUserPass(@Body UserMuseum user);
}
