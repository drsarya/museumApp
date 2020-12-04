package com.example.museums.view.fragments.museum.exhibition.editExhibition;

import android.view.View;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.ExhbtToExhbtnFacadeImpl;
import com.example.museums.API.models.NewExhibitModel;

import java.util.List;

public class QueryGetExhibitsFromExhibition {

    private EditExhibtion activity;

    private ExhbtToExhbtnFacadeImpl exhibitionFacade;
    private MuseumDao museumDao;

    public QueryGetExhibitsFromExhibition(EditExhibtion editExhibtion) {
        this.activity = editExhibtion;
    }

    public void onSuccess(List<NewExhibitModel> list) {
        System.out.println("gettttttttttttExhibittstststtsttstts");
         activity.updateListExhibits(list);
        for (NewExhibitModel a:
                list) {
            System.out.println(a.toString());
        }
        activity.progressBar.setVisibility(View.GONE);

    }


    public void onError() {
        Toast.makeText(activity.getContext(),
                "Ошибка получения данных", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

    }


    public void getQuery(int id) {
        System.out.println(Thread.currentThread()+"кто-то вызывает ");
        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        exhibitionFacade = new ExhbtToExhbtnFacadeImpl(museumDao, this);
        exhibitionFacade.getExhibitsByExhdtnId(Integer.toString(id));
    }
}
