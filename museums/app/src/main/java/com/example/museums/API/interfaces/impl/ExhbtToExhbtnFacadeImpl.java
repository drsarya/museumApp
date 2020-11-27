package com.example.museums.API.interfaces.impl;

import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.ExhbtToExbtnFacade;
import com.example.museums.API.models.Exhibit;
import com.example.museums.API.models.ExhibitToExhbtn;
import com.example.museums.view.fragments.museum.createExhibition.QueryCreateExhibition;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ExhbtToExhbtnFacadeImpl implements ExhbtToExbtnFacade {
    private MuseumDao museumDao;
    private QueryCreateExhibition queryCreateExhibition;

    public ExhbtToExhbtnFacadeImpl(MuseumDao museumDao) {
        this.museumDao = museumDao;
    }

    public ExhbtToExhbtnFacadeImpl(MuseumDao museumDao, QueryCreateExhibition queryCreateExhibition) {
        this.museumDao = museumDao;
        this.queryCreateExhibition = queryCreateExhibition;
    }

    @Override
    public List<Exhibit> getExhibitsByExhdtnId(String ixhbtnId) {

        List<Exhibit> list = museumDao.getExhibitsByExhdtnId(ixhbtnId);
        return list;
    }

    @Override
    public void insertExhbToExbtn(List<ExhibitToExhbtn> exhbtns) {
        museumDao.insertExhbToExbtn(exhbtns)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Long[]>() {
                    @Override
                    public void onSuccess(@NonNull Long[] listIds) {
                        queryCreateExhibition.onSuccessInsertsExhbtToExhbtn(listIds);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        queryCreateExhibition.onErrorInsertsExhbtToExhbtn();

                    }
                })
        ;

    }


}
