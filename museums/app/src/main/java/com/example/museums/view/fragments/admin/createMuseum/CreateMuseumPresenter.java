package com.example.museums.view.fragments.admin.createMuseum;

import com.example.museums.API.models.museum.BaseMuseum;
import com.example.museums.API.presenter.BasePresenterImpl;
import com.example.museums.API.services.repository.MuseumRepos;
import com.example.museums.API.services.repository.impl.MuseumReposImpl;

public class CreateMuseumPresenter extends BasePresenterImpl {
    MuseumRepos museumRepos = new MuseumReposImpl();
    String login, name, address;



    public void  setInfo(String login, String name, String address) {
        this.login = login;
        this.name = name;
        this.address = address;
    }

    @Override
    public void loadData() {
        museumRepos.createMuseum(new BaseMuseum(name, address), login, this);
    }
}
