package com.example.museums.API.services.repository.impl;

import android.annotation.SuppressLint;

import com.example.museums.API.RetrofitConnect;
import com.example.museums.API.presenter.BasePresenter;
import com.example.museums.API.services.api.AuthorService;
import com.example.museums.API.services.repository.AuthorRepos;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AuthorReposImpl implements AuthorRepos {

    AuthorService authorService;

    public AuthorReposImpl() {
        authorService = RetrofitConnect.getRetrofitConnect().create(AuthorService.class);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getAuthors(BasePresenter.Presenter viewContract) {
        authorService.getAuthors()
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(authors -> {
                    viewContract.onSuccess(authors);
                }, error -> {
                    viewContract.onError(error.getMessage());
                });
    }
}
