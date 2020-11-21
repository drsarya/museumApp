package com.example.museums.API.interfaces.impl;

import android.annotation.SuppressLint;

import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.MuseumFacade;
import com.example.museums.API.models.Museum;
import com.example.museums.view.activities.common.Authorization.QueryAuthorization;
import com.example.museums.view.fragments.admin.allMuseums.QueryAllMuseums;
import com.example.museums.view.fragments.admin.createMuseum.QueryCreateMuseum;
import com.example.museums.view.fragments.admin.editMuseum.QueryEditMuseum;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MuseumFacadeImpl implements MuseumFacade {
    private MuseumDao museumDao;
    private QueryAuthorization queryAuthorization;
    private QueryCreateMuseum queryCreateMuseum;
    private QueryAllMuseums queryAllMuseums;
    private QueryEditMuseum queryEditMuseum;

    public MuseumFacadeImpl(MuseumDao museumDao) {
        this.museumDao = museumDao;
    }

    public MuseumFacadeImpl(MuseumDao mDao, QueryAuthorization queryAuthorization) {
        museumDao = mDao;
        this.queryAuthorization = queryAuthorization;
    }



    public MuseumFacadeImpl(MuseumDao mDao, QueryEditMuseum queryEditMuseum) {
        museumDao = mDao;
        this.queryEditMuseum = queryEditMuseum;
    }

    public MuseumFacadeImpl(MuseumDao mDao, QueryAllMuseums queryAllMuseums) {
        museumDao = mDao;
        this.queryAllMuseums = queryAllMuseums;
    }

    public MuseumFacadeImpl(MuseumDao mDao, QueryCreateMuseum queryCreateMuseum) {
        museumDao = mDao;
        this.queryCreateMuseum = queryCreateMuseum;
    }



    @Override
    public void getMuseumByLogin(String login) {

        museumDao.getMuseumByLogin(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Museum>() {
                    @Override
                    public void onSuccess(@NonNull Museum museum) {
                        queryAuthorization.isMuseum(museum);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        queryAuthorization.isUser();

                    }
                })
        ;
    }

    @Override
    public void updateMuseumInfoByMuseum(String image, String description) {
        Museum museum = new Museum();
        museum.description = description;
        museum.image = image;
        //int res = museumDao.updateMuseumInfo(museum);


    }

    @Override
    public void updateMuseumInfoByAdmin(String name, String address, int id) {
        Museum museum = new Museum();
        museum.name = name;
        museum.address = address;
        museumDao.updateMuseumInfo(name,  address, id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Integer>() {
                    @Override
                    public void onSuccess(@NonNull Integer museum) {


                        System.out.println(museum+"kooooooooooooooooool");
                        queryEditMuseum.onSuccess();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        queryEditMuseum.onError();
                    }
                });

    }

    //    public void insertMuseum(String login, String name, String country, String city, String street, String build) {
    @Override
    public void insertMuseum(String login, String name, String address) {

        Museum m = new Museum();
        m.login = login;
        m.address = address;
        m.name = name;
        m.description = null;
        m.image = null;
        museumDao.insertMuseum(m)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Long>() {
                    @Override
                    public void onSuccess(@NonNull Long museum) {
                        queryCreateMuseum.onSuccess(museum);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        queryCreateMuseum.onError();
                    }
                })
        ;

    }

    @Override
    @SuppressLint("CheckResult")

    public void getAllMuseums() {
        museumDao.getAllMuseums()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(museums -> queryAllMuseums.onSuccess(museums))
        ;
    }
}
