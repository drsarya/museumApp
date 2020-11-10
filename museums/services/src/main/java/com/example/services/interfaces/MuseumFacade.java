package com.example.services.interfaces;

import com.example.services.models.Museum;


public interface MuseumFacade {
    Museum getMuseumById(String id);

    Museum getMuseumByLogin(String login);

    int updateMuseumInfo(String image, String description);

}
