package com.example.museums.API.interfaces;

import com.example.museums.API.models.NewUser;
import com.example.museums.API.models.User;
import com.example.museums.API.models.UserUpdate;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserApi {

    @POST("users")
    Single<User> createUser(@Body NewUser task);

    @PUT("users")
    Single<Boolean> updatePassword(@Body UserUpdate task);

    @POST("users/get")
    Single<User> getUser(@Body NewUser task);

    @GET("users/{login}")
    Single<User> getUserMuseum(@Path("login") String login);

}
