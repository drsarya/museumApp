package com.example.museums.API.interfaces.impl;

import android.annotation.SuppressLint;

import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.ExhibitionFacade;
import com.example.museums.API.models.Exhibition;
import com.example.museums.view.fragments.museum.createExhibition.QueryCreateExhibition;
import com.example.museums.view.fragments.museum.museumExhibitions.QueryDeleteExhibition;
import com.example.museums.view.fragments.museum.museumExhibitions.QueryMuseumExhibitions;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ExhibitionFacadeImpl implements ExhibitionFacade {

    private MuseumDao museumDao;
    private QueryCreateExhibition queryCreateExhibition;
    private QueryMuseumExhibitions queryMuseumExhibitions;
    private QueryDeleteExhibition queryDeleteExhibition;

    public ExhibitionFacadeImpl(MuseumDao museumDao) {
        museumDao = museumDao;
    }

    public ExhibitionFacadeImpl(MuseumDao museumDao, QueryCreateExhibition queryCreateExhibition) {
        this.museumDao = museumDao;
        this.queryCreateExhibition = queryCreateExhibition;
    }

    public ExhibitionFacadeImpl(MuseumDao museumDao, QueryMuseumExhibitions queryMuseumExhibitions) {
        this.museumDao = museumDao;
        this.queryMuseumExhibitions = queryMuseumExhibitions;
    }

    public ExhibitionFacadeImpl(MuseumDao museumDao, QueryDeleteExhibition queryDeleteExhibition) {
        this.museumDao = museumDao;
        this.queryDeleteExhibition = queryDeleteExhibition;
    }


    @Override
    public List<Exhibition> getAllExhibitions() {
        List<Exhibition> list = museumDao.getAllExhibitions();
        return list;
    }


    @Override
    public void getMuseumByLogin(String login) {
        museumDao.getMuseumByLogin(login)

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Integer>() {
                    @Override
                    public void onSuccess(@NonNull Integer integer) {
                        queryMuseumExhibitions.onSuccess(integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        queryMuseumExhibitions.onError();
                    }
                })
        ;
    }

    @Override
    public void insertExhbtn(Exhibition exhibition) {
        museumDao.insertExhbtn(exhibition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Long>() {
                    @Override
                    public void onSuccess(@NonNull Long museum) {
                        queryCreateExhibition.onSuccessInsertExhbtn(museum);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        queryCreateExhibition.onErrorInsertExhbtn();
                    }
                })
        ;

    }

    @SuppressLint("CheckResult")
    @Override
    public void getExhibitionByMuseumId(int id) {
        museumDao.getExhbtnByMuseumId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(museums -> queryMuseumExhibitions.onSuccessGetExhbtn(museums));
    }

    @Override
    public void deleteExhibition(int id) {


        museumDao.deleteExhibition(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Integer>() {
                    @Override
                    public void onSuccess(@NonNull Integer integer) {
                        queryDeleteExhibition.onSuccessDeleteExhibition();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        queryDeleteExhibition.onError();
                    }
                });
    }
}
