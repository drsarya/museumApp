package com.example.museums.view.fragments.museum.museumExhibitions;

import android.view.View;
import android.widget.Toast;

import com.example.museums.API.services.impl.ExhibitionFacadeImpl;

import java.util.List;

public class QueryMuseumExhibitions {
    private MuseumExhibitions activity;
    private ExhibitionFacadeImpl exhibitionFacade;
    private MuseumDao museumDao;

    public QueryMuseumExhibitions(MuseumExhibitions museumExhibits) {
        this.activity = museumExhibits;
    }

    public void onSuccess(int idMuseum) {
        exhibitionFacade.getExhibitionByMuseumId(idMuseum);
    }


    public void onError() {
        Toast.makeText(activity.getContext(),
                "Ошибка удаления", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);
    }

    public void onSuccessGetExhbtn(List<ExhibitionWithMuseumName> exhibitions) {

        activity.updateAll(exhibitions);
        activity.progressBar.setVisibility(View.GONE);

    }

    public void getQuery(String login) {
        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        exhibitionFacade = new ExhibitionFacadeImpl(museumDao, this);
        exhibitionFacade.getMuseumByLogin(login);
    }

}
