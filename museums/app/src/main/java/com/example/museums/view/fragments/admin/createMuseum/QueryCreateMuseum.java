package com.example.museums.view.fragments.admin.createMuseum;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.MuseumFacadeImpl;

public class QueryCreateMuseum {
    private MuseumFacadeImpl museumFacade;
    private MuseumDao memsDao;
    private CreateMuseum activity;

    public QueryCreateMuseum(CreateMuseum fragment) {
        this.activity = fragment;
    }

    public void onSuccess(Long idCode) {

        DialogMuseumCreated myFragment = new DialogMuseumCreated();
        Bundle bd = new Bundle();
        bd.putString(myFragment.CODE_KEY, idCode.toString());
        bd.putString(myFragment.LOGIN_KEY, curogin);

        myFragment.setArguments(bd);
        AppCompatActivity ss = (AppCompatActivity) activity.getContext();
        final FragmentTransaction ft = ss.getSupportFragmentManager().beginTransaction();
        myFragment.show(ft, "dialog");
        activity.progressBar.setVisibility(View.GONE);
    }

    public void onError() {
        Toast.makeText(activity.getContext(),
                "Ошибка создания", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

    }

    private String curogin;

    public void getQuery(String login, String name, String address) {
        activity.progressBar.setVisibility(View.GONE);
        curogin = login;
        memsDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        museumFacade = new MuseumFacadeImpl(memsDao, this);
        museumFacade.insertMuseum(login, name, address);

    }
}