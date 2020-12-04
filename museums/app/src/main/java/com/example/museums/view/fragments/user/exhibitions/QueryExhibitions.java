package com.example.museums.view.fragments.user.exhibitions;

import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.ExhibitFacadeImpl;
import com.example.museums.API.interfaces.impl.ExhibitionFacadeImpl;
import com.example.museums.API.models.Exhibition;
import com.example.museums.API.models.ExhibitionWithMuseumName;
import com.example.museums.API.models.NewExhibitModel;
import com.example.museums.view.fragments.user.exhibits.Exhibits;

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
