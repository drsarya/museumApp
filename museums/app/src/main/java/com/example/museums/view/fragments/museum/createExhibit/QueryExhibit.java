package com.example.museums.view.fragments.museum.createExhibit;

import android.view.View;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.AuthorFacadeImpl;

public class QueryExhibit {
    private CreateExhibit activity;
    private AuthorFacadeImpl authorFacade;
    private MuseumDao museumDao;
    private NewExhibitModel newExhibitModel;

    public QueryExhibit(CreateExhibit exhibit) {
        this.activity = exhibit;
    }

    //Сам экспонат пока что хранится просто в списке активити
    public void onSuccessInsert(Integer id) {
        activity.progressBar.setVisibility(View.GONE);
        newExhibitModel.setIdAuthor(id );
        Toast.makeText(activity.getContext(), "Успешное создание экпоната", Toast.LENGTH_SHORT).show();
        activity.insertNewExhibit(newExhibitModel);
    }

    public void onSuccess(Integer id) {
        if (id != null) {
            activity.progressBar.setVisibility(View.GONE);
            newExhibitModel.setIdAuthor(id );
            Toast.makeText(activity.getContext(), "Успешное создание экпоната", Toast.LENGTH_SHORT).show();
            activity.insertNewExhibit(newExhibitModel);
        }
    }

    public void getQuery(NewExhibitModel model) {
        newExhibitModel = model;
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
