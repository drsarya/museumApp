package com.example.museums.API.services.repository.impl;

import android.annotation.SuppressLint;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.museum.BaseMuseum;
import com.example.museums.API.models.museum.Museum;
import com.example.museums.API.models.museum.UpdatableMuseum;
import com.example.museums.API.presenter.BasePresenter;
import com.example.museums.API.services.MuseumService;
import com.example.museums.API.services.repository.MuseumRepos;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MuseumReposImpl implements MuseumRepos {
    MuseumService museumService;

    public MuseumReposImpl() {
        museumService = RetrofitConnect.getRetrofitConnect().create(MuseumService.class);
    }

    @Override
    public void createMuseum(BaseMuseum baseMuseum, BasePresenter.Presenter viewContract) {
        museumService.createMuseum(baseMuseum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Museum>() {
                    @Override
                    public void onSuccess(@NonNull Museum  listIds) {
                        viewContract.onSuccess(listIds);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        viewContract.onError(e.getMessage());
                    }
                }) ;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getAllMuseums(BasePresenter.Presenter viewContract) {
        museumService.getAllMuseums()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    viewContract.onSuccess(list);
                }, error -> {
                    viewContract.onError(error.getMessage());
                });
    }

    @Override
    public void updateMuseum(UpdatableMuseum baseMuseum, BasePresenter.Presenter viewContract) {
        museumService.updateMuseum(baseMuseum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Museum>() {
                    @Override
                    public void onSuccess(@NonNull Museum  listIds) {
                        viewContract.onSuccess(listIds);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        viewContract.onError(e.getMessage());
                    }
                }) ;
    }

    @Override
    public void getMuseumByWorkerId(Integer id, BasePresenter.Presenter viewContract) {
        museumService.getMuseumByWorkerId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Museum>() {
                    @Override
                    public void onSuccess(@NonNull Museum  listIds) {
                        viewContract.onSuccess(listIds);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        viewContract.onError(e.getMessage());
                    }
                }) ;
    }
}
