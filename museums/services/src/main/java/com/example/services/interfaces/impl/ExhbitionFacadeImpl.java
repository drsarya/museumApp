package com.example.services.interfaces.impl;

import com.example.services.MuseumDao;
import com.example.services.interfaces.ExhibitionFacade;
import com.example.services.models.Exhibition;

import java.util.List;

public class ExhbitionFacadeImpl implements ExhibitionFacade {

    private MuseumDao museumDao;

    public ExhbitionFacadeImpl(MuseumDao museumDao) {
        museumDao = museumDao;
    }

    @Override
    public List<Exhibition> getAllExhibitions() {
        List<Exhibition> list = museumDao.getAllExhibitions();
        return list;
    }

    @Override
    public List<Exhibition> getExhbtnByMuseumId(String id) {
        List<Exhibition> list = museumDao.getExhbtnByMuseumId(id);
        return list;
    }

    @Override
    public long insertExhbtn(Exhibition exhibition) {
        long res = museumDao.insertExhbtn(exhibition);
        return res;
    }
}
