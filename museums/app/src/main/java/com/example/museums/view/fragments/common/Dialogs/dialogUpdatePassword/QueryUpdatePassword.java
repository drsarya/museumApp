package com.example.museums.view.fragments.common.Dialogs.dialogUpdatePassword;

import android.view.View;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.UserFacade;
import com.example.museums.API.interfaces.impl.MuseumFacadeImpl;
import com.example.museums.API.interfaces.impl.UserFacadeImpl;
import com.example.museums.API.models.Museum;
import com.example.museums.view.fragments.admin.allMuseums.AllMuseums;

import java.util.List;

public class QueryUpdatePassword {
    private DialogUpdatePassword activity;
    private MuseumDao memsDao;
    private UserFacadeImpl userFacade;

    public QueryUpdatePassword(DialogUpdatePassword activity) {
        this.activity = activity;
    }

    public void onSuccess() {
        Toast.makeText(activity.getContext(),
                "Пароль успешно обновлён", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

    }

    public void onError() {
        Toast.makeText(activity.getContext(),
                "Проверьте введённые данные", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

    }

    public void getQuery(String login, String password, String newPass) {

        memsDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        userFacade = new UserFacadeImpl(memsDao, this);
        userFacade.getUser(login, password, newPass);
    }
}