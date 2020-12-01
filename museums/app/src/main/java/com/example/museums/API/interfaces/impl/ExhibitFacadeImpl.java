package com.example.museums.API.interfaces.impl;

import android.annotation.SuppressLint;

import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.ExhibitFacade;
import com.example.museums.API.models.Exhibit;
import com.example.museums.view.fragments.museum.createExhibition.QueryCreateExhibition;
import com.example.museums.view.fragments.museum.editExhibit.QueryUpdateExhibit;
import com.example.museums.view.fragments.museum.editExhibition.QueryDeleteExhibition;
import com.example.museums.view.fragments.museum.museumExhibits.QueryDeleteMuseumExhibit;
import com.example.museums.view.fragments.museum.museumExhibits.QueryListMuseumExhibits;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.FlowableSubscriber;
import io.reactivex.Single;
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

    public ExhibitFacadeImpl(MuseumDao museumDao) {
        this.museumDao = museumDao;
    }

    public ExhibitFacadeImpl(MuseumDao museumDao, QueryCreateExhibition queryCreateExhibition) {
        this.museumDao = museumDao;
        this.queryCreateExhibition = queryCreateExhibition;
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

    @Override
    public List<Exhibit> getAllExhibits() {
        List<Exhibit> list = museumDao.getAllExhibits();
        return list;
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
    public void insertExhibits(List<Exhibit> exhibits) {

        museumDao.insertExhibits(exhibits)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Long[]>() {
                    @Override
                    public void onSuccess(@NonNull Long[] listIds) {
                        queryCreateExhibition.onSuccessInsertsExhibits(listIds);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        queryCreateExhibition.onErrorInsertsExhibits();

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
