package com.example.museums.API.interfaces.impl;

import android.annotation.SuppressLint;

import com.example.museums.API.MuseumDao;
import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.interfaces.UserApi;
import com.example.museums.API.interfaces.UserFacade;
import com.example.museums.API.models.NewUser;
import com.example.museums.API.models.User;
import com.example.museums.API.models.UserUpdate;
import com.example.museums.view.activities.common.Authorization.QueryAuthorization;
import com.example.museums.view.activities.common.Registration.QueryRegistration;
import com.example.museums.view.activities.common.RegistrationMuseum.QueryRegistrationMuseum;
import com.example.museums.view.fragments.admin.createMuseum.QueryCreateMuseum;
import com.example.museums.view.fragments.common.dialogs.dialogUpdatePassword.QueryUpdatePassword;
import com.example.museums.view.services.ConfigEncrypt;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


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


        Retrofit retrofit = RetrofitConnect.create();
        UserApi messagesApi = retrofit.create(UserApi.class);
        messagesApi.getUserMuseum(login)
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
                        System.out.println(e.toString());
                    }
                });


    }

    @SuppressLint("CheckResult")
    @Override
    public void getUser(String login, String password) {


        NewUser nu = new NewUser(login, password, false);
        Retrofit retrofit = RetrofitConnect.create();
        UserApi messagesApi = retrofit.create(UserApi.class);
        messagesApi.getUser(nu)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<User>() {
                    @Override
                    public void onSuccess(@NonNull User user) {
                        System.out.println(user + "dddddddddddddddddddddddddddddd");
                        queryAuthorization.onSuccess(user);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        queryAuthorization.onError();
                    }
                });


    }


    @SuppressLint("CheckResult")
    @Override
    public void insertUser(String login, String password, boolean type) {

        NewUser nu = new NewUser(login, password, type);
        Retrofit retrofit = RetrofitConnect.create();
        UserApi messagesApi = retrofit.create(UserApi.class);
        messagesApi.createUser(nu)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<User>() {
                    @Override
                    public void onSuccess(@NonNull User messages) {
                        if (queryRegistration != null) {
                            queryRegistration.onSuccess();
                        }
                        if (queryAuthorization != null) {
                            queryAuthorization.onSuccessInsertAdmin();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (queryRegistration != null) {
                            queryRegistration.onError();
                        }
                        if (queryAuthorization != null) {
                            System.out.println(e.toString());
                            queryAuthorization.onErrorInsertAdmin();
                        }
                    }
                });
    }


    @Override
    public void insertUserMuseum(String login, String password, boolean type) {

        NewUser nu = new NewUser(login, password, type);
        Retrofit retrofit = RetrofitConnect.create();
        UserApi messagesApi = retrofit.create(UserApi.class);
        messagesApi.createUser(nu)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<User>() {
                    @Override
                    public void onSuccess(@NonNull User messages) {
                        queryCreateMuseum.insertMuseum();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        queryCreateMuseum.onError();

                    }
                });
    }

    @Override
    public void updateUserPassword(String login, String password, String newPassword) {
        UserUpdate nu = new UserUpdate(null, login, newPassword, password);
        Retrofit retrofit = RetrofitConnect.create();
        UserApi messagesApi = retrofit.create(UserApi.class);
        messagesApi.updatePassword(nu)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Boolean>() {
                    @Override
                    public void onSuccess(@NonNull Boolean messages) {
                        System.out.println(messages);
                        System.out.println("ssssssssssssssssssssssssssssssssssssssssssss");
                        if (messages) {
                            if (queryUpdatePassword != null) {
                                queryUpdatePassword.onSuccess();
                            } else {
                                queryRegistrationMuseum.onSuccess();
                            }
                        } else {

                            if (queryUpdatePassword != null) {
                                queryUpdatePassword.onError();
                            } else {
                                queryRegistrationMuseum.onSuccess();
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (queryUpdatePassword != null) {
                            queryUpdatePassword.onError();
                        } else {
                            queryRegistrationMuseum.onSuccess();
                        }
                    }
                });


    }


}
