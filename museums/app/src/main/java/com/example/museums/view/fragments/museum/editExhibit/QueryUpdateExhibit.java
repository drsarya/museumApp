package com.example.museums.view.fragments.museum.editExhibit;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.AuthorFacadeImpl;
import com.example.museums.API.interfaces.impl.ExhibitFacadeImpl;
import com.example.museums.API.models.Exhibit;
import com.example.museums.API.models.NewExhibitModel;
import com.example.museums.view.fragments.museum.createExhibition.CreateExhibition;
import com.example.museums.view.fragments.museum.museumExhibits.MuseumExhibits;

public class QueryUpdateExhibit {
    private EditExhibit activity;
    private AuthorFacadeImpl authorFacade;
    private MuseumDao museumDao;
    private NewExhibitModel newExhibitModel;
    private ExhibitFacadeImpl exhibitFacade;
    private MuseumExhibits museumExhibits;
    private CreateExhibition createExhibition;

    public QueryUpdateExhibit(EditExhibit exhibit, MuseumExhibits museumExhibits) {
        this.activity = exhibit;
        this.museumExhibits = museumExhibits;

    }
    public QueryUpdateExhibit(EditExhibit exhibit, CreateExhibition createExhibition) {
        this.activity = exhibit;
        this.createExhibition = createExhibition;

    }

    public void onSuccessInsert(Integer id) {
        // автора не было и его имя пришлось добавить -
        activity.progressBar.setVisibility(View.GONE);
        newExhibitModel.setIdAuthor(id);
        Exhibit exhibit = new Exhibit();
        exhibit.authorId = newExhibitModel.idAuthor;
        exhibit.tags = newExhibitModel.tags;
        Bitmap bmp2 = newExhibitModel.photo.copy(newExhibitModel.photo.getConfig(), true);
        exhibit.photo = bmp2;
        exhibit.id = this.id;
        exhibit.dateOfCreate = newExhibitModel.dateOfCreate;
        exhibit.name = newExhibitModel.name;
        exhibit.description = newExhibitModel.description;
        exhibitFacade.updateExhibit(exhibit);
    }

    public void onSuccess(Integer id) {
        // автор есть
        activity.progressBar.setVisibility(View.GONE);
        newExhibitModel.setIdAuthor(id);
        Exhibit exhibit = new Exhibit();
        exhibit.authorId = newExhibitModel.idAuthor;
        exhibit.tags = newExhibitModel.tags;
        Bitmap bmp2 = newExhibitModel.photo.copy(newExhibitModel.photo.getConfig(), true);
        exhibit.photo = bmp2;
        exhibit.id = this.id;
        exhibit.dateOfCreate = newExhibitModel.dateOfCreate;
        exhibit.name = newExhibitModel.name;
        exhibit.description = newExhibitModel.description;
        exhibitFacade.updateExhibit(exhibit);


    }

    public void onSuccessUpdate(int count) {
        Toast.makeText(activity.getContext(), "Успешное обновление экпоната", Toast.LENGTH_SHORT).show();
        if (museumExhibits != null) {
            museumExhibits.refreshAllList();
        }else{
             createExhibition.updateExhibit(  posId, newExhibitModel);
        }
    }

    public void onErrorUpdate() {
        Toast.makeText(activity.getContext(), "Ошибка обновления", Toast.LENGTH_SHORT).show();
    }

    private int id;

    public void getQuery(NewExhibitModel model, int id) {
        newExhibitModel = model;
        this.id = id;
        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        authorFacade = new AuthorFacadeImpl(museumDao, this);
        exhibitFacade = new ExhibitFacadeImpl(museumDao, this);
        authorFacade.getAuthorByName(model.author);

    }
    private int posId;
    public void getQuery(NewExhibitModel model, int id, int posId) {
        newExhibitModel = model;
        this.id = id;
        this.posId = posId;
        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        authorFacade = new AuthorFacadeImpl(museumDao, this);
        exhibitFacade = new ExhibitFacadeImpl(museumDao, this);
        authorFacade.getAuthorByName(model.author);

    }
    public void onError() {
        authorFacade.insertAuthor(newExhibitModel.author);
    }

    public void onErrorInsert() {
        authorFacade.insertAuthor(newExhibitModel.author);
        Toast.makeText(activity.getContext(), "Ошибка добавления", Toast.LENGTH_SHORT).show();
    }

}
