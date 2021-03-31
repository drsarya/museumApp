//package com.example.museums.view.fragments.admin.createMuseum;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.FragmentTransaction;
//
//import com.example.museums.API.services.impl.MuseumFacadeImpl;
//import com.example.museums.API.services.impl.UserFacadeImpl;
//
//public class QueryCreateMuseum {
//    private MuseumFacadeImpl museumFacade;
//    private MuseumDao memsDao;
//    private CreateMuseum activity;
//    private String curogin;
//    String login, name, address;
//
//    public QueryCreateMuseum(CreateMuseum fragment) {
//        this.activity = fragment;
//    }
//
//    public void onSuccess(Long idCode) {
//
//        DialogMuseumCreated myFragment = new DialogMuseumCreated();
//        Bundle bd = new Bundle();
//        bd.putString(myFragment.CODE_KEY, idCode.toString());
//        bd.putString(myFragment.LOGIN_KEY, curogin);
//        myFragment.setArguments(bd);
//        AppCompatActivity ss = (AppCompatActivity) activity.getContext();
//        final FragmentTransaction ft = ss.getSupportFragmentManager().beginTransaction();
//        myFragment.show(ft, "dialog");
//        activity.progressBar.setVisibility(View.GONE);
//    }
//
