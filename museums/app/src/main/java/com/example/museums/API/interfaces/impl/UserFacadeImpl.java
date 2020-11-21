package com.example.museums.API.interfaces.impl;

import android.annotation.SuppressLint;

import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.UserFacade;
import com.example.museums.API.models.User;
import com.example.museums.view.activities.common.Authorization.QueryAuthorization;
import com.example.museums.view.activities.common.Registration.QueryRegistration;

import java.util.concurrent.atomic.AtomicLong;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class UserFacadeImpl implements UserFacade {
    private MuseumDao museumDao;

    private QueryAuthorization queryAuthorization;
    private QueryRegistration queryRegistration;

    public UserFacadeImpl(MuseumDao mDao, QueryAuthorization queryAuthorization) {
        museumDao = mDao;
        this.queryAuthorization = queryAuthorization;
    }

    public UserFacadeImpl(MuseumDao mDao, QueryRegistration queryRegistration) {
        museumDao = mDao;
        this.queryRegistration = queryRegistration;
    }

    public UserFacadeImpl(MuseumDao mDao) {
        museumDao = mDao;

    }

    @SuppressLint("CheckResult")
    @Override
    public void getUser(String login, String password) {

        museumDao.getUser(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<User>() {
                    @Override
                    public void onSuccess(@NonNull User user) {
                        queryAuthorization.onSuccess(user);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        queryAuthorization.onError();
                    }
                })
        ;

    }

    @SuppressLint("CheckResult")
    @Override
    public void insertUser(String login, String password, boolean type) {
        User user = new User();
        user.login = login;
        user.password = password;
        user.type = type;
        museumDao.insertUser(user).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Long>() {
                    @Override
                    public void onSuccess(@NonNull Long aLong) {
                        queryRegistration.onSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        queryRegistration.onError();
                    }
                });

    }


}
