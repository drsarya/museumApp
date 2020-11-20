package com.example.museums.API.interfaces.impl;

import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.MuseumFacade;
import com.example.museums.API.models.Museum;
import com.example.museums.API.models.User;
import com.example.museums.view.activities.common.Authorization.QueryAuthorization;

import org.w3c.dom.ls.LSOutput;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MuseumFacadeImpl implements MuseumFacade {
    private MuseumDao museumDao;
    private QueryAuthorization queryAuthorization;

    public MuseumFacadeImpl(MuseumDao museumDao) {
       this.museumDao = museumDao;
    }

    public MuseumFacadeImpl(MuseumDao mDao, QueryAuthorization queryAuthorization) {
        museumDao = mDao;
        this.queryAuthorization = queryAuthorization;
    }

    @Override
    public void getMuseumById(String id) {
        Museum museum = museumDao.getMuseumById(id);

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
    public int updateMuseumInfo(String image, String description) {
        Museum museum = new Museum();
        museum.description = description;
        museum.image = image;
        int res = museumDao.updateMuseumInfo(museum);

        return res;
    }

    @Override
    public void insertMuseum(String login, String name, String country, String city, String street, String build) {

        Museum m = new Museum();
        m.build = build;
        m.city = city;
        m.country = country;
        m.login = login;
        m.street = street;
        m.name = name;
        m.description =null;
        m.image = null;
        museumDao.insertMuseum(m)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Long>() {
                    @Override
                    public void onSuccess(@NonNull Long museum) {
                        System.out.println(museum+"dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
                     //   queryAuthorization.isMuseum(museum);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                     //   queryAuthorization.isUser();

                    }
                })
        ;

    }
}
