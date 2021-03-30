package com.example.museums.API.services.repository.impl;

import android.annotation.SuppressLint;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.exhibition.BaseExhibition;
import com.example.museums.API.models.exhibition.ExistingExhibition;
import com.example.museums.API.presenter.BasePresenter;
import com.example.museums.API.services.ExhibitionService;
import com.example.museums.API.services.repository.ExhibitionRepos;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ExhibitionReposImpl implements ExhibitionRepos {
    ExhibitionService exhibitionService;

    public ExhibitionReposImpl() {
        exhibitionService = RetrofitConnect.getRetrofitConnect().create(ExhibitionService.class);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getAllExhibitions(BasePresenter.Presenter viewContract) {
        exhibitionService.getAllExhibitions()
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(viewContract::onSuccess, error -> {
                    viewContract.onError(error.getMessage());
                });
    }

    @Override
    public void deleteExhibition(int id, BasePresenter.Presenter viewContract) {
        exhibitionService.deleteExhibition(id)
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
                });
    }

    @Override
    public void createExhibition(BaseExhibition exhibition, BasePresenter.Presenter viewContract) {
        exhibitionService.createExhibition(exhibition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<ExistingExhibition>() {
                    @Override
                    public void onSuccess(@NonNull ExistingExhibition listIds) {
                        viewContract.onSuccess(listIds);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        viewContract.onError(e.getMessage());
                    }
                });
    }

    @Override
    public void updateExhibition(ExistingExhibition exhibition, BasePresenter.Presenter viewContract) {
        exhibitionService.updateExhibition(exhibition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<ExistingExhibition>() {
                    @Override
                    public void onSuccess(@NonNull ExistingExhibition listIds) {
                        viewContract.onSuccess(listIds);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        viewContract.onError(e.getMessage());
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getExhibitionsByMuseumId(int id, BasePresenter.Presenter viewContract) {
        exhibitionService.getExhibitionsByMuseumId(id)
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(authors -> {
                    viewContract.onSuccess(authors);
                }, error -> {
                    viewContract.onError(error.getMessage());
                });

    }
}
