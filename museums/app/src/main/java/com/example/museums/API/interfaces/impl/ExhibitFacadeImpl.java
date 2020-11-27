package com.example.museums.API.interfaces.impl;

import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.ExhibitFacade;
import com.example.museums.API.models.Exhibit;
import com.example.museums.view.fragments.museum.createExhibition.QueryCreateExhibition;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ExhibitFacadeImpl implements ExhibitFacade {
    private MuseumDao museumDao;
    private QueryCreateExhibition queryCreateExhibition;

    public ExhibitFacadeImpl(MuseumDao museumDao) {
        this.museumDao = museumDao;
    }

    public ExhibitFacadeImpl(MuseumDao museumDao, QueryCreateExhibition queryCreateExhibition) {
        this.museumDao = museumDao;
        this.queryCreateExhibition = queryCreateExhibition;
    }


    @Override
    public List<Exhibit> getAllExhibits() {
        List<Exhibit> list = museumDao.getAllExhibits();
        return list;
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
}
