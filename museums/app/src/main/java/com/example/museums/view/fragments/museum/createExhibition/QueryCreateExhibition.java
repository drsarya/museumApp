package com.example.museums.view.fragments.museum.createExhibition;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.ExhbtToExhbtnFacadeImpl;
import com.example.museums.API.interfaces.impl.ExhibitFacadeImpl;
import com.example.museums.API.interfaces.impl.ExhibitionFacadeImpl;
import com.example.museums.API.interfaces.impl.MuseumFacadeImpl;
import com.example.museums.API.models.Exhibit;
import com.example.museums.API.models.ExhibitToExhbtn;
import com.example.museums.API.models.Exhibition;

import java.util.ArrayList;
import java.util.List;

public class QueryCreateExhibition {
    private ExhibitionFacadeImpl exhibitionFacade;
    private MuseumFacadeImpl museumFacade;
    private ExhibitFacadeImpl exhibitFacade;

    private MuseumDao museumDao;
    private CreateExhibition activity;
    private Exhibition exhibition;
    private List<NewExhibitModel> exhibits;
    private Integer exhibitionId;

    public QueryCreateExhibition(CreateExhibition fragment) {
        this.activity = fragment;
    }

    public void onSuccess(int id) {
        exhibition.idMuseum = Integer.toString(id);
        Exhibition exhibition2 = new Exhibition();
        Bitmap bmp2 = exhibition.image.copy(exhibition.image.getConfig(), true);
        exhibition2.image = bmp2;
        exhibition2.name = exhibition.name;
        exhibition2.description = exhibition.description;
        exhibition2.idMuseum = exhibition.idMuseum;
        exhibition2.lastDate = exhibition.lastDate;
        exhibition2.firstDate = exhibition.firstDate;

        exhibitionFacade.insertExhbtn(exhibition2);

    }


    public void onError() {
        Toast.makeText(activity.getContext(),
                "Ошибка получения индекса музея", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

    }

    public void onErrorInsertExhbtn() {
        Toast.makeText(activity.getContext(),
                "Ошибка добавления выставки", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

    }

    public void onSuccessInsertsExhbtToExhbtn(Long[] ids) {

        Toast.makeText(activity.getContext(),
                "Успешное добавление выставки"  , Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);
    }

    public void onErrorInsertsExhbtToExhbtn() {
        Toast.makeText(activity.getContext(),
                "Ошибка получения индекса музея", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);
        //обновить ресайкл вью во фрагменте всех выставок и всех экспонатов
    }

    /*

     *
     *
     * Получаем id музея для доьавления выставки ,
     * добавляем выставку
     * добавлем экспонаты  в бд
     * добавяем в бд модель с id выставки и id экспоната
     *
     * */
    public void onSuccessInsertExhbtn(Long id) {
        exhibitionId = id.intValue();

        exhibitFacade = new ExhibitFacadeImpl(museumDao, this);
        List<Exhibit> list = new ArrayList<>();
        for (NewExhibitModel model : exhibits) {
            Exhibit exhibit = new Exhibit();
            exhibit.authorId = model.idAuthor.intValue();
            exhibit.tags = model.tags;


            Bitmap bmp2 = model.photo.copy(model.photo.getConfig(), true);
            exhibit.photo = bmp2;
            exhibit.dateOfCreate = model.dateOfCreate;
            exhibit.name = model.name;
            exhibit.description = model.description;
            list.add(exhibit);
        }
        exhibitFacade.insertExhibits(list);


    }

    public void onSuccessInsertsExhibits(Long[] ids) {

        List<ExhibitToExhbtn> list = new ArrayList<>();

        for (Long i : ids) {
            ExhibitToExhbtn exhibitToExhbtn = new ExhibitToExhbtn();
            exhibitToExhbtn.idExhibit = i.intValue();
            exhibitToExhbtn.idExhibition = exhibitionId;
            list.add(exhibitToExhbtn);
        }
        ExhbtToExhbtnFacadeImpl exhbtnFacade = new ExhbtToExhbtnFacadeImpl(museumDao, this);
        exhbtnFacade.insertExhbToExbtn(list);
    }

    public void onErrorInsertsExhibits() {
        Toast.makeText(activity.getContext(),
                "Ошибка добавления экспонатов", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

    }

    public void getQuery(String login, Exhibition exhibition, List<NewExhibitModel> exhibits) {
        this.exhibits = exhibits;
        this.exhibition = exhibition;
        activity.progressBar.setVisibility(View.GONE);
        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        exhibitionFacade = new ExhibitionFacadeImpl(museumDao, this);

        museumFacade = new MuseumFacadeImpl(museumDao, this);
        museumFacade.getMuseumIDByLogin(login);


    }


}
