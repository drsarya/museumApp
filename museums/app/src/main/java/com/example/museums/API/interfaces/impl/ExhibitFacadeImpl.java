package com.example.museums.API.interfaces.impl;

import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.ExhibitFacade;
import com.example.museums.API.models.Exhibit;

import java.util.List;

public class ExhibitFacadeImpl implements ExhibitFacade {
    private MuseumDao museumDao;

    public ExhibitFacadeImpl(MuseumDao museumDao) {
        museumDao = museumDao;
    }

    @Override
    public List<Exhibit> getAllExhibits() {
        List<Exhibit> list = museumDao.getAllExhibits();
        return list;
    }

    @Override
    public long[] insertExhbt(List<Exhibit> exhibits) {

        long[] res = museumDao.insertExhbt(exhibits);

        return res;
    }
}
