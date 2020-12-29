package com.example.museums.view.fragments.museum.mainInfoMuseumEditPage.MainInfoMuseumPageEdit;

import android.graphics.Bitmap;
import android.view.View;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.MuseumFacadeImpl;
import com.example.museums.API.models.MuseumInfoWithoutImage;

public class QueryMainInfoMuseumPageEdit {

    private MainInfoMuseumPageEdit activity;
    private MuseumFacadeImpl museumFacade;
    private MuseumDao museumDao;

    public QueryMainInfoMuseumPageEdit(MainInfoMuseumPageEdit pageEdit) {
        this.activity = pageEdit;
    }

    public void onSuccess(Bitmap bitmap) {
        if (bitmap != null) {
            activity.imageViewMainImage.setImageBitmap(bitmap);
        }
        activity.progressBar.setVisibility(View.GONE);
        activity.chooseImageTextView.setVisibility(View.GONE);

    }

    public void onSuccess(MuseumInfoWithoutImage museumInfoWithoutImage) {
        activity.addressTextView.setText(museumInfoWithoutImage.address);
        System.out.println(museumInfoWithoutImage.toString());
        activity.nameOfMuseumTextView.setText(museumInfoWithoutImage.name);
        if (museumInfoWithoutImage.description != null) {
            activity.descriptionTextView.setText(museumInfoWithoutImage.description);
        } else if (museumInfoWithoutImage.description == null) {
            activity.descriptionTextView.setText(activity.descriptionIsEmpty);
        }
        activity.progressBar.setVisibility(View.GONE);
        activity.chooseImageTextView.setVisibility(View.GONE);

    }

    public void onError() {
        activity.progressBar.setVisibility(View.GONE);
        activity.chooseImageTextView.setVisibility(View.VISIBLE);
    }

    public void getImageQuery(String login) {
        activity.progressBar.setVisibility(View.GONE);
        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        museumFacade = new MuseumFacadeImpl(museumDao, this);
        museumFacade.getMuseumImageByLogin(login);
        museumFacade.getMuseumInfoByLogin(login);

    }


}
