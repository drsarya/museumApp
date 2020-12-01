package com.example.museums.API.interfaces.impl;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;

import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.MuseumFacade;
import com.example.museums.API.models.Museum;
import com.example.museums.API.models.MuseumInfoWithoutImage;
import com.example.museums.view.activities.common.Authorization.QueryAuthorization;
import com.example.museums.view.activities.common.RegistrationMuseum.QueryRegistrationMuseum;
import com.example.museums.view.fragments.admin.allMuseums.QueryAllMuseums;
import com.example.museums.view.fragments.admin.createMuseum.QueryCreateMuseum;
import com.example.museums.view.fragments.admin.editMuseum.QueryEditMuseum;
import com.example.museums.view.fragments.museum.mainInfoMuseumEditPage.DialogChangeDescriptionMuseum.QueryDialogChangeDescriptionMuseum;
import com.example.museums.view.fragments.museum.mainInfoMuseumEditPage.DialogChangeImageMuseum.QueryChangeMuseumImage;
import com.example.museums.view.fragments.museum.mainInfoMuseumEditPage.MainInfoMuseumPageEdit.QueryMainInfoMuseumPageEdit;
import com.example.museums.view.fragments.museum.createExhibition.QueryCreateExhibition;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MuseumFacadeImpl implements MuseumFacade {
    private MuseumDao museumDao;
    private QueryAuthorization queryAuthorization;
    private QueryCreateMuseum queryCreateMuseum;
    private QueryAllMuseums queryAllMuseums;
    private QueryEditMuseum queryEditMuseum;
    private QueryRegistrationMuseum queryRegistrationMuseum;
    private QueryChangeMuseumImage queryChangeMuseumImage;
    private QueryDialogChangeDescriptionMuseum queryDialogChangeDescriptionMuseum;
    private QueryMainInfoMuseumPageEdit queryMainInfoMuseumPageEdit;
    private QueryCreateExhibition queryCreateExhibition;

    public MuseumFacadeImpl(MuseumDao museumDao) {
        this.museumDao = museumDao;
    }

    public MuseumFacadeImpl(MuseumDao mDao, QueryAuthorization queryAuthorization) {
        museumDao = mDao;
        this.queryAuthorization = queryAuthorization;
    }


    public MuseumFacadeImpl(MuseumDao mDao, QueryDialogChangeDescriptionMuseum queryDialogChangeDescriptionMuseum) {
        museumDao = mDao;
        this.queryDialogChangeDescriptionMuseum = queryDialogChangeDescriptionMuseum;
    }

    public MuseumFacadeImpl(MuseumDao mDao, QueryChangeMuseumImage queryChangeMuseumImage) {
        museumDao = mDao;
        this.queryChangeMuseumImage = queryChangeMuseumImage;
    }

    public MuseumFacadeImpl(MuseumDao mDao, QueryCreateExhibition queryCreateExhibition) {
        museumDao = mDao;
        this.queryCreateExhibition = queryCreateExhibition;
    }

    public MuseumFacadeImpl(MuseumDao mDao, QueryMainInfoMuseumPageEdit queryMainInfoMuseumPageEdit) {
        museumDao = mDao;
        this.queryMainInfoMuseumPageEdit = queryMainInfoMuseumPageEdit;
    }

    public MuseumFacadeImpl(MuseumDao mDao, QueryEditMuseum queryEditMuseum) {
        museumDao = mDao;
        this.queryEditMuseum = queryEditMuseum;
    }

    public MuseumFacadeImpl(MuseumDao mDao, QueryAllMuseums queryAllMuseums) {
        museumDao = mDao;
        this.queryAllMuseums = queryAllMuseums;
    }

    public MuseumFacadeImpl(MuseumDao mDao, QueryRegistrationMuseum queryRegistrationMuseum) {
        museumDao = mDao;
        this.queryRegistrationMuseum = queryRegistrationMuseum;
    }

    public MuseumFacadeImpl(MuseumDao mDao, QueryCreateMuseum queryCreateMuseum) {
        museumDao = mDao;
        this.queryCreateMuseum = queryCreateMuseum;
    }


    @Override
    public void getMuseumByLogin(String login) {

        museumDao.getMuseumByLogin(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Integer>() {
                    @Override
                    public void onSuccess(@NonNull Integer id) {
                        queryAuthorization.isMuseum(id);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        queryAuthorization.isUser();
                    }
                })
        ;
    }

    @Override
    public void updateMuseumDescription(String login, String description) {
        Museum museum = new Museum();
        museum.description = description;
        museumDao.updateMuseumDescription(description, login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Integer>() {
                    @Override
                    public void onSuccess(@NonNull Integer museum) {
                        queryDialogChangeDescriptionMuseum.onSuccess();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        queryDialogChangeDescriptionMuseum.onError();
                    }
                });

    }

    @Override
    public void updateMuseumImage(String login, Bitmap image) {
        Museum museum = new Museum();
        museum.image = image;
        museumDao.updateMuseumImage(image, login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Integer>() {
                    @Override
                    public void onSuccess(@NonNull Integer museum) {
                        queryChangeMuseumImage.onSuccess();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        queryChangeMuseumImage.onError();
                    }
                });

    }

    @Override
    public void updateMuseumInfoByAdmin(String name, String address, int id) {
        Museum museum = new Museum();
        museum.nameMuseum = name;
        museum.address = address;
        museumDao.updateMuseumInfo(name, address, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Integer>() {
                    @Override
                    public void onSuccess(@NonNull Integer museum) {
                        queryEditMuseum.onSuccess();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        queryEditMuseum.onError();
                    }
                });

    }

    @Override
    public void getMuseumImageByLogin(String login) {
        museumDao.getMuseumImage(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Bitmap>() {
                    @Override
                    public void onSuccess(@NonNull Bitmap museum) {
                        queryMainInfoMuseumPageEdit.onSuccess(museum);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        queryMainInfoMuseumPageEdit.onError();
                    }
                })
        ;
    }

    @Override
    public void getMuseumIDByLogin(String login) {

        museumDao.getMuseumByLogin(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Integer>() {
                    @Override
                    public void onSuccess(@NonNull Integer id) {
                        queryCreateExhibition.onSuccess(id);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        queryCreateExhibition.onError();

                    }
                })
        ;
    }

    @Override
    public void getMuseumInfoByLogin(String login) {
        museumDao.getMuseumInfo(login).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<MuseumInfoWithoutImage>() {
                    @Override
                    public void onSuccess(@NonNull MuseumInfoWithoutImage museum) {
                        queryMainInfoMuseumPageEdit.onSuccess(museum);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        queryMainInfoMuseumPageEdit.onError();
                    }
                })
        ;
    }



    //    public void insertMuseum(String login, String name, String country, String city, String street, String build) {
    @Override
    public void insertMuseum(String login, String name, String address) {

        Museum m = new Museum();
        m.login = login;
        m.address = address;
        m.nameMuseum = name;
        m.description = null;
        m.image = null;
        museumDao.insertMuseum(m)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Long>() {
                    @Override
                    public void onSuccess(@NonNull Long museum) {
                        queryCreateMuseum.onSuccess(museum);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        queryCreateMuseum.onError();
                    }
                })
        ;

    }

    @Override
    @SuppressLint("CheckResult")

    public void getAllMuseums() {
        museumDao.getAllMuseums()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(museums -> queryAllMuseums.onSuccess(museums))
        ;
    }

    @Override
    public void getMuseumByLoginAndIdCode(String login, int id, String password, boolean type) {
        museumDao.getMuseumByLoginAndIdCode(login, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Museum>() {
                    @Override
                    public void onSuccess(@NonNull Museum museum) {
                        System.out.println(1);
                        UserFacadeImpl userFacade = new UserFacadeImpl(museumDao, queryRegistrationMuseum);
                        userFacade.insertUserMuseum(login, password, type);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        queryRegistrationMuseum.onError();
                    }
                });
    }
}
