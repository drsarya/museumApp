package com.example.museums.API.services.repository.impl;

import android.annotation.SuppressLint;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.OkModel;
import com.example.museums.API.models.museum.BaseMuseum;
import com.example.museums.API.models.museum.ExistingMuseum;
import com.example.museums.API.models.museum.UpdatableMuseum;
import com.example.museums.API.presenter.BasePresenter;
import com.example.museums.API.services.ErrorParser;
import com.example.museums.API.services.api.MuseumService;
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
    public void createMuseum(BaseMuseum baseMuseum, String login, BasePresenter.Presenter viewContract) {
        museumService.createMuseum(baseMuseum, login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<OkModel>() {
                    @Override
                    public void onSuccess(@NonNull OkModel listIds) {
                        viewContract.onSuccess(listIds);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        viewContract.onError(ErrorParser.getMessage(e));
                    }
                }) ;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getAllMuseums(BasePresenter.Presenter viewContract) {
        museumService.getAllMuseums()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                
                .subscribe(
                        viewContract::onSuccess, error -> {
                    viewContract.onError(ErrorParser.getMessage(error));
                });
    }

    @Override
    public void updateMuseum(UpdatableMuseum baseMuseum, BasePresenter.Presenter viewContract) {
        museumService.updateMuseum(baseMuseum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<OkModel>() {
                    @Override
                    public void onSuccess(@NonNull OkModel listIds) {
                        viewContract.onSuccess(listIds);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        viewContract.onError(ErrorParser.getMessage(e));
                    }
                }) ;
    }

    @Override
    public void getMuseumByWorkerId(Integer id, BasePresenter.Presenter viewContract) {
        museumService.getMuseumByWorkerId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<ExistingMuseum>() {
                    @Override
                    public void onSuccess(@NonNull ExistingMuseum listIds) {
                        viewContract.onSuccess(listIds);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        viewContract.onError(ErrorParser.getMessage(e));
                    }
                }) ;
    }
}
