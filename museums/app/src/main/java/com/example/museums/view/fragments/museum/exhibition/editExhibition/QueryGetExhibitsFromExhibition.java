package com.example.museums.view.fragments.museum.exhibition.editExhibition;

import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.ExhbtToExhbtnFacadeImpl;
import com.example.museums.API.models.NewExhibitModel;
import com.example.museums.view.services.oop.IUpdateList;

import java.util.List;

public class QueryGetExhibitsFromExhibition {

    private Fragment activity;
    private ExhbtToExhbtnFacadeImpl exhibitionFacade;
    private MuseumDao museumDao;
    private IUpdateList updateList;

    public QueryGetExhibitsFromExhibition(Fragment editExhibtion, IUpdateList updateList) {
        this.activity = editExhibtion;
        this.updateList = updateList;
    }

    public void onSuccess(List<NewExhibitModel> list) {
        updateList.updateList(list);
    }


    public void onError() {
        Toast.makeText(activity.getContext(),
                "Ошибка получения данных", Toast.LENGTH_SHORT).show();

    }


    public void getQuery(int id) {
        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        exhibitionFacade = new ExhbtToExhbtnFacadeImpl(museumDao, this);
        exhibitionFacade.getExhibitsByExhdtnId(Integer.toString(id));
    }
}
