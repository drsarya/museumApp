package com.example.services;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.services.models.TypeOfExihibit;
import com.example.services.models.User;

import java.util.List;


@Dao
public interface MuseumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTypeOfExhibit(TypeOfExihibit type);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User ui);

    @Query("SELECT * FROM user")
    List<User> getUsers();
}
