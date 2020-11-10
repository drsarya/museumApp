package com.example.services;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.services.models.Exhibit;
import com.example.services.models.ExhibitToExhbtn;
import com.example.services.models.Exhibition;
import com.example.services.models.Like;
import com.example.services.models.Museum;
import com.example.services.models.User;

import java.util.List;


@Dao
public interface MuseumDao {


    @Query("SELECT extn2.id,  extn2.authorId , extn2.name  ,extn2.photoUrl ,extn2.description, extn2.dateOfCreate , extn2.tags FROM exhibit_to_exhbtn AS ex1 \n" +
            "        JOIN exhibit AS extn2 ON extn2.id = ex1.idExhibit WHERE idExhibition = :ixhbtnId")
    List<Exhibit> getExhibitsByExhdtnId(String ixhbtnId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertExhbToExbtn(List<ExhibitToExhbtn> exhbtns);

    @Query("SELECT * FROM exhibit")
    List<Exhibit> getAllExhibits();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertExhbt(List<Exhibit>  exhibit);

    @Query("SELECT * FROM exhibition")
    List<Exhibition> getAllExhibitions();

    @Query("SELECT * FROM  exhibition WHERE idMuseum =:id")
    List<Exhibition> getExhbtnByMuseumId(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertExhbtn(Exhibition exhibition);

    @Query("SELECT Count (*) FROM `like` WHERE idExhb =:exhbtId")
    String getLikesByExhId(String exhbtId);

    @Delete
    int deleteLikesByExhbtId(Like like);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertLike(Like like);

    @Query("SELECT * FROM museum WHERE id =:id")
    Museum getMuseumById(String id);

    @Query("SELECT * FROM museum WHERE login =:login")
    Museum getMuseumByLogin(String login);

    @Update
    int updateMuseumInfo(Museum museum);

    @Query("SELECT * FROM user WHERE login =:login AND password=:password")
    long getUser(String login, String password);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertUser(User user);

}
