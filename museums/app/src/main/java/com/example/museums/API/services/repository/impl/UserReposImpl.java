package com.example.museums.API.services.repository.impl;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.ErrorModel;
import com.example.museums.API.models.OkModel;
import com.example.museums.API.models.user.ExistingUser;
import com.example.museums.API.models.user.NewUser;
import com.example.museums.API.models.user.UserMuseum;
import com.example.museums.API.models.user.UserUpdate;
import com.example.museums.API.presenter.BasePresenter;
import com.example.museums.API.services.ErrorParser;
import com.example.museums.API.services.api.UserService;
import com.example.museums.API.services.repository.UserRepos;
import com.google.gson.Gson;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class UserReposImpl implements UserRepos {
    UserService userService;

    public UserReposImpl() {
        userService = RetrofitConnect.getRetrofitConnect().create(UserService.class);
    }

    @Override
    public void getUser(NewUser user, BasePresenter.Presenter viewContract) {
        userService.getUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<ExistingUser>() {
                    @Override
                    public void onSuccess(@NonNull ExistingUser listIds) {
                        viewContract.onSuccess(listIds);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        viewContract.onError(ErrorParser.getMessage(e));
                    }
                });
    }

    @Override
    public void updateUserPassword(UserUpdate user, BasePresenter.Presenter viewContract) {
        userService.updateUserPassword(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<OkModel>() {
                    @Override
                    public void onSuccess(@NonNull OkModel listIds) {
                        viewContract.onSuccess(listIds);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        viewContract.onError(ErrorParser.getMessage(e));
                    }
                });
    }

    @Override
    public void createUser(NewUser user, BasePresenter.Presenter viewContract) {
        userService.createUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<OkModel>() {
                    @Override
                    public void onSuccess(@NonNull OkModel listIds) {
                        viewContract.onSuccess(listIds);
                        System.out.println(listIds.getMessage());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        viewContract.onError(ErrorParser.getMessage(e));
                    }
                });
    }

    @Override
    public void updateMuseumUserPass(UserMuseum user, BasePresenter.Presenter viewContract) {
        userService.updateMuseumUserPass(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<OkModel>() {
                    @Override
                    public void onSuccess(@NonNull OkModel listIds) {
                        viewContract.onSuccess(listIds);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        viewContract.onError(ErrorParser.getMessage(e));
                    }
                });
    }
}
