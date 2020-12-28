package com.example.museums.API.interfaces;


import com.example.museums.API.models.NewUser;
import com.example.museums.API.models.User;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserFacade {

    /*GET*/
    void getUser(String login, String password );
    void getUserMuseum(String login );


    /*INSERT*/
    void insertUser(String login, String password, boolean type);

    void insertUserMuseum(String login, String password, boolean type);

    /*UPDATE*/
    void updateUserPassword(String login, String password, String newPassword);



}
