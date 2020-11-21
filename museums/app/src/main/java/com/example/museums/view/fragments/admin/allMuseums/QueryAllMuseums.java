package com.example.museums.view.fragments.admin.allMuseums;

import android.view.View;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.MuseumFacadeImpl;
import com.example.museums.API.models.Museum;

import java.util.List;

public class QueryAllMuseums {
    private AllMuseums activity;
    private MuseumFacadeImpl museumFacade;
    private MuseumDao memsDao;

    public QueryAllMuseums(AllMuseums activity) {
        this.activity = activity;
    }

    public void onSuccess(List<Museum> listM) {
        activity.progressBar.setVisibility(View.GONE);
        activity.refreshAllList(listM);

    }

    public void onError() {
        Toast.makeText(activity.getContext(),
                "Ошибка получения данных", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

    }

    public void getQuery() {

        memsDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        museumFacade = new MuseumFacadeImpl(memsDao, this);
        museumFacade.getAllMuseums();
    }

}
