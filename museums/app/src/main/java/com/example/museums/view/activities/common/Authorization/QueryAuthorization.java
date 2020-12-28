package com.example.museums.view.activities.common.Authorization;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.MuseumFacadeImpl;
import com.example.museums.API.interfaces.impl.UserFacadeImpl;
import com.example.museums.API.models.User;
import com.example.museums.view.activities.tabs.AdminTab;
import com.example.museums.view.activities.tabs.MuseumTab;
import com.example.museums.view.activities.tabs.UserTab;


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

    public void isMuseum(Integer id) {
        activity.authBtn.setVisibility(View.VISIBLE);
        activity.progressBar.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(activity.getApplicationContext(), MuseumTab.class);
        intent.putExtra(MuseumTab.LOGIN_KEY_USER, login);
        activity.startActivity(intent);
    }

    public void isUser() {
        activity.progressBar.setVisibility(View.GONE);
        Intent intent = new Intent(activity.getApplicationContext(), UserTab.class);
        intent.putExtra(UserTab.ID_USER_KEY, idUser);
        intent.putExtra(UserTab.LOGIN_USER_KEY, login);

        activity.startActivity(intent);
    }

    private Integer idUser;

    public void onSuccess(User user) {
        idUser = user.id;
        System.out.println(user.id +"dddddddddddddddddddddddddddddddddddddddddd");
        museumFacade = new MuseumFacadeImpl(memsDao, this);
        if (user.type) {
            activity.progressBar.setVisibility(View.GONE);

            Intent intent = new Intent(activity.getApplicationContext(), AdminTab.class);
            intent.putExtra(AdminTab.LOGIN_USER_KEY, login);
            activity.startActivity(intent);
        } else {
            museumFacade.getMuseumByLogin(user.login);
        }
    }
    public void onSuccessInsertAdmin() {
        activity.progressBar.setVisibility(View.GONE);
        Toast.makeText(activity.getApplicationContext(),
                "Успешное добавление администратора", Toast.LENGTH_SHORT).show();

    }
    public void onErrorInsertAdmin() {
        Toast.makeText(activity.getApplicationContext(),
                "ошибка добавления администратора", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);
    }
    public void onError() {
        Toast.makeText(activity.getApplicationContext(),
                "ошибка входа", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);
    }


    public void insertAdmin()  {

        memsDao = ((AppDelegate) activity.getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        userFacade = new UserFacadeImpl(memsDao, this);
        String pass = "1111";

        userFacade.insertUser("1111", pass, true);

    }

    public void getQuery() {

        memsDao = ((AppDelegate) activity.getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        userFacade = new UserFacadeImpl(memsDao, this);
        userFacade.getUser(login, password);
    }

}
