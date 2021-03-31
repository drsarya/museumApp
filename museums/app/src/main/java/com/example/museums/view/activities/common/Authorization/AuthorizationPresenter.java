package com.example.museums.view.activities.common.Authorization;

import com.example.museums.API.models.user.NewUser;
import com.example.museums.API.presenter.BasePresenter;
import com.example.museums.API.presenter.BasePresenterImpl;
import com.example.museums.API.services.repository.UserRepos;
import com.example.museums.API.services.repository.impl.UserReposImpl;

public class AuthorizationPresenter extends BasePresenterImpl {
    private UserRepos userRepos = new UserReposImpl();
    private String login;
    private String password;


    public void setUserInfo(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public void loadData() {
        view.showProgress();
         userRepos.getUser(new NewUser(login, password), this);
    }

}
