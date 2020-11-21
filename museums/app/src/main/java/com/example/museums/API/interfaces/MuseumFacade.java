package com.example.museums.API.interfaces;

import com.example.museums.API.models.Museum;


public interface MuseumFacade {

    void getMuseumByLogin(String login);

    void updateMuseumInfoByMuseum(String image, String description);
    void updateMuseumInfoByAdmin( String name, String address,  int id);

    void insertMuseum(String login, String name, String address );
    void getAllMuseums( );

}
