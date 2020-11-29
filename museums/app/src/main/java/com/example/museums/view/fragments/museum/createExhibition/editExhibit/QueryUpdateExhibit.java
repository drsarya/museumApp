package com.example.museums.view.fragments.museum.createExhibition.editExhibit;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.AuthorFacadeImpl;
import com.example.museums.API.interfaces.impl.ExhibitFacadeImpl;
import com.example.museums.API.models.Exhibit;
import com.example.museums.view.fragments.museum.createExhibition.NewExhibitModel;
import com.example.museums.view.fragments.museum.createExhibition.editExhibit.EditExhibit;
import com.example.museums.view.fragments.museum.museumExhibits.MuseumExhibits;

public class QueryUpdateExhibit {
    private EditExhibit activity;
    private AuthorFacadeImpl authorFacade;
    private MuseumDao museumDao;
    private NewExhibitModel newExhibitModel;
    private ExhibitFacadeImpl exhibitFacade;
    private MuseumExhibits museumExhibits;

    public QueryUpdateExhibit(EditExhibit exhibit, MuseumExhibits museumExhibits) {
        this.activity = exhibit;
        this.museumExhibits = museumExhibits;

    }


    public void onSuccessInsert(Integer id) {
        // автора не было и его имя пришлось добавить -
        activity.progressBar.setVisibility(View.GONE);
        newExhibitModel.setIdAuthor(id.longValue());
        Exhibit exhibit = new Exhibit();
        exhibit.authorId = newExhibitModel.idAuthor.intValue();
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
        newExhibitModel.setIdAuthor(id.longValue());
        Exhibit exhibit = new Exhibit();
        exhibit.authorId = newExhibitModel.idAuthor.intValue();
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
        System.out.println(count+"количсетво обновленных строчек!!!!!!!!!!!!!!!!!!!!!!!!");
        Toast.makeText(activity.getContext(), "Успешное обновление экпоната", Toast.LENGTH_SHORT).show();
        // activity.updateExhibits(newExhibitModel);
        museumExhibits.refreshAllList();
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

    public void onError() {
        authorFacade.insertAuthor(newExhibitModel.author);
    }

    public void onErrorInsert() {
        authorFacade.insertAuthor(newExhibitModel.author);
        Toast.makeText(activity.getContext(), "Ошибка добавления", Toast.LENGTH_SHORT).show();

    }

}