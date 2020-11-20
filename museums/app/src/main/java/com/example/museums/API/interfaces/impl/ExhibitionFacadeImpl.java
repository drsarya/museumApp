package com.example.museums.API.interfaces.impl;

import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.ExhibitionFacade;
import com.example.museums.API.models.Exhibition;

import java.util.List;

public class ExhibitionFacadeImpl implements ExhibitionFacade {

    private MuseumDao museumDao;

    public ExhibitionFacadeImpl(MuseumDao museumDao) {
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
