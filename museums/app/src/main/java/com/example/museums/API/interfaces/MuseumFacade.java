package com.example.museums.API.interfaces;

import com.example.museums.API.models.Museum;


public interface MuseumFacade {
    void getMuseumById(String id);

    void getMuseumByLogin(String login);

    int updateMuseumInfo(String image, String description);

    void insertMuseum(String login, String name, String country, String city, String street, String build
    );

}
