package com.example.museums.view.fragments.museum.MainInfoMuseumEditPage.MainInfoMuseumPageEdit;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.MuseumFacadeImpl;

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
    }

    public void onError() {
        Toast.makeText(activity.getContext(),
                "Ошибка получения данных", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

    }

    public void getQuery(String login) {
        activity.progressBar.setVisibility(View.GONE);
        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        museumFacade = new MuseumFacadeImpl(museumDao, this);
        museumFacade.getMuseumImageByLogin(login);
    }
}
