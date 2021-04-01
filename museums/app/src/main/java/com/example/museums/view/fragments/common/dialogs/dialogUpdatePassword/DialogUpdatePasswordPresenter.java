//package com.example.museums.view.fragments.common.dialogs.dialogUpdatePassword;
//
//import com.example.museums.API.models.user.UserUpdate;
//import com.example.museums.API.presenter.specific.BasePresenterImpl;
//import com.example.museums.API.services.repository.UserRepos;
//import com.example.museums.API.services.repository.impl.UserReposImpl;
//
//public class DialogUpdatePasswordPresenter extends BasePresenterImpl {
//UserRepos userRepos = new UserReposImpl();
//
//    String login, oldPassword, newPassword;
//
//    public void setInfo(String login, String oldPassword, String newPassword) {
//        this.login = login;
//        this.oldPassword = oldPassword;
//        this.newPassword = newPassword;
//    }
//
//    @Override
//    public void loadData() {
//        userRepos.updateUserPassword(new UserUpdate(login, oldPassword, newPassword), this);
//    }
//}
