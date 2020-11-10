package com.example.services.interfaces.impl;

import com.example.services.MuseumDao;
import com.example.services.interfaces.UserFacade;
import com.example.services.models.User;

public class UserFacadeImpl implements UserFacade {
    private MuseumDao museumDao;

    public UserFacadeImpl(MuseumDao museumDao) {
        museumDao = museumDao;
    }


    @Override
    public long getUser(String login, String password) {
        long res = museumDao.getUser(login, password);
        return res;
    }

    @Override
    public long insertUser(String login, String password) {
        User user = new User();
        user.login = login;
        user.password = password;
        long res = museumDao.insertUser(user);

        return res;
    }
}
