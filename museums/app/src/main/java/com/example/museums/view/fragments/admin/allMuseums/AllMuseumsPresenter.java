package com.example.museums.view.fragments.admin.allMuseums;

import com.example.museums.API.presenter.BasePresenterImpl;
import com.example.museums.API.services.repository.MuseumRepos;
import com.example.museums.API.services.repository.impl.MuseumReposImpl;

public class AllMuseumsPresenter extends BasePresenterImpl {
    MuseumRepos museumRepos = new  MuseumReposImpl();

    @Override
    public void loadData() {
        museumRepos.getAllMuseums(this);
    }
}
