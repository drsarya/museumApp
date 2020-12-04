package com.example.museums.API.interfaces.impl;

import android.annotation.SuppressLint;

import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.ExhibitFacade;
import com.example.museums.API.models.Exhibit;
import com.example.museums.API.models.Like;
import com.example.museums.API.models.NewExhibitModel;
import com.example.museums.view.fragments.museum.exhibit.createExhibit.QueryInsertExhibit;
import com.example.museums.view.fragments.museum.exhibition.createExhibition.QueryCreateExhibition;
import com.example.museums.view.fragments.museum.exhibit.editExhibit.QueryUpdateExhibit;
import com.example.museums.view.fragments.museum.museumExhibitions.QueryDeleteExhibition;
import com.example.museums.view.fragments.museum.museumExhibits.QueryDeleteMuseumExhibit;
import com.example.museums.view.fragments.museum.museumExhibits.QueryListMuseumExhibits;
import com.example.museums.view.fragments.user.exhibits.QueryExhibits;

import java.util.List;

import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ExhibitFacadeImpl implements ExhibitFacade {
    private MuseumDao museumDao;
    private QueryCreateExhibition queryCreateExhibition;
    private QueryDeleteMuseumExhibit queryDeleteMuseumExhibit;
    private QueryListMuseumExhibits queryListMuseumExhibits;
    private QueryUpdateExhibit queryUpdateExhibit;
    private QueryDeleteExhibition queryDeleteExhibition;
    private QueryInsertExhibit queryInsertExhibit;
    private QueryExhibits queryExhibits;
    public ExhibitFacadeImpl(MuseumDao museumDao) {
        this.museumDao = museumDao;
    }

    public ExhibitFacadeImpl(MuseumDao museumDao, QueryExhibits queryExhibits) {
        this.museumDao = museumDao;
        this.queryExhibits = queryExhibits;
    }
    public ExhibitFacadeImpl(MuseumDao museumDao, QueryInsertExhibit queryInsertExhibit) {
        this.museumDao = museumDao;
        this.queryInsertExhibit = queryInsertExhibit;
    }

    public ExhibitFacadeImpl(MuseumDao museumDao, QueryDeleteExhibition queryDeleteExhibition) {
        this.museumDao = museumDao;
        this.queryDeleteExhibition = queryDeleteExhibition;
    }

    public ExhibitFacadeImpl(MuseumDao museumDao, QueryUpdateExhibit queryUpdateExhibit) {
        this.museumDao = museumDao;
        this.queryUpdateExhibit = queryUpdateExhibit;
    }

    public ExhibitFacadeImpl(MuseumDao museumDao, QueryDeleteMuseumExhibit queryDeleteMuseumExhibit) {
        this.museumDao = museumDao;
        this.queryDeleteMuseumExhibit = queryDeleteMuseumExhibit;
    }

    public ExhibitFacadeImpl(MuseumDao museumDao, QueryListMuseumExhibits queryListMuseumExhibits) {
        this.museumDao = museumDao;
        this.queryListMuseumExhibits = queryListMuseumExhibits;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getAllExhibits() {
         museumDao.getAllExhibits().observeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
         .subscribe( exhibitModels -> {

             queryExhibits.onSuccess(exhibitModels);
         }, error ->{
             queryExhibits.onError();

         });

    }

    @SuppressLint("CheckResult")
    @Override
    public void getExhibitsByMuseumLogin(String login) {

        museumDao.getExhibitsByMuseumId(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(museums -> queryListMuseumExhibits.onSuccess(museums));
    }

    @Override
    public void deleteExhibits(int idExhibition) {
        museumDao.deleteExhibits(idExhibition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Integer>() {
                    @Override
                    public void onSuccess(@NonNull Integer integer) {
                        queryDeleteExhibition.onSuccess();
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        queryDeleteExhibition.onError();
                    }
                });
    }



    @Override
    public void insertExhibit(Exhibit exhibit) {
        museumDao.insertExhibit(exhibit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Long>() {
                    @Override
                    public void onSuccess(@NonNull Long listIds) {
                        queryInsertExhibit.onSuccess(listIds.intValue());

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println(e.toString());
                        queryInsertExhibit.onError();

                    }
                })
        ;

    }

    @Override
    public void deleteExhibit(int id) {
        museumDao.deleteExhibit(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Integer>() {
                    @Override
                    public void onSuccess(@NonNull Integer integer) {
                        queryDeleteMuseumExhibit.onSuccess();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        queryDeleteMuseumExhibit.onError();
                    }
                });
    }

    @Override
    public void updateExhibit(Exhibit exhibit) {
        museumDao.updateExhibitInfo(exhibit).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Integer>() {
                    @Override
                    public void onSuccess(@NonNull Integer museum) {

                        queryUpdateExhibit.onSuccessUpdate(museum);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        queryUpdateExhibit.onErrorUpdate();
                    }
                });
    }


}
