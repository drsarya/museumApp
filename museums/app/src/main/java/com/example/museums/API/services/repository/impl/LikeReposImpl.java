package com.example.museums.API.services.repository.impl;

import android.annotation.SuppressLint;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.models.like.BaseLike;
import com.example.museums.API.models.like.UserLike;
import com.example.museums.API.presenter.BasePresenter;
import com.example.museums.API.services.LikeService;
import com.example.museums.API.services.repository.LikeRepos;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LikeReposImpl implements LikeRepos {
    LikeService likeService;

    public LikeReposImpl() {
        likeService = RetrofitConnect.getRetrofitConnect().create(LikeService.class);
    }

    @Override
    public void getLikesByArtId(BaseLike baseLike, BasePresenter.Presenter viewContract) {
        likeService.getLikesByArtId(baseLike)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Integer>() {
                    @Override
                    public void onSuccess(@NonNull Integer  listIds) {
                        viewContract.onSuccess(listIds);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        viewContract.onError(e.getMessage());
                    }
                }) ;
    }

    @Override
    public void getLikeByUser(UserLike userLike, BasePresenter.Presenter viewContract) {
        likeService.getLikeByUser(userLike)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<BaseLike>() {
                    @Override
                    public void onSuccess(@NonNull BaseLike  listIds) {
                        viewContract.onSuccess(listIds);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        viewContract.onError(e.getMessage());
                    }
                }) ;
    }

    @Override
    public void deleteLikeByUser(UserLike userLike, BasePresenter.Presenter viewContract) {
        likeService.deleteLikeByUser(userLike)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Boolean>() {
                    @Override
                    public void onSuccess(@NonNull Boolean  listIds) {
                        viewContract.onSuccess(listIds);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        viewContract.onError(e.getMessage());
                    }
                }) ;
    }

    @Override
    public void createLike(UserLike userLike, BasePresenter.Presenter viewContract) {
        likeService.createLike(userLike)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Boolean>() {
                    @Override
                    public void onSuccess(@NonNull Boolean  listIds) {
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
    public void getLikedExhibitsByUser(Integer idUser, BasePresenter.Presenter viewContract) {
        likeService.getLikedExhibitsByUser(idUser).observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    viewContract.onSuccess(list);
                }, error -> {
                    viewContract.onError(error.getMessage());
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getLikedExhibitionsByUser(Integer idUser, BasePresenter.Presenter viewContract) {
        likeService.getLikedExhibitionsByUser(idUser).observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    viewContract.onSuccess(list);
                }, error -> {
                    viewContract.onError(error.getMessage());
                });
    }
}
