package com.example.museums.API.services.repository;

import com.example.museums.API.models.user.NewUser;
import com.example.museums.API.models.user.UserMuseum;
import com.example.museums.API.models.user.UserUpdate;
import com.example.museums.API.presenter.BasePresenter;


public interface UserRepos {

    void getUser(NewUser user, BasePresenter.Presenter viewContract);

    void updateUserPassword(UserUpdate user, BasePresenter.Presenter viewContract);

    void createUser(NewUser user, BasePresenter.Presenter viewContract);

    void updateMuseumUserPass(UserMuseum user, BasePresenter.Presenter viewContract);
}
