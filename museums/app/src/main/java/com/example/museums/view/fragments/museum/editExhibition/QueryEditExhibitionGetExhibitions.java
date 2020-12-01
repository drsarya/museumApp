package com.example.museums.view.fragments.museum.editExhibition;

import android.view.View;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.ExhibitionFacadeImpl;
import com.example.museums.API.models.ExhibitionWithMuseumName;

import java.util.List;

public class QueryEditExhibitionGetExhibitions {
    private EditExhibition activity;
    private ExhibitionFacadeImpl exhibitionFacade;
    private MuseumDao museumDao;

    public QueryEditExhibitionGetExhibitions(EditExhibition museumExhibits) {
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
