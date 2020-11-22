package com.example.museums.view.activities.common.RegistrationMuseum;

import android.view.View;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.MuseumFacadeImpl;

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

    public void getQuery(String login, String password,boolean type , int idCode) {
        System.out.println(1);
        memsDao = ((AppDelegate) activity.getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        museumFacade = new MuseumFacadeImpl(memsDao, this);
        museumFacade.getMuseumByLoginAndIdCode(login, idCode, password,type ) ;

    }
}
