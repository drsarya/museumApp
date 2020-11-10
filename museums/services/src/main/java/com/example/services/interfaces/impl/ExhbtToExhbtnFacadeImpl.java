package com.example.services.interfaces.impl;

import com.example.services.MuseumDao;
import com.example.services.interfaces.ExhbtToExbtnFacade;
import com.example.services.models.Exhibit;
import com.example.services.models.ExhibitToExhbtn;

import java.util.List;

public class ExhbtToExhbtnFacadeImpl implements ExhbtToExbtnFacade {
    private MuseumDao museumDao;

    public ExhbtToExhbtnFacadeImpl(MuseumDao museumDao) {
        museumDao = museumDao;
    }

    @Override
    public List<Exhibit> getExhibitsByExhdtnId(String ixhbtnId) {

        List<Exhibit> list = museumDao.getExhibitsByExhdtnId(ixhbtnId);
        return list;
    }

    @Override
    public long[] insertExhbToExbtn(List<ExhibitToExhbtn> exhbtns) {
        long[] arrStates = museumDao.insertExhbToExbtn(exhbtns);
        return arrStates;
    }


}
