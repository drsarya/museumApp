package com.example.museums.API.services.repository;

import com.example.museums.API.models.exhibition.BaseExhibition;
import com.example.museums.API.models.exhibition.ExistingExhibition;
import com.example.museums.API.presenter.BasePresenter;


public interface ExhibitionRepos {
    void getAllExhibitions(BasePresenter.Presenter viewContract);

    void deleteExhibition(  int id, BasePresenter.Presenter viewContract);

    void createExhibition(BaseExhibition exhibition, BasePresenter.Presenter viewContract);

    void updateExhibition(ExistingExhibition exhibition, BasePresenter.Presenter viewContract);

    void getExhibitionsByMuseumId(  int id, BasePresenter.Presenter viewContract);

}
