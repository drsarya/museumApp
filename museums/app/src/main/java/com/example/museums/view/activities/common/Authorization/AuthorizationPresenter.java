package com.example.museums.view.activities.common.Authorization;

import com.example.museums.API.models.user.NewUser;
import com.example.museums.API.presenter.BasePresenter;
import com.example.museums.API.services.repository.UserRepos;
import com.example.museums.API.services.repository.impl.UserReposImpl;

public class AuthorizationPresenter implements BasePresenter.Presenter {
    private UserRepos userRepos = new UserReposImpl();
    private String login;
    private String password;
    BasePresenter.View  view ;
    public AuthorizationPresenter(BasePresenter.View  view) {
        this.view = view;
    }

    public void setUserInfo(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public <T> void onSuccess(T value) {
        view.hideProgress();
        view.showData(value);
    }

    @Override
    public void onError(String error) {
        view.hideProgress();
        view.showError(error);
    }

    @Override
    public void loadData() {
        view.showProgress();
        userRepos.getUser(new NewUser(login, password), this);
    }
}
