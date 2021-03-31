package com.example.museums.API.services.api;

import com.example.museums.API.models.OkModel;
import com.example.museums.API.models.user.ExistingUser;
import com.example.museums.API.models.user.NewUser;
import com.example.museums.API.models.user.UserMuseum;
import com.example.museums.API.models.user.UserUpdate;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserService {
//    @POST("users")
//    Single<User> createUser(@Body NewUser task);
//
//    @PUT("users")
//    Single<Boolean> updatePassword(@Body UserUpdate task);
//
//    @POST("users/get")
//    Single<User> getUser(@Body NewUser task);
//
//    @GET("users/{login}")
//    Single<User> getUserMuseum(@Path("login") String login);

    @POST("users/get")
    Single<ExistingUser> getUser(@Body NewUser user);

    @PUT("users")
    Single<OkModel> updateUserPassword(@Body UserUpdate user);

    @POST("users")
    Single<OkModel> createUser(@Body NewUser user);

    @PUT("users/museum")
    Single<OkModel> updateMuseumUserPass(@Body UserMuseum user);
}
