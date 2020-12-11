package com.example.museums.API.interfaces.impl;

import android.annotation.SuppressLint;

import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.UserFacade;
import com.example.museums.API.models.User;
import com.example.museums.view.activities.common.Authorization.QueryAuthorization;
import com.example.museums.view.activities.common.Registration.QueryRegistration;
import com.example.museums.view.activities.common.RegistrationMuseum.QueryRegistrationMuseum;
import com.example.museums.view.fragments.admin.createMuseum.QueryCreateMuseum;
import com.example.museums.view.fragments.common.Dialogs.dialogUpdatePassword.QueryUpdatePassword;
import com.example.museums.view.services.ConfigEncrypt;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class UserFacadeImpl implements UserFacade {
    private MuseumDao museumDao;

    private QueryAuthorization queryAuthorization;
    private QueryRegistration queryRegistration;
    private QueryRegistrationMuseum queryRegistrationMuseum;
    private QueryUpdatePassword queryUpdatePassword;
    private QueryCreateMuseum queryCreateMuseum;

    public UserFacadeImpl(MuseumDao mDao, QueryAuthorization queryAuthorization) {
        museumDao = mDao;
        this.queryAuthorization = queryAuthorization;
    }

    public UserFacadeImpl(MuseumDao mDao, QueryCreateMuseum queryCreateMuseum) {
        museumDao = mDao;
        this.queryCreateMuseum = queryCreateMuseum;
    }

    public UserFacadeImpl(MuseumDao mDao, QueryUpdatePassword queryUpdatePassword) {
        museumDao = mDao;
        this.queryUpdatePassword = queryUpdatePassword;
    }

    public UserFacadeImpl(MuseumDao mDao, QueryRegistrationMuseum queryRegistrationMuseum) {
        museumDao = mDao;
        this.queryRegistrationMuseum = queryRegistrationMuseum;
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
    public void getUserMuseum(String login) {

        museumDao.getUserMuseum(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<User>() {
                    @Override
                    public void onSuccess(@NonNull User user) {

                        queryRegistrationMuseum.updateMuseumPassword();


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        queryRegistrationMuseum.onError();

                    }
                })
        ;

    }

    @SuppressLint("CheckResult")
    @Override
    public void getUser(String login, String password) {

        museumDao.getUser(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<User>() {
                    @Override
                    public void onSuccess(@NonNull User user) {
                        try {
                            if (ConfigEncrypt.check(password, user.password)) {
                                System.out.println("ssssssss");
                                queryAuthorization.onSuccess(user);
                            } else {
                                System.out.println("ssssssssssssssssssssssssssssssssss");
                                queryAuthorization.onError();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("ssssssssssssssssssssssssssssssssss");

                        queryAuthorization.onError();
                    }
                })
        ;

    }

    @Override
    public void getUser(String login, String password, String newPassword) {
        museumDao.getUser(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<User>() {
                    @Override
                    public void onSuccess(@NonNull User user) {
                        try {
                            if (ConfigEncrypt.check(password, user.password)) {
                                updateUserPassword(login, newPassword);

                            } else {
                                queryUpdatePassword.onError();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        queryUpdatePassword.onError();
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
        museumDao.insertUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Long>() {
                    @Override
                    public void onSuccess(@NonNull Long aLong) {
                        if (queryRegistration != null) {
                            queryRegistration.onSuccess();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (queryRegistration != null) {
                            queryRegistration.onError();
                        }
                    }
                });
    }

    @Override
    public void insertUserMuseum(String login, String password, boolean type) {
        User user = new User();
        user.login = login;
        user.password = password;
        user.type = type;
        museumDao.insertUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Long>() {
                    @Override
                    public void onSuccess(@NonNull Long aLong) {
                        queryCreateMuseum.insertMuseum();
                    }

                    @Override
                    public void onError(Throwable e) {
                        queryCreateMuseum.onError();
                    }
                });
    }

    @Override
    public void updateUserPassword(String login, String password) {

        museumDao.updateUserPassword(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Integer>() {
                    @Override
                    public void onSuccess(@NonNull Integer user) {
                        if (queryUpdatePassword != null) {
                            queryUpdatePassword.onSuccess();
                        } else {

                            queryRegistrationMuseum.onSuccess();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (queryUpdatePassword != null) {
                            queryUpdatePassword.onError();
                        } else {
                            System.out.println(e.toString() + " 3");
                            queryRegistrationMuseum.onSuccess();
                        }
                    }
                });
    }

}
