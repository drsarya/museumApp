package com.example.museums.view.fragments.museum.museumExhibits;

import android.view.View;
import android.widget.Toast;

import com.example.museums.API.services.impl.ExhibitFacadeImpl;

import java.util.List;

public class QueryListMuseumExhibits {
    private MuseumExhibits activity;

    private ExhibitFacadeImpl exhibitFacadel;
    private MuseumDao museumDao;

    public QueryListMuseumExhibits(MuseumExhibits museumExhibits) {
        this.activity = museumExhibits;
    }


    public void onSuccess(List<NewExhibitModel> exhibits) {
        activity.refreshAllList(exhibits);
        activity.progressBar.setVisibility(View.GONE);
    }


    public void onError() {
        Toast.makeText(activity.getContext(),
                "Ошибка получения данных", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);
    }

    public void getQuery(String login) {
        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        exhibitFacadel = new ExhibitFacadeImpl(museumDao, this);
        exhibitFacadel.getExhibitsByMuseumLogin(login);

    }
}
