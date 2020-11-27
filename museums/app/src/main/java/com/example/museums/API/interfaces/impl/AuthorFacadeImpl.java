package com.example.museums.API.interfaces.impl;

import android.annotation.SuppressLint;

import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.AuthorFacade;
import com.example.museums.API.models.Author;
import com.example.museums.view.fragments.museum.createExhibition.authors.QueryAuthor;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class AuthorFacadeImpl implements AuthorFacade {
    private MuseumDao museumDao;
    private QueryAuthor queryAuthor;

    public AuthorFacadeImpl(MuseumDao museumDao) {
        this.museumDao = museumDao;
    }

    public AuthorFacadeImpl(MuseumDao museumDao, QueryAuthor queryAuthor) {
        this.museumDao = museumDao;
        this.queryAuthor = queryAuthor;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getAuthors() {
        museumDao.getAllAuthors()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(museums -> queryAuthor.onSuccess(museums));
    }

    @Override
    public void insertAuthor(String author) {
        Author authorModel = new Author();
        authorModel.fullName = author;
        museumDao.insertAuthor(authorModel) .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Long>() {
                    @Override
                    public void onSuccess(@NonNull Long aLong) {
                     }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }
}
