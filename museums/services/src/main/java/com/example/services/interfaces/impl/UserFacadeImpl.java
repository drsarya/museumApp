package com.example.services.interfaces.impl;

import android.annotation.SuppressLint;
import android.content.Context;

import android.util.Log;
import android.widget.Toast;


import com.example.services.MuseumDao;
import com.example.services.interfaces.UserFacade;
import com.example.services.models.User;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class UserFacadeImpl implements UserFacade {
    private MuseumDao museumDao;
    private Context context;
    public UserFacadeImpl(MuseumDao mDao, Context appContext) {
        museumDao = mDao;
        context = appContext;
    }

    @SuppressLint("CheckResult")
    @Override
    public User getUser(String login, String password) {

       AtomicReference<User> userC = new AtomicReference<>(new User());

        museumDao.getUser(login, password)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<User>() {




                    @Override
                    public void onSuccess(@NonNull User user) {
                        resGetUser(user);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context,
                                "ошибка входа", Toast.LENGTH_SHORT).show();

                    }

                });



        return userC.get();
    }

    @SuppressLint("CheckResult")
    @Override
    public long insertUser(String login, String password) {
        User user = new User();
        user.login = login;
        user.password = password;
       AtomicLong lo=  new AtomicLong(0);
         museumDao.insertUser(user) .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new DisposableSingleObserver<Long>() {


                     @Override
                     public void onSuccess(@NonNull Long aLong) {
                         System.out.println("ssssssssss");

                     }

                     @Override
                     public void onError(Throwable e) {
                         Toast.makeText(context,
                                 "ошибка входа", Toast.LENGTH_SHORT).show();

                     }

                 });
         return lo.get();
    }

    private void resGetUser( User user) {

        if (user == null) {
            //showerror
            Toast.makeText(context,
                    "ошибка входа", Toast.LENGTH_SHORT).show();
        } else {
            //showerror
            Toast.makeText(context,
                    "всё отлично", Toast.LENGTH_SHORT).show();
        }


    }

}
