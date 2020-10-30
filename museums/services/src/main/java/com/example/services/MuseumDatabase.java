package com.example.services;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.services.models.*;

@Database(entities = {TypeOfExihibit.class, User.class}, version = 1)
public abstract class MuseumDatabase extends RoomDatabase {
    public abstract MuseumDao museumDao();
}
