package com.example.museums.view.activities.common.RegistrationMuseum;

import android.view.View;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.MuseumFacadeImpl;
import com.example.museums.API.interfaces.impl.UserFacadeImpl;
import com.example.museums.view.services.ConfigEncrypt;

public class QueryRegistrationMuseum {

    private RegistrationMuseum activity;
    private MuseumFacadeImpl museumFacade;
    private MuseumDao memsDao;

    public QueryRegistrationMuseum(RegistrationMuseum activity) {
        this.activity = activity;
    }


    public void onSuccess() {
        Toast.makeText(activity.getApplicationContext(),
                "Успешная регистрация", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);
    }

    public void onError() {
        Toast.makeText(activity.getApplicationContext(),
                "Ошибка регистрации", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

    }

    public void updateMuseumPassword() {
        UserFacadeImpl userFacade = new UserFacadeImpl(memsDao, this);

        userFacade.updateUserPassword(login,null, password);

    }

    private String password, login;

    public void getQuery(String login, String password ,int idCode)  {
        this.login = login;

        memsDao = ((AppDelegate) activity.getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        museumFacade = new MuseumFacadeImpl(memsDao, this);
        this.password = password;

        museumFacade.getMuseumByLoginAndIdCode(login, idCode );
    }
}
