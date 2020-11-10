package com.example.services.interfaces.impl;

import com.example.services.MuseumDao;
import com.example.services.interfaces.MuseumFacade;
import com.example.services.models.Museum;

public class MuseumFacadeImpl implements MuseumFacade {
    private MuseumDao museumDao;

    public MuseumFacadeImpl(MuseumDao museumDao) {
        museumDao = museumDao;
    }

    @Override
    public Museum getMuseumById(String id) {
        Museum museum = museumDao.getMuseumById(id);

        return museum;
    }

    @Override
    public Museum getMuseumByLogin(String login) {
        Museum museum = museumDao.getMuseumByLogin(login);

        return museum;
    }

    @Override
    public int updateMuseumInfo(String image, String description) {
        Museum museum = new Museum();
        museum.description = description;
        museum.image = image;
        int res = museumDao.updateMuseumInfo(museum);

        return res;
    }
}
