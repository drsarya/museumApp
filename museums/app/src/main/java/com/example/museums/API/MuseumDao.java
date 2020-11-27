package com.example.museums.API;


import android.graphics.Bitmap;

import androidx.constraintlayout.helper.widget.Flow;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.museums.API.models.Author;
import com.example.museums.API.models.Exhibit;
import com.example.museums.API.models.ExhibitToExhbtn;
import com.example.museums.API.models.Exhibition;
import com.example.museums.API.models.Like;
import com.example.museums.API.models.Museum;
import com.example.museums.API.models.MuseumInfoWithoutImage;
import com.example.museums.API.models.User;

import java.util.List;

import io.reactivex.Flowable;

import io.reactivex.Single;


@Dao
public interface MuseumDao {


    @Query("SELECT extn2.id,  extn2.authorId , extn2.name  ,extn2.photo ,extn2.description, extn2.dateOfCreate , extn2.tags FROM exhibit_to_exhbtn AS ex1 \n" +
            "        JOIN exhibit AS extn2 ON extn2.id = ex1.idExhibit WHERE idExhibition = :ixhbtnId")
    List<Exhibit> getExhibitsByExhdtnId(String ixhbtnId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertExhbToExbtn(List<ExhibitToExhbtn> exhbtns);

    @Query("SELECT * FROM exhibit")
    List<Exhibit> getAllExhibits();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertExhbt(List<Exhibit> exhibit);

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


    /*MUSEUM*/
    /*GET*/
    @Query("SELECT * FROM museum WHERE id =:id")
    Single<Museum> getMuseumById(int id);

    @Transaction
    @Query("SELECT id FROM museum WHERE login =:login")
    Single<Integer> getMuseumByLogin(String login);

    @Query("SELECT image FROM museum WHERE login =:login")
    Single<Bitmap> getMuseumImage(String login);


    @Query("SELECT id, name, address, description  FROM museum WHERE login =:login")
    Single<MuseumInfoWithoutImage> getMuseumInfo(String login);

    @Query("SELECT * FROM museum WHERE login =:login AND id = :idCode")
    Single<Museum> getMuseumByLoginAndIdCode(String login, int idCode);

    @Query("SELECT * FROM museum  ")
    Flowable<List<Museum>> getAllMuseums();

    /*INSERT*/
    @Insert(onConflict = OnConflictStrategy.ABORT)
    Single<Long> insertMuseum(Museum museum);

    /*UPDATE*/
    @Query("UPDATE museum SET  address = :address  , name = :name where id= :id ")
    Single<Integer> updateMuseumInfo(String name, String address, int id);

    @Transaction
    @Query("UPDATE museum SET  image = :image    where login= :login ")
    Single<Integer> updateMuseumImage(Bitmap image, String login);

    @Query("UPDATE museum SET  description = :description   where login= :login ")
    Single<Integer> updateMuseumDescription(String description, String login);


    /*USER*/

    /*GET*/
    @Query("SELECT * FROM user where login = :login and type=:type and password = null ")
    Single<User> getUserByLoginAndType(String login, boolean type);

    @Query("SELECT * FROM user WHERE login =:login AND password=:password")
    Single<User> getUser(String login, String password);

    /*UPDATE*/
    @Query("UPDATE user SET  password = :password  where login= :login ")
    Single<Integer> updateUserPassword(String login, String password);

    /*INSERT*/
    @Insert(onConflict = OnConflictStrategy.ABORT)
    Single<Long> insertUser(User user);

    /*AUTHORS*/

    /*GET*/
    @Query("SELECT * FROM author  ")
    Flowable<List<Author>> getAllAuthors();
    /*INSERT*/
    @Insert(onConflict = OnConflictStrategy.ABORT)
    Single<Long> insertAuthor(Author author);

}
