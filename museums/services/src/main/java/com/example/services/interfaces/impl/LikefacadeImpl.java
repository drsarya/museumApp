package com.example.services.interfaces.impl;

import com.example.services.MuseumDao;
import com.example.services.interfaces.LikeFacade;
import com.example.services.models.Like;


public class LikefacadeImpl implements LikeFacade {
    private MuseumDao museumDao;

    public LikefacadeImpl(MuseumDao museumDao) {
        museumDao = museumDao;
    }

    @Override
    public String getLikesByExhId(String exhbtnId) {
        String count = museumDao.getLikesByExhId(exhbtnId);
        return count;
    }


    @Override
    public int deleteLikesByExhbtId(String iduser, String idExhb) {
        Like like = new Like();
        like.login = iduser;
        like.idExhb = idExhb;
        int res = museumDao.deleteLikesByExhbtId(like);
        return res;
    }

    @Override
    public long insertLike(String iduser, String idExhb) {
        Like like = new Like();
        like.login = iduser;
        like.idExhb = idExhb;
        long res = museumDao.insertLike(like);
        return res;
    }
}
