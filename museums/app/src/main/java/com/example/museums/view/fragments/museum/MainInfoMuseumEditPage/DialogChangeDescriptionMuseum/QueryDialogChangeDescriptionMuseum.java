package com.example.museums.view.fragments.museum.MainInfoMuseumEditPage.DialogChangeDescriptionMuseum;

import android.view.View;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.MuseumFacadeImpl;

public class QueryDialogChangeDescriptionMuseum {

    private MuseumFacadeImpl museumFacade;
    private MuseumDao museumDao;
    private DialogChangeDescriptionMuseum activity;

    public QueryDialogChangeDescriptionMuseum(DialogChangeDescriptionMuseum fragment) {
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

    public void getQuery(String login, String description) {
        activity.progressBar.setVisibility(View.GONE);
        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        museumFacade = new MuseumFacadeImpl(museumDao, this);
        museumFacade.updateMuseumDescription(login,description);
    }
}
