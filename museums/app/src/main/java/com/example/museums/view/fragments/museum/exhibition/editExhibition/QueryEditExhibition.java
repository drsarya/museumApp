package com.example.museums.view.fragments.museum.exhibition.editExhibition;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.ExhibitionFacadeImpl;
import com.example.museums.API.models.Exhibition;

public class QueryEditExhibition {
    private ExhibitionFacadeImpl exhibitionFacade;

    private MuseumDao museumDao;
    private EditExhibtion activity;


    public QueryEditExhibition(EditExhibtion fragment) {
        this.activity = fragment;
    }

    public void onSuccess() {
        Toast.makeText(activity.getContext(),
                "Успешное обновление", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);
    }

    public void onError() {
        Toast.makeText(activity.getContext(),
                "Ошибка обновления", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

    }

    public void getQuery(Exhibition exhibition) {
        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        exhibitionFacade = new ExhibitionFacadeImpl(museumDao, this);
        if (exhibition.id != null) {
            Bitmap bmp2 = exhibition.image.copy(exhibition.image.getConfig(), true);
            exhibition.image = bmp2;
            exhibitionFacade.updateExhibition(exhibition);
        }
    }
}
