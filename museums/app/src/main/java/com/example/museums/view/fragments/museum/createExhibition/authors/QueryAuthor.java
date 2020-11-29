package com.example.museums.view.fragments.museum.createExhibition.authors;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.AuthorFacadeImpl;
import com.example.museums.API.models.Author;
import com.example.museums.view.fragments.museum.createExhibition.CreateExhibit.CreateExhibit;
import com.example.museums.view.fragments.museum.createExhibition.editExhibit.EditExhibit;

import java.util.List;

public class QueryAuthor {
    private AuthorFacadeImpl authorFacade;
    private MuseumDao museumDao;
    private CreateExhibit activity;
    private EditExhibit editExhibit;


    public QueryAuthor(CreateExhibit activity) {
        this.activity = activity;
    }

    public QueryAuthor(EditExhibit activity) {
        this.editExhibit = activity;
    }

    public void onSuccess(List<Author> list) {
        if (activity != null) {
            activity.refreshAllList(list);
        } else {
            editExhibit.refreshAllList(list);

        }
    }

    public void getQuery() {
        if (activity != null) {
            museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        } else {
            museumDao = ((AppDelegate) editExhibit.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        }
        authorFacade = new AuthorFacadeImpl(museumDao, this);
        authorFacade.getAuthors();
    }


}