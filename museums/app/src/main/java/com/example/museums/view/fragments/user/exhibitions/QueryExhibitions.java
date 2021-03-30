package com.example.museums.view.fragments.user.exhibitions;

import android.widget.Toast;

import com.example.museums.API.services.impl.ExhibitionFacadeImpl;

import java.util.List;

public class QueryExhibitions {
    private Exhibitions activity;
    private ExhibitionFacadeImpl exhibitFacade;
    private MuseumDao museumDao;

    public QueryExhibitions(Exhibitions museumExhibits) {
        this.activity = museumExhibits;
    }

    public void onSuccess(List<ExhibitionWithMuseumName> exhibitModels) {
        activity.refreshAllList(exhibitModels);
    }


    public void onError() {
        Toast.makeText(activity.getContext(),
                "Ошибка получения данных", Toast.LENGTH_SHORT).show();
    }

    public void getQuery() {

        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        exhibitFacade = new ExhibitionFacadeImpl(museumDao, this);
        exhibitFacade.getAllExhibitions();

    }
}
