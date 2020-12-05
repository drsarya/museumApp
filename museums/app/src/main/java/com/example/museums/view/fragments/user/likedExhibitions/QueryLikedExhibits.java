package com.example.museums.view.fragments.user.likedExhibitions;

import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.LikefacadeImpl;
import com.example.museums.API.models.NewExhibitModel;
import com.example.museums.view.fragments.user.likedExhibits.LikedExhbtns;

import java.util.List;

public class QueryLikedExhibits {
    private LikedExhibits activity;
    private LikefacadeImpl exhibitFacade;
    private MuseumDao museumDao;

    public QueryLikedExhibits(LikedExhibits museumExhibits) {
        this.activity = museumExhibits;
    }

    public void onSuccess(List<NewExhibitModel> exhibitModels) {
        System.out.println(exhibitModels.size());
        activity.refreshAllList(exhibitModels);
    }


    public void onError() {
        Toast.makeText(activity.getContext(),
                "Ошибка получения данных", Toast.LENGTH_SHORT).show();
    }

    public void getQuery(Integer userId) {
        System.out.println(userId+"iduser");
        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        exhibitFacade = new LikefacadeImpl(museumDao, this);
        exhibitFacade.getExhibitsLikedByUser(userId);

    }
}
