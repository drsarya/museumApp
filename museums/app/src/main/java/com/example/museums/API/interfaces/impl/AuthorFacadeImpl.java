package com.example.museums.API.interfaces.impl;

import android.annotation.SuppressLint;

import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.AuthorFacade;
import com.example.museums.API.models.Author;
import com.example.museums.view.fragments.museum.exhibition.createExhibition.QueryCreateExhibition;
import com.example.museums.view.fragments.museum.authors.QueryAuthor;
import com.example.museums.view.fragments.museum.exhibit.QueryInsertAuthor;
import com.example.museums.view.fragments.museum.exhibit.editExhibit.QueryUpdateExhibit;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class AuthorFacadeImpl implements AuthorFacade {
    private MuseumDao museumDao;
    private QueryAuthor queryAuthor;
    private QueryCreateExhibition queryCreateExhibition;
    private QueryUpdateExhibit queryUpdateExhibit;
    private QueryInsertAuthor queryInsertAuthor;

    public AuthorFacadeImpl(MuseumDao museumDao) {
        this.museumDao = museumDao;
    }

    public AuthorFacadeImpl(MuseumDao museumDao, QueryAuthor queryAuthor) {
        this.museumDao = museumDao;
        this.queryAuthor = queryAuthor;
    }

    public AuthorFacadeImpl(MuseumDao museumDao, QueryInsertAuthor queryInsertAuthor) {
        this.museumDao = museumDao;
        this.queryInsertAuthor = queryInsertAuthor;
    }

    public AuthorFacadeImpl(MuseumDao museumDao, QueryUpdateExhibit queryUpdateExhibit) {
        this.museumDao = museumDao;
        this.queryUpdateExhibit = queryUpdateExhibit;
    }


    public AuthorFacadeImpl(MuseumDao museumDao, QueryCreateExhibition queryCreateExhibition) {
        this.museumDao = museumDao;
        this.queryCreateExhibition = queryCreateExhibition;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getAuthors() {
        museumDao.getAllAuthors()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(museums -> queryAuthor.onSuccess(museums));
    }

    @SuppressLint("CheckResult")
    @Override
    public void getAuthorByName(String name) {
        museumDao.getAllAuthorByName(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Integer>() {
                    @Override
                    public void onSuccess(@NonNull Integer aLong) {

                        queryInsertAuthor.onSuccess(aLong);

                    }

                    @Override
                    public void onError(Throwable e) {

                        queryInsertAuthor.onError();

                    }
                });

    }


    @Override
    public void insertAuthor(String author) {
        Author authorModel = new Author();
        authorModel.fullName = author;
        museumDao.insertAuthor(authorModel).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Long>() {
                    @Override
                    public void onSuccess(@NonNull Long aLong) {
                        if (aLong == null) {
                            queryInsertAuthor.onSuccess(null);
                        } else {
                            queryInsertAuthor.onSuccess(aLong.intValue());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        queryInsertAuthor.onErrorInsert();

                    }
                });
    }

    @Override
    public void insertAuthors(List<Author> author) {
        System.out.println(author.size() + "изначальное число");
        museumDao.insertAuthors(author).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Long>>() {
                    @Override
                    public void onSuccess(@NonNull List<Long> aLong) {
                        System.out.println(aLong.size() + "конечное число");

                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e);
                    }
                });
    }
}
