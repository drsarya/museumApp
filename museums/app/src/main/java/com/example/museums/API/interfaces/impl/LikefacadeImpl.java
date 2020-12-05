package com.example.museums.API.interfaces.impl;

import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.LikeFacade;
import com.example.museums.API.models.Like;
import com.example.museums.view.fragments.common.likes.QueryGetLikes;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class LikefacadeImpl implements LikeFacade {
    private MuseumDao museumDao;
    private QueryGetLikes queryGetLikes;

    public LikefacadeImpl(MuseumDao museumDao) {
        this.museumDao = museumDao;
    }

    public LikefacadeImpl(MuseumDao museumDao, QueryGetLikes queryGetLikes) {
        this.museumDao = museumDao;
        this.queryGetLikes = queryGetLikes;

    }

    @Override
    public void getLikesByExhId(String exhbtnId, boolean type) {
        museumDao.getLikesByExhId(exhbtnId,type)
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
                        System.out.println("insert");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        queryGetLikes.deleteLike();
                        System.out.println("delete");
                        System.out.println(e.toString());

                    }
                });
    }
}
