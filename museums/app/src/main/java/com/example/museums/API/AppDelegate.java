package com.example.museums.API;

import android.app.Application;

import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;


public class AppDelegate extends Application {
    private MuseumDatabase museumDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        museumDatabase =
                Room.databaseBuilder(getApplicationContext(), MuseumDatabase.class, "museum_database")

                        .build();
    }

    public MuseumDatabase getMuseumDb() {
        return museumDatabase;
    }

}
