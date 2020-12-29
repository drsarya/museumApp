package com.example.museums.API.interfaces.impl;

import android.annotation.SuppressLint;

import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.LikeFacade;
import com.example.museums.API.models.Like;
import com.example.museums.view.fragments.common.likes.QueryGetLikes;
import com.example.museums.view.fragments.user.likedExhibitions.QueryLikedExhibits;
import com.example.museums.view.fragments.user.likedExhibits.QueryLikedExhibitions;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class LikefacadeImpl implements LikeFacade {
    private MuseumDao museumDao;
    private QueryGetLikes queryGetLikes;
    private QueryLikedExhibits queryLikedExhibits;
    private QueryLikedExhibitions queryLikedExhibitions;


    public LikefacadeImpl(MuseumDao museumDao, QueryGetLikes queryGetLikes) {
        this.museumDao = museumDao;
        this.queryGetLikes = queryGetLikes;

    }

    public LikefacadeImpl(MuseumDao museumDao, QueryLikedExhibits queryLikedExhibits) {
        this.museumDao = museumDao;
        this.queryLikedExhibits = queryLikedExhibits;

    }

    public LikefacadeImpl(MuseumDao museumDao, QueryLikedExhibitions queryLikedExhibitions) {
        this.museumDao = museumDao;
        this.queryLikedExhibitions = queryLikedExhibitions;

    }

    @Override
    public void getLikesByExhId(String exhbtnId, boolean type) {
        museumDao.getLikesByExhId(exhbtnId, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<String>() {
                    @Override
                    public void onSuccess(@NonNull String aLong) {
                        queryGetLikes.onSuccess(aLong);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        queryGetLikes.onError();
                    }
                });
    }

    @Override
    public void getLikeByUserId(Integer userId, String idExhibit, boolean type) {
        museumDao.getLikeByUserId(userId, idExhibit, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Like>() {
                    @Override
                    public void onSuccess(@NonNull Like aLong) {
                        queryGetLikes.setLike();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        queryGetLikes.setDontLike();
                    }
                });
    }


    @Override
    public void deleteLikesByExhbtId(Integer iduser, String idExhb, boolean type) {
        museumDao.deleteLikesByExhbtId(iduser, idExhb, type).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Integer>() {
                    @Override
                    public void onSuccess(@NonNull Integer aLong) {
                        queryGetLikes.setDontLike();
                        queryGetLikes.getCountLikes();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        queryGetLikes.onError();
                    }
                });
    }

    @Override
    public void insertLike(Integer iduser, String idExhb, boolean type) {
        Like like = new Like();
        like.idUserFk = iduser;
        like.idExhb = idExhb;
        like.type = type;
        museumDao.insertLike(like)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Long>() {
                    @Override
                    public void onSuccess(@NonNull Long aLong) {
                        queryGetLikes.getCountLikes();
                        queryGetLikes.setLike();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        queryGetLikes.deleteLike();
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getExhibitsLikedByUser(Integer iduser) {
        museumDao.getAllExhibitsLikedByUser(iduser, true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(v -> queryLikedExhibits.onSuccess(v)
                );

    }

    @SuppressLint("CheckResult")
    @Override
    public void getExhibitionsLikedByUser(Integer iduser) {
        museumDao.getAllExhibitionsLikedByUser(iduser, false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(v -> queryLikedExhibitions.onSuccess(v)
                );

    }
}
