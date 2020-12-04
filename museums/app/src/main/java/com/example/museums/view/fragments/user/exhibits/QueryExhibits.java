package com.example.museums.view.fragments.user.exhibits;

import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.ExhibitFacadeImpl;
import com.example.museums.API.models.NewExhibitModel;

import java.util.List;

public class QueryExhibits {
    private Exhibits activity;
    private ExhibitFacadeImpl exhibitFacade;
    private MuseumDao museumDao;

    public QueryExhibits(Exhibits museumExhibits) {
        this.activity = museumExhibits;
    }

    public void onSuccess(List<NewExhibitModel> exhibitModels) {
        activity.refreshAllList(exhibitModels);
    }


    public void onError() {
        Toast.makeText(activity.getContext(),
                "Ошибка получения данных", Toast.LENGTH_SHORT).show();
    }

    public void getQuery( ) {

        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        exhibitFacade = new ExhibitFacadeImpl(museumDao, this);
        exhibitFacade.getAllExhibits( );

    }
}
