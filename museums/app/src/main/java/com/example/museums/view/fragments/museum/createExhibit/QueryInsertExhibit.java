package com.example.museums.view.fragments.museum.createExhibit;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.ExhbtToExhbtnFacadeImpl;
import com.example.museums.API.interfaces.impl.ExhibitFacadeImpl;
import com.example.museums.API.models.Exhibit;
import com.example.museums.API.models.ExhibitToExhbtn;
import com.example.museums.API.models.NewExhibitModel;


public class QueryInsertExhibit {
    private CreateExhibit activity;
    private MuseumDao museumDao;
    private NewExhibitModel newExhibitModel;
    private Integer idExhibition;
    private ExhibitFacadeImpl exhibitFacade;

    public QueryInsertExhibit(CreateExhibit exhibit) {
        this.activity = exhibit;
    }

    public void onSuccess(Integer idExhibit) {
        newExhibitModel.exhibitId = idExhibit;
        ExhbtToExhbtnFacadeImpl exhbtnFacade = new ExhbtToExhbtnFacadeImpl(museumDao, this);
        ExhibitToExhbtn exhibitToExhbtn = new ExhibitToExhbtn();
        exhibitToExhbtn.idExhibit = idExhibit;
        exhibitToExhbtn.idExhibition = idExhibition;

        exhbtnFacade.insertExhbToExbtn(exhibitToExhbtn);

    }

    public void onSuccessInsertExhbtToExhbn() {
        Toast.makeText(activity.getContext(),
                "Успешное создание экпоната", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

        activity.insertNewExhibit(newExhibitModel);
    }

    public void onErrorInsertExhbtToExhbn() {
        Toast.makeText(activity.getContext(),
                "Ошибка создания экспоната", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

    }

    public void getQuery(NewExhibitModel model, Integer idExhibition) {
        newExhibitModel = model;
        this.idExhibition = idExhibition;
        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        exhibitFacade = new ExhibitFacadeImpl(museumDao, this);
        Exhibit exhibit = new Exhibit();
        exhibit.authorId = newExhibitModel.idAuthor;
        exhibit.tags = newExhibitModel.tags;
        Bitmap bmp2 = newExhibitModel.photo.copy(newExhibitModel.photo.getConfig(), true);
        exhibit.photo = bmp2;
        exhibit.dateOfCreate = newExhibitModel.dateOfCreate;
        exhibit.name = newExhibitModel.name;
        exhibit.description = newExhibitModel.description;

        exhibitFacade.insertExhibit(exhibit);
    }

    public void onError() {
    }
}
