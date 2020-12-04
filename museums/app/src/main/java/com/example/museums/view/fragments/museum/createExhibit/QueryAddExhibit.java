package com.example.museums.view.fragments.museum.createExhibit;

import android.view.View;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.AuthorFacadeImpl;
import com.example.museums.API.models.NewExhibitModel;

public class QueryAddExhibit {
    private CreateExhibit activity;
    private AuthorFacadeImpl authorFacade;
    private MuseumDao museumDao;
    private NewExhibitModel newExhibitModel;
    private Integer idExhibition;

    public QueryAddExhibit(CreateExhibit exhibit) {
        this.activity = exhibit;
    }

    //Сам экспонат пока что хранится просто в списке активити
    public void onSuccessInsert(Integer id) {
        activity.progressBar.setVisibility(View.GONE);
        newExhibitModel.setIdAuthor(id);
        if (idExhibition == null) {
            Toast.makeText(activity.getContext(), "Успешное добавление экпоната", Toast.LENGTH_SHORT).show();
            activity.insertNewExhibit(newExhibitModel);
        } else {
            //добавляем экспонат
            QueryInsertExhibit queryInsertExhibit = new QueryInsertExhibit(activity);
            queryInsertExhibit.getQuery(newExhibitModel, idExhibition);
        }
    }

    public void onSuccess(Integer id) {

        activity.progressBar.setVisibility(View.GONE);
        newExhibitModel.setIdAuthor(id);
        if (idExhibition == null) {
            Toast.makeText(activity.getContext(), "Успешное добавление экпоната", Toast.LENGTH_SHORT).show();
            activity.insertNewExhibit(newExhibitModel);
        } else {
            //добавляем экспонат
            QueryInsertExhibit queryInsertExhibit = new QueryInsertExhibit(activity);
            queryInsertExhibit.getQuery(newExhibitModel, idExhibition);
        }

    }

    public void getQuery(NewExhibitModel model, Integer idExhibition) {
        newExhibitModel = model;
        this.idExhibition = idExhibition;
        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        authorFacade = new AuthorFacadeImpl(museumDao, this);
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
