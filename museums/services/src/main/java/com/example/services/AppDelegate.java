package com.example.services;

import android.app.Application;

import androidx.room.Room;


public class AppDelegate extends Application {
    private MuseumDatabase museumDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        museumDatabase =
                Room.databaseBuilder(getApplicationContext(), MuseumDatabase.class, "museum_database")
                        .build();
    }

    public   MuseumDatabase getMuseumDb() {
        return museumDatabase;
    }

}
