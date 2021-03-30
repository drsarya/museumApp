package com.example.museums.API.services.repository;

import com.example.museums.API.models.exhibit.BaseExhibit;
import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.API.presenter.BasePresenter;


public interface ExhibitRepos {
    void getAllExhibits(BasePresenter.Presenter viewContract);

    void getExhibitsByMuseumId(  Integer id, BasePresenter.Presenter viewContract);

    void  deleteExhibit(  int id, BasePresenter.Presenter viewContract);

    void createExhibit(BaseExhibit exhibit, BasePresenter.Presenter viewContract);

    void updateExhibit(ExistingExhibit exhibit, BasePresenter.Presenter viewContract);
}
