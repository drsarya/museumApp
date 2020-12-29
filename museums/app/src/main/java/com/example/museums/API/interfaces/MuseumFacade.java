package com.example.museums.API.interfaces;

import android.graphics.Bitmap;


public interface MuseumFacade {
    /*GET*/
    void getMuseumByLogin(String login);

    void getMuseumImageByLogin(String login);

    void getMuseumIDByLogin(String login);

    void getMuseumInfoByLogin(String login);

    void getMuseumInfoById(Integer id);


    void getAllMuseums();

    void getMuseumByLoginAndIdCode(String login, int id);

    /*INSERT*/
    void insertMuseum(String login, String name, String address);

    /*UPDATE*/
    void updateMuseumImage(String login, Bitmap image);

    void updateMuseumDescription(String login, String description);

    void updateMuseumInfoByAdmin(String name, String address, int id);


}
