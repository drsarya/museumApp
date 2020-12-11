package com.example.museums.view.fragments.museum.exhibit.editExhibit;

import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.ExhibitFacadeImpl;
import com.example.museums.API.models.Exhibit;
import com.example.museums.API.models.NewExhibitModel;
import com.example.museums.view.fragments.museum.exhibition.editExhibition.EditExhibtion;
import com.example.museums.view.fragments.museum.exhibit.QueryInsertAuthor;
import com.example.museums.view.fragments.museum.museumExhibits.MuseumExhibits;

public class QueryUpdateExhibit {
    private MuseumDao museumDao;
    private NewExhibitModel newExhibitModel;
    private ExhibitFacadeImpl exhibitFacade;
    public Fragment activity;


    public QueryUpdateExhibit(EditExhibit exhibit, Fragment museumExhibits) {
        this.activity = exhibit;
        this.activity = museumExhibits;

    }

    public void onErrorUpdate() {
        Toast.makeText(activity.getContext(), "Ошибка обновления экпоната", Toast.LENGTH_SHORT).show();

    }


    public void onSuccessUpdate(int count) {
        Toast.makeText(activity.getContext(), "Успешное обновление экпоната", Toast.LENGTH_SHORT).show();
        if (  activity.getClass().toString().equals(MuseumExhibits.class.toString())  ) {
            MuseumExhibits m = (MuseumExhibits) activity;
            m.refreshAllList();
        } else {
            EditExhibtion m = (EditExhibtion) activity;
            m.updateExhibit(posId, newExhibitModel);
        }
    }

    public void onSuccessInsertAuthor(Integer id) {
        newExhibitModel.setIdAuthor(id);
        Exhibit exhibit = new Exhibit();
        exhibit.authorId = newExhibitModel.idAuthor;
       // exhibit.tags = newExhibitModel.tags;
        Bitmap bmp2 = newExhibitModel.photo.copy(newExhibitModel.photo.getConfig(), true);
        exhibit.photo = bmp2;
        exhibit.id = this.id;
        exhibit.dateOfCreate = newExhibitModel.dateOfCreate;
        exhibit.name = newExhibitModel.name;
        exhibit.description = newExhibitModel.description;
        exhibitFacade.updateExhibit(exhibit);


    }

    private int id;
    private int posId;

    public void getQuery(NewExhibitModel model, int id, int posId) {
        newExhibitModel = model;
        this.id = id;
        this.posId = posId;
        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        exhibitFacade = new ExhibitFacadeImpl(museumDao, this);
        QueryInsertAuthor q = new QueryInsertAuthor(museumDao, this);
        q.getQuery(model);
    }

}
