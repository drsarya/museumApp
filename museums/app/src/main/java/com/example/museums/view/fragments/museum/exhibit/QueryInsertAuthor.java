package com.example.museums.view.fragments.museum.exhibit;

import android.widget.Toast;

import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.AuthorFacadeImpl;
import com.example.museums.API.models.NewExhibitModel;
import com.example.museums.view.fragments.museum.exhibit.createExhibit.QueryInsertExhibit;
import com.example.museums.view.fragments.museum.exhibit.editExhibit.QueryUpdateExhibit;

public class QueryInsertAuthor {

    private AuthorFacadeImpl authorFacade;
    private MuseumDao museumDao;
    private QueryUpdateExhibit queryUpdateExhibit;
    private QueryInsertExhibit queryInsertExhibit;

    public QueryInsertAuthor(MuseumDao museumDao, QueryUpdateExhibit queryUpdateExhibit) {
        this.queryUpdateExhibit = queryUpdateExhibit;
        this.museumDao = museumDao;
    }

    public QueryInsertAuthor(MuseumDao museumDao, QueryInsertExhibit queryInsertExhibit) {
        this.museumDao = museumDao;
        this.queryInsertExhibit = queryInsertExhibit;
    }

    public void onSuccess(Integer id) {
        if (id != null) {
            if (queryInsertExhibit != null) {
                queryInsertExhibit.onSuccessInsertAuthor(id.intValue());
            } else {
                queryUpdateExhibit.onSuccessInsertAuthor(id.intValue());
            }
        } else {
            authorFacade.insertAuthor(authorName);
        }
    }

    String authorName;

    public void onError() {
        authorFacade.insertAuthor(authorName);
    }

    public void onErrorInsert() {
        if (queryInsertExhibit != null) {
            Toast.makeText(queryInsertExhibit.activity.getContext(), "Ошибка добавления автора", Toast.LENGTH_SHORT);
        } else {
            Toast.makeText(queryUpdateExhibit.activity.getContext(), "Ошибка добавления автора", Toast.LENGTH_SHORT);
        }
    }

    public void getQuery(NewExhibitModel model) {
        authorName = model.author;
        authorFacade = new AuthorFacadeImpl(museumDao, this);
        authorFacade.getAuthorByName(model.author);
    }
}
