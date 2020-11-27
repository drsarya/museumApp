package com.example.museums.view.fragments.museum.createExhibition.authors;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.AuthorFacadeImpl;
import com.example.museums.API.models.Author;
import com.example.museums.view.fragments.museum.createExhibition.CreateExhibit;

import java.util.List;

public class QueryAuthor {
    private AuthorFacadeImpl authorFacade;
    private MuseumDao museumDao;
    private CreateExhibit activity;
    private String author;

    public QueryAuthor(CreateExhibit activity) {
        this.activity = activity;
    }

    public void onSuccess(List<Author> list) {
        activity.refreshAllList(list);

    }

    public void getQuery() {
        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        authorFacade = new AuthorFacadeImpl(museumDao, this);
        authorFacade.getAuthors();
    }


}
