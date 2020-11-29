package com.example.museums.view.fragments.museum.museumExhibits;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.ExhibitFacadeImpl;
import com.example.museums.API.interfaces.impl.ExhibitionFacadeImpl;
import com.example.museums.API.interfaces.impl.MuseumFacadeImpl;
import com.example.museums.API.models.Exhibition;
import com.example.museums.view.fragments.museum.createExhibition.NewExhibitModel;

import java.util.List;

public class QueryDeleteMuseumExhibit {
    private MuseumExhibits activity;
    private   ExhibitFacadeImpl exhibitFacadel;
    private MuseumDao museumDao;
    public QueryDeleteMuseumExhibit(MuseumExhibits museumExhibits) {
        this.activity = museumExhibits;
    }

    public void onSuccess( ) {

        Toast.makeText(activity.getContext(),
                "Успешное удаление"  , Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

    }


    public void onError() {
        Toast.makeText(activity.getContext(),
                "Ошибка удаления", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

    }
    public void getQuery(int id) {


        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        exhibitFacadel = new ExhibitFacadeImpl(museumDao, this);

        exhibitFacadel.deleteExhibit(id);


    }

}
