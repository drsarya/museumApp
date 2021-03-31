package com.example.museums.view.activities.common.RegistrationMuseum;

import com.example.museums.API.models.user.UserMuseum;
import com.example.museums.API.presenter.BasePresenterImpl;
import com.example.museums.API.services.repository.UserRepos;
import com.example.museums.API.services.repository.impl.UserReposImpl;

public class RegistrationMuseumPresenter extends BasePresenterImpl {
    private String login;
    private String password;
    private Integer idCode;
    private UserRepos userRepos = new UserReposImpl();


    public void setInfo(Integer idCode, String login, String password) {
        this.idCode = idCode;
        this.login = login;
        this.password = password;
    }

    @Override
    public void loadData() {
        view.showProgress();
        userRepos.updateMuseumUserPass(new UserMuseum(login, password, idCode), this);
    }
}
