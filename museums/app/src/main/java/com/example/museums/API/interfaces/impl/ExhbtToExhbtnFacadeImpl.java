package com.example.museums.API.interfaces.impl;

import android.annotation.SuppressLint;

import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.ExhbtToExbtnFacade;
import com.example.museums.API.models.ExhibitToExhbtn;
import com.example.museums.view.fragments.museum.exhibit.createExhibit.QueryInsertExhibit;
import com.example.museums.view.fragments.museum.exhibition.createExhibition.QueryCreateExhibition;
import com.example.museums.view.fragments.museum.exhibition.editExhibition.QueryGetExhibitsFromExhibition;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ExhbtToExhbtnFacadeImpl implements ExhbtToExbtnFacade {
    private MuseumDao museumDao;
    private QueryInsertExhibit queryInsertExhibit;

    private QueryGetExhibitsFromExhibition queryGetExhibitsFromExhibition;

    public ExhbtToExhbtnFacadeImpl(MuseumDao museumDao, QueryGetExhibitsFromExhibition queryGetExhibitsFromExhibition) {
        this.museumDao = museumDao;
        this.queryGetExhibitsFromExhibition = queryGetExhibitsFromExhibition;
    }

    public ExhbtToExhbtnFacadeImpl(MuseumDao museumDao, QueryInsertExhibit queryInsertExhibit) {
        this.museumDao = museumDao;
        this.queryInsertExhibit = queryInsertExhibit;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getExhibitsByExhdtnId(String ixhbtnId) {

        museumDao.getExhibitsByExhibitionId(ixhbtnId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(museums -> queryGetExhibitsFromExhibition.onSuccess(museums));
        ;
    }


    @Override
    public void insertExhbToExbtn(ExhibitToExhbtn exhbtns) {
        museumDao.insertExhbToExbtn(exhbtns)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Long>() {
                    @Override
                    public void onSuccess(@NonNull Long listIds) {
                        queryInsertExhibit.onSuccessInsertExhbtToExhbn();
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        queryInsertExhibit.onErrorInsertExhbtToExhbn();
                    }
                })
        ;
    }


}
