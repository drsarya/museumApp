package com.example.museums.API.interfaces.impl;

import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.ExhibitionFacade;
import com.example.museums.API.models.Exhibition;
import com.example.museums.view.fragments.museum.createExhibition.QueryCreateExhibition;

import org.w3c.dom.ls.LSOutput;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ExhibitionFacadeImpl implements ExhibitionFacade {

    private MuseumDao museumDao;
    private QueryCreateExhibition queryCreateExhibition;

    public ExhibitionFacadeImpl(MuseumDao museumDao) {
        museumDao = museumDao;
    }

    public ExhibitionFacadeImpl(MuseumDao museumDao, QueryCreateExhibition queryCreateExhibition) {
        this.museumDao = museumDao;
        this.queryCreateExhibition = queryCreateExhibition;
    }

    @Override
    public List<Exhibition> getAllExhibitions() {
        List<Exhibition> list = museumDao.getAllExhibitions();
        return list;
    }

    @Override
    public List<Exhibition> getExhbtnByMuseumId(String id) {
        List<Exhibition> list = museumDao.getExhbtnByMuseumId(id);
        return list;
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
}