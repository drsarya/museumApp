package com.example.museums.view.fragments.museum.exhibition.createExhibition;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.ExhibitionFacadeImpl;
import com.example.museums.API.interfaces.impl.MuseumFacadeImpl;
import com.example.museums.API.models.Exhibition;

public class QueryCreateExhibition {
    private ExhibitionFacadeImpl exhibitionFacade;
    private MuseumFacadeImpl museumFacade;
    private MuseumDao museumDao;
    private CreateExhibition activity;
    private Exhibition exhibition;

    public QueryCreateExhibition(CreateExhibition fragment) {
        this.activity = fragment;
    }

    public void onSuccess(int id) {
        System.out.println("sdsdsdsdssssssssssssss");
        exhibition.idMuseum = Integer.toString(id);
        Exhibition exhibition2 = new Exhibition();
        if (exhibition.image != null) {
            Bitmap bmp2 = exhibition.image.copy(exhibition.image.getConfig(), true);
            exhibition2.image = bmp2;
        }
        exhibition2.name = exhibition.name;
        exhibition2.description = exhibition.description;
        exhibition2.idMuseum = exhibition.idMuseum;
        exhibition2.lastDate = exhibition.lastDate;
        exhibition2.firstDate = exhibition.firstDate;
        exhibitionFacade.insertExhbtn(exhibition2);
    }

    public void onError() {
        Toast.makeText(activity.getContext(),
                "Ошибка получения индекса музея", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

    }

    public void onErrorInsertExhbtn() {

        Toast.makeText(activity.getContext(),
                "Ошибка обновления выставки", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

    }


    public void onSuccessInsertExhbtn(Long id) {

        Toast.makeText(activity.getContext(),
                "Успешно", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

    }


    public void getQuery(String login, Exhibition exhibition) {
        this.exhibition = exhibition;
        activity.progressBar.setVisibility(View.GONE);
        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        exhibitionFacade = new ExhibitionFacadeImpl(museumDao, this);
        museumFacade = new MuseumFacadeImpl(museumDao, this);
        museumFacade.getMuseumIDByLogin(login);
    }
}
