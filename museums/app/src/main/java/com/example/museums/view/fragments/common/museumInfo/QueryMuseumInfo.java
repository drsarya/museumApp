package com.example.museums.view.fragments.common.museumInfo;

import android.widget.Toast;

import com.example.museums.API.services.impl.MuseumFacadeImpl;

public class QueryMuseumInfo {
    private MainInfoMuseum activity;
    private MuseumFacadeImpl museumFacade;
    private MuseumDao museumDao;

    public QueryMuseumInfo(MainInfoMuseum museumExhibits) {
        this.activity = museumExhibits;
    }

    public void onSuccess(Museum museum) {
        activity.setData(museum);
    }


    public void onError() {
        Toast.makeText(activity.getContext(),
                "Ошибка получения данных", Toast.LENGTH_SHORT).show();
    }

    public void getQuery(Integer id) {
        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        museumFacade = new MuseumFacadeImpl(museumDao, this);
        museumFacade.getMuseumInfoById(id);
    }
}
