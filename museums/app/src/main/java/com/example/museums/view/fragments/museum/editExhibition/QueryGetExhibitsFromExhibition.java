package com.example.museums.view.fragments.museum.editExhibition;

import android.view.View;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.ExhbtToExhbtnFacadeImpl;
import com.example.museums.API.interfaces.impl.ExhibitFacadeImpl;
import com.example.museums.API.interfaces.impl.ExhibitionFacadeImpl;
import com.example.museums.API.models.ExhibitToExhbtn;
import com.example.museums.view.fragments.museum.createExhibit.NewExhibitModel;
import com.example.museums.view.fragments.museum.createExhibition.CreateExhibition;
import com.example.museums.view.fragments.museum.museumExhibitions.MuseumExhibitions;

import java.util.List;

public class QueryGetExhibitsFromExhibition {

    private CreateExhibition activity;

    private ExhbtToExhbtnFacadeImpl exhibitionFacade;
    private MuseumDao museumDao;

    public QueryGetExhibitsFromExhibition(CreateExhibition createExhibition) {
        this.activity = createExhibition;
    }

    public void onSuccess(List<NewExhibitModel> list) {
         activity.updateListExhibits(list);
        activity.progressBar.setVisibility(View.GONE);

    }


    public void onError() {
        Toast.makeText(activity.getContext(),
                "Ошибка получения данных", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

    }


    public void getQuery(int id) {
        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        exhibitionFacade = new ExhbtToExhbtnFacadeImpl(museumDao, this);
        exhibitionFacade.getExhibitsByExhdtnId(Integer.toString(id));
    }
}
