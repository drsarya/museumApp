package com.example.museums.view.activities.common.Authorization;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.MuseumFacadeImpl;
import com.example.museums.API.interfaces.impl.UserFacadeImpl;
import com.example.museums.API.models.Museum;
import com.example.museums.API.models.User;
import com.example.museums.view.activities.AdminTab;
import com.example.museums.view.activities.MuseumTab;
import com.example.museums.view.activities.UserTab;

public class QueryAuthorization {

    private Authorization activity;
    private MuseumDao memsDao;
    private UserFacadeImpl userFacade;
    private MuseumFacadeImpl museumFacade;
    private String login;
    private String password;


    public QueryAuthorization(Authorization context) {
        this.activity = context;

    }

    public void setUserInfo(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void isMuseum(Museum museum) {
        activity.authBtn.setVisibility(View.VISIBLE);
        activity.progressBar.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(activity.getApplicationContext(), MuseumTab.class);
        intent.putExtra(MuseumTab.LOGIN_KEY, login);

        activity.startActivity(intent);
    }

    public void isUser() {

        activity.progressBar.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(activity.getApplicationContext(), UserTab.class);
        intent.putExtra(UserTab.LOGIN_KEY, login);
        activity.startActivity(intent);
    }


    public void onSuccess(User user) {
        museumFacade = new MuseumFacadeImpl(memsDao, this);
        if (user.type) {
            activity.progressBar.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(activity.getApplicationContext(), AdminTab.class);
             activity.startActivity(intent);

        } else {
            museumFacade.getMuseumByLogin(user.login);
        }
    }

    public void onError() {
        Toast.makeText(activity.getApplicationContext(),
                "ошибка входа", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.INVISIBLE);

    }


    public void getQuery() {

        memsDao = ((AppDelegate) activity.getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        userFacade = new UserFacadeImpl(memsDao, this);
        userFacade.getUser(login, password);

    }


}
