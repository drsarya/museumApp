package com.example.museums.API.interfaces;

import android.graphics.Bitmap;

import com.example.museums.API.models.Museum;


public interface MuseumFacade {
    /*GET*/
    void getMuseumByLogin(String login);

    void getMuseumImageByLogin(String login);

    void getMuseumIDByLogin(String login);

    void getMuseumInfoByLogin(String login);

    void getMuseumExhibitsById(int id);


    void getAllMuseums();

    void getMuseumByLoginAndIdCode(String login, int id, String password, boolean type);

    /*INSERT*/
    void insertMuseum(String login, String name, String address);

    /*UPDATE*/
    void updateMuseumImage(String login, Bitmap image);

    void updateMuseumDescription(String login, String description);

    void updateMuseumInfoByAdmin(String name, String address, int id);


}
