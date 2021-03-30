package com.example.museums.API.services.repository;

import com.example.museums.API.models.museum.BaseMuseum;
import com.example.museums.API.models.museum.UpdatableMuseum;
import com.example.museums.API.presenter.BasePresenter;


public interface MuseumRepos {
    void createMuseum(BaseMuseum baseMuseum, BasePresenter.Presenter viewContract);

    void getAllMuseums(BasePresenter.Presenter viewContract);

    void updateMuseum(UpdatableMuseum baseMuseum, BasePresenter.Presenter viewContract);

    void getMuseumByWorkerId(Integer id, BasePresenter.Presenter viewContract);
}
