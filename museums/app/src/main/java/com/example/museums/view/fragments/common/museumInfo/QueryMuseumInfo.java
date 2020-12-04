package com.example.museums.view.fragments.common.museumInfo;

import android.view.View;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.ExhibitFacadeImpl;
import com.example.museums.API.interfaces.impl.MuseumFacadeImpl;
import com.example.museums.API.models.Museum;
import com.example.museums.API.models.NewExhibitModel;
import com.example.museums.view.fragments.museum.museumExhibits.MuseumExhibits;

import java.util.List;

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
