package com.example.services.interfaces.impl;

import com.example.services.MuseumDao;
import com.example.services.interfaces.ExhibitFacade;
import com.example.services.models.Exhibit;

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
