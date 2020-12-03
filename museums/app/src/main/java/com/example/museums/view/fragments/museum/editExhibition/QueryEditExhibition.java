package com.example.museums.view.fragments.museum.editExhibition;

import android.view.View;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.ExhbtToExhbtnFacadeImpl;
import com.example.museums.API.models.Exhibition;
import com.example.museums.API.models.NewExhibitModel;
import com.example.museums.view.fragments.museum.createExhibition.CreateExhibition;

import java.util.List;

public class QueryEditExhibition {
    private CreateExhibition activity;

    private ExhbtToExhbtnFacadeImpl exhibitionFacade;
    private MuseumDao museumDao;

    public QueryEditExhibition(CreateExhibition createExhibition) {
        this.activity = createExhibition;
    }

    public void onSuccess(List<NewExhibitModel> list) {
        activity.progressBar.setVisibility(View.GONE);

    }


    public void onError() {
        Toast.makeText(activity.getContext(),
                "Ошибка получения данных", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

    }


    public void getQuery(Exhibition exhibition, List<NewExhibitModel> exhibits) {
        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
      ///  exhibitionFacade = new ExhbtToExhbtnFacadeImpl(museumDao, this);
    ///    exhibitionFacade.getExhibitsByExhdtnId(Integer.toString(id));
    }
}
