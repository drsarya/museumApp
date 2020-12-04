package com.example.museums.API;


import android.graphics.Bitmap;

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
import com.example.museums.API.models.ExhibitionWithMuseumName;
import com.example.museums.API.models.Like;
import com.example.museums.API.models.Museum;
import com.example.museums.API.models.MuseumInfoWithoutImage;
import com.example.museums.API.models.User;
import com.example.museums.API.models.NewExhibitModel;

import java.util.List;

import io.reactivex.Flowable;

import io.reactivex.Single;


@Dao
public interface MuseumDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long[]> insertExhbToExbtn(List<ExhibitToExhbtn> exhbtns);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long > insertExhbToExbtn( ExhibitToExhbtn  exhbtns);

    @Query("SELECT * FROM exhibit as e1 JOIN author as a1 ON a1.id_author = e1.authorId ")
    Flowable<List<NewExhibitModel>> getAllExhibits();


    @Query("SELECT e1.id,e1.name, e1.idMuseum, e1.image, e1.description, e1.firstDate, e1.lastDate, m2.nameMuseum  FROM  exhibition as e1 JOIN museum as m2 on  m2.id = e1.idMuseum  ")
    Flowable<List<ExhibitionWithMuseumName>> getAllExhibitions();


    @Query("SELECT Count (*) FROM `like` WHERE idExhb =:exhbtId")
    String getLikesByExhId(String exhbtId);

    @Delete
    int deleteLikesByExhbtId(Like like);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertLike(Like like);


    /*EXHIBITION*/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Single<Long> insertExhbtn(Exhibition exhibition);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    Single<Integer> updateExhibition(Exhibition exhibit);

    @Query("SELECT e1.id,e1.name, e1.idMuseum, e1.image, e1.description, e1.firstDate, e1.lastDate, m2.nameMuseum  FROM  exhibition as e1 JOIN museum as m2 on  m2.id = e1.idMuseum WHERE e1.idMuseum =:id")
    Flowable<List<ExhibitionWithMuseumName>> getExhbtnByMuseumId(Integer id);


    /*DELETE*/

    @Query("DELETE FROM exhibition   WHERE id = :id")
    Single<Integer> deleteExhibition(int id);



    /*EXHIBIT*/
    /*GET*/


    @Query("SELECT extn2.id,  extn2.authorId , extn2.name ,a1.fullName,extn2.photo ,extn2.description, extn2.dateOfCreate , extn2.tags FROM exhibit_to_exhbtn AS ex1 \n" +
            "        JOIN exhibit AS extn2 ON extn2.id = ex1.idExhibit  JOIN author as a1 on  extn2.authorId = a1.id_author WHERE idExhibition = :exhbtnId")
    Flowable<List<NewExhibitModel>> getExhibitsByExhibitionId(String exhbtnId);

    @Query("delete from exhibit where id in  (select idExhibit from exhibit_to_exhbtn  " +
            "   where idExhibition =:idExhibitiob )")
    Single<Integer> deleteExhibits(int idExhibitiob);



    @Query("SELECT  * FROM" +

            "  museum AS m1 JOIN exhibition as ex1 ON ex1.idMuseum = m1.id JOIN exhibit_to_exhbtn AS ex2 ON ex2.idExhibition = ex1.id JOIN exhibit as ex3 On ex3.id = ex2.idExhibit " +
            "JOIN author as a1 ON a1.id_author = ex3.authorId" +
            " WHERE  m1.login = :login   ")
    Flowable<List<NewExhibitModel>> getExhibitsByMuseumId(String login);

    /*DELETE*/
    @Query("DELETE FROM exhibit WHERE id = :id")
    Single<Integer> deleteExhibit(int id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    Single<Integer> updateExhibitInfo(Exhibit exhibit);


    /*INSERT*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long> insertExhibit( Exhibit  exhibit);

    /*MUSEUM*/
    /*GET*/
    @Query("SELECT * FROM museum WHERE id =:id")
    Single<Museum> getMuseumById(int id);

    @Transaction
    @Query("SELECT id FROM museum WHERE login =:login")
    Single<Integer> getMuseumByLogin(String login);

    @Query("SELECT image FROM museum WHERE login =:login")
    Single<Bitmap> getMuseumImage(String login);


    @Query("SELECT id, nameMuseum, address, description  FROM museum WHERE login =:login")
    Single<MuseumInfoWithoutImage> getMuseumInfo(String login);

    @Query("SELECT * FROM museum WHERE login =:login AND id = :idCode")
    Single<Museum> getMuseumByLoginAndIdCode(String login, int idCode);

    @Query("SELECT * FROM museum  ")
    Flowable<List<Museum>> getAllMuseums();

    /*INSERT*/
    @Insert(onConflict = OnConflictStrategy.ABORT)
    Single<Long> insertMuseum(Museum museum);

    /*UPDATE*/
    @Query("UPDATE museum SET  address = :address  , nameMuseum = :name where id= :id ")
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


    @Query("SELECT id_author FROM author  where fullName = :fullname ")
    Single<Integer> getAllAuthorByName(String fullname);

    /*INSERT*/
    @Insert(onConflict = OnConflictStrategy.ABORT)
    Single<Long> insertAuthor(Author author);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    Single<List<Long>> insertAuthors(List<Author> author);
}
