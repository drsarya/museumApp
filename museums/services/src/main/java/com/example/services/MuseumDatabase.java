package com.example.services;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.services.models.*;

@Database(entities = { User.class, Exhibit.class, Exhibition.class, Author.class, ExhibitToExhbtn.class,
IdentificationCode.class, Like.class, Museum.class}, version = 1)
public abstract class MuseumDatabase extends RoomDatabase {
    public abstract MuseumDao museumDao();
}
