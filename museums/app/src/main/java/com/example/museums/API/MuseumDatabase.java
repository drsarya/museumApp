package com.example.museums.API;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.museums.API.models.*;

@TypeConverters(BitmapConverter.class)
@Database(entities = {Exhibit.class, Exhibition.class, Author.class, ExhibitToExhbtn.class,
        Like.class, Museum.class}, version = 1)
public abstract class MuseumDatabase extends RoomDatabase {
    public abstract MuseumDao museumDao();
}
