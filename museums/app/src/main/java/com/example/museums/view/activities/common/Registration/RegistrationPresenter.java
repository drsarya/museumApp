package com.example.museums.view.activities.common.Registration;

import com.example.museums.API.models.enums.RoleEnum;
import com.example.museums.API.models.user.NewUser;
import com.example.museums.API.presenter.BasePresenterImpl;
import com.example.museums.API.services.repository.UserRepos;
import com.example.museums.API.services.repository.impl.UserReposImpl;

public class RegistrationPresenter extends BasePresenterImpl {
    private String login;
    private String password;
    private UserRepos userRepos = new UserReposImpl();


    public void setUserInfo(String login, String password) {
        this.login = login;
        this.password = password;
    }
    @Override
    public void loadData() {
        view.showProgress();
        userRepos.createUser(new NewUser(login, password, RoleEnum.USER), this);
    }
}
