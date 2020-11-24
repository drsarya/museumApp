package com.example.museums.view.fragments.museum.MainInfoMuseumEditPage.DialogChangeImageMuseum;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.MuseumFacadeImpl;

public class QueryChangeMuseumImage {

    private MuseumFacadeImpl museumFacade;
    private MuseumDao museumDao;
    private DialogChangeMuseumPhoto activity;

    public QueryChangeMuseumImage(DialogChangeMuseumPhoto fragment) {
        this.activity = fragment;
    }

    public void onSuccess() {
        Toast.makeText(activity.getContext(),
                "Успешное обновление", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);
    }

    public void onError() {
        Toast.makeText(activity.getContext(),
                "Ошибка обновления", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

    }

    public void getQuery(String login, Bitmap image) {
        activity.progressBar.setVisibility(View.GONE);
        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        museumFacade = new MuseumFacadeImpl(museumDao, this);
        museumFacade.updateMuseumImage(login,image);
    }
}
