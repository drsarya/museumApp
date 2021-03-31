package com.example.museums.view.fragments.admin.editMuseum;

import com.example.museums.API.models.museum.UpdatableMuseum;
import com.example.museums.API.presenter.BasePresenter;
import com.example.museums.API.presenter.BasePresenterImpl;
import com.example.museums.API.services.repository.MuseumRepos;
import com.example.museums.API.services.repository.impl.MuseumReposImpl;

public class DialogEditMuseumPresenter extends BasePresenterImpl {
    MuseumRepos m = new MuseumReposImpl();

    @Override
    public void loadData() {
        UpdatableMuseum updatableMuseum = new UpdatableMuseum();
        updatableMuseum.setId(Long.valueOf(id));
        updatableMuseum.setAddress(address);
        updatableMuseum.setNameMuseum(name);
        m.updateMuseum(updatableMuseum, this);
    }

    String name, address, id;

    public void setInfo(String name, String address, String id) {
        this.name = name;
        this.address = address;
        this.id = id;
    }
}
