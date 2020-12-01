package com.example.museums.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.museums.API.interfaces.impl.MuseumFacadeImpl;
import com.example.museums.R;
import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.UserFacadeImpl;
import com.example.museums.API.models.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final MuseumDao memsDao = ((AppDelegate) getApplication()).getMuseumDb().museumDao();

        UserFacadeImpl i = new UserFacadeImpl(memsDao);
        i.insertUser("1111", "1111", true);
        i.insertUser("2222", "1111", false);
        i.insertUser("3333", "1111", false);
        i.insertUser("4444", "1111", false);
        i.insertUser("5555", "1111", false);


        MuseumFacadeImpl i1 = new MuseumFacadeImpl(memsDao);
        i1.insertMuseum("2222", "tretizkovskayay", "Russia  Moskow Novgorodskaya 56");
        //final TypeOfExihibit t1 = new TypeOfExihibit();
        i1.insertMuseum("3333", "tretizkovskayay", "Russia  Moskow Novgorodskaya 56");


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