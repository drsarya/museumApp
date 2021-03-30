package com.example.museums.view.fragments.admin.editMuseum;

import android.view.View;
import android.widget.Toast;

import com.example.museums.API.services.impl.MuseumFacadeImpl;

public class QueryEditMuseum {
    private MuseumFacadeImpl museumFacade;
    private MuseumDao memsDao;
    private DialogEditMuseum activity;

    public QueryEditMuseum(DialogEditMuseum fragment) {
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

    public void getQuery(String name, String address, int id) {
        activity.progressBar.setVisibility(View.GONE);
        memsDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        museumFacade = new MuseumFacadeImpl(memsDao, this);
        museumFacade.updateMuseumInfoByAdmin(name, address, id);
    }
}
