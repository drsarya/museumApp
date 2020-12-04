package com.example.museums.view.fragments.museum.exhibit.createExhibit;

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
import com.example.museums.view.fragments.museum.exhibit.QueryInsertAuthor;


public class QueryInsertExhibit {
    public CreateExhibit activity;
    private MuseumDao museumDao;
    private NewExhibitModel newExhibitModel;
    private Integer idExhibition;
    private ExhibitFacadeImpl exhibitFacade  ;

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
        activity.insertNewExhibit(newExhibitModel);

        Toast.makeText(activity.getContext(),
                "Успешное создание экпоната", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

     }

    public void onErrorInsertExhbtToExhbn() {

        Toast.makeText(activity.getContext(),
                "Ошибка создания экспоната", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

    }


    public void   onSuccessInsertAuthor(Integer id){
        Exhibit exhibit = new Exhibit();
        exhibit.authorId = newExhibitModel.idAuthor;
        exhibit.tags = newExhibitModel.tags;
        Bitmap bmp2 = newExhibitModel.photo.copy(newExhibitModel.photo.getConfig(), true);
        exhibit.photo = bmp2;
        exhibit.dateOfCreate = newExhibitModel.dateOfCreate;
        exhibit.name = newExhibitModel.name;
        exhibit.description = newExhibitModel.description;
        exhibit.authorId = id;
        exhibitFacade = new ExhibitFacadeImpl(museumDao, this);
        exhibitFacade.insertExhibit(exhibit);

    }

    public void getQuery(NewExhibitModel model, int idExhibition) {
        newExhibitModel = model;
        this.idExhibition = idExhibition;
        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        QueryInsertAuthor q= new QueryInsertAuthor(museumDao, this);
        q.getQuery(model);

    }
    public void onError() {
        Toast.makeText(activity.getContext(),
                "Ошибка создания экспоната", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

    }
}
