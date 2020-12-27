package com.example.museums.view.activities.common.Registration;

import android.view.View;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.UserFacadeImpl;
import com.example.museums.view.activities.common.Authorization.Authorization;
import com.example.museums.view.fragments.admin.createMuseum.CreateMuseum;
import com.example.museums.view.services.ConfigEncrypt;

import java.security.CryptoPrimitive;

public class QueryRegistration {
    private Registration activity;
    private UserFacadeImpl userFacade;
    private MuseumDao memsDao;

    public QueryRegistration(Registration activity) {
        this.activity = activity;

    }

    public void onSuccess() {
        Toast.makeText(activity.getApplicationContext(),
                "Успешная регистрация", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);
    }

    public void onError() {
        Toast.makeText(activity.getApplicationContext(),
                "Пользователь с  введёнными данными уже существует", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);
    }

    public void getQuery(String login, String password, boolean type) throws Exception {

        memsDao = ((AppDelegate) activity.getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        userFacade = new UserFacadeImpl(memsDao, this);
        String newHashPassword = ConfigEncrypt.getSaltedHash(password);
        System.out.println(newHashPassword+"ddddddddddddddddddddddddddddddddddddddddddddddddddddd");
        userFacade.insertUser(login, newHashPassword, type);
    }
}
