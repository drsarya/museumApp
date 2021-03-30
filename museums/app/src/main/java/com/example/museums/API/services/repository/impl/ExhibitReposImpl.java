package com.example.museums.API.services.repository.impl;

import android.annotation.SuppressLint;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.exhibit.BaseExhibit;
import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.API.presenter.BasePresenter;
import com.example.museums.API.services.api.ExhibitService;
import com.example.museums.API.services.repository.ExhibitRepos;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ExhibitReposImpl implements ExhibitRepos {
    ExhibitService exhibitService;

    public ExhibitReposImpl() {
        exhibitService = RetrofitConnect.getRetrofitConnect().create(ExhibitService.class);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getAllExhibits(BasePresenter.Presenter viewContract) {
        exhibitService.getAllExhibits()
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    viewContract.onSuccess(list);
                }, error -> {
                    viewContract.onError(error.getMessage());
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getExhibitsByMuseumId(Integer id, BasePresenter.Presenter viewContract) {
        exhibitService
                .getExhibitsByMuseumId(id)
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    viewContract.onSuccess(list);
                }, error -> {
                    viewContract.onError(error.getMessage());
                });
    }

    @Override
    public void deleteExhibit(int id, BasePresenter.Presenter viewContract) {
        exhibitService.deleteExhibit(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Boolean>() {
                    @Override
                    public void onSuccess(@NonNull Boolean listIds) {
                        viewContract.onSuccess(listIds);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        viewContract.onError(e.getMessage());
                    }
                }) ;
    }

    @Override
    public void createExhibit(BaseExhibit exhibit, BasePresenter.Presenter viewContract) {
        exhibitService.createExhibit(exhibit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<ExistingExhibit>() {
                    @Override
                    public void onSuccess(@NonNull ExistingExhibit listIds) {
                        viewContract.onSuccess(listIds);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        viewContract.onError(e.getMessage());
                    }
                }) ;
    }

    @Override
    public void updateExhibit(ExistingExhibit exhibit, BasePresenter.Presenter viewContract) {
        exhibitService.updateExhibit(exhibit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<ExistingExhibit>() {
                    @Override
                    public void onSuccess(@NonNull ExistingExhibit listIds) {
                        viewContract.onSuccess(listIds);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        viewContract.onError(e.getMessage());
                    }
                }) ;
    }
}
