package com.example.museums.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.museums.R;
import com.example.services.AppDelegate;
import com.example.services.MuseumDao;
import com.example.services.interfaces.impl.UserFacadeImpl;
import com.example.services.models.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final MuseumDao memsDao = ((AppDelegate) getApplication()).getMuseumDb().museumDao();
        final User u1 = new User();
            u1.login = "1111";
            u1.password = "1111";

        UserFacadeImpl i = new UserFacadeImpl(memsDao, getApplicationContext());
        i.insertUser("1111", "1111");
        i.insertUser("2222", "1111");
        i.insertUser("3333", "1111");
        i.insertUser("4444", "1111");
        i.insertUser("5555", "1111");
        //final TypeOfExihibit t1 = new TypeOfExihibit();


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