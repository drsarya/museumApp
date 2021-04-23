package com.example.museums.view.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;


import com.example.museums.R;


public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final MuseumDao memsDao = ((AppDelegate) getApplication()).getMuseumDb().museumDao();
//
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                memsDao.deleee();
//            }
//        });
//        try {
//            final MuseumDao memsDao = ((AppDelegate) getApplication()).getMuseumDb().museumDao();
//
//            User u = new User();
//            u.type =true;
//            u.login = "1111";
//            String pass = "1111";
//            String sssss =   ConfigEncrypt.getSaltedHash(pass);
//            UserFacadeImpl us = new UserFacadeImpl(memsDao);
//            us.insertUser("1111", sssss, true);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        //        for (int i = 0; i < 3 ; i++) {
//            final User u1 = new User();
//            u1.login = "1111";
//            u1.password = Integer.toString(i);
//            AsyncTask.execute(new Runnable() {
//                @Override
//                public void run() {
//                    memsDao.insertUser(u1);
//                }
//            });
//        }
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                List<User> users = memsDao.getUsers();
//                for (int i = 0; i < users.size(); i++) {
//                    System.out.println(users.get(i).id + " " + users.get(i).login + " " + users.get(i).password);
//                }
//            }
//        });


    }
}