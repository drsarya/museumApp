package com.example.museums.view.fragments.user.likedExhibits;

import android.widget.Toast;

import com.example.museums.API.services.impl.LikefacadeImpl;

import java.util.List;

public class QueryLikedExhibitions {
    private LikedExhbtns activity;
    private LikefacadeImpl exhibitFacade;
    private MuseumDao museumDao;

    public QueryLikedExhibitions(LikedExhbtns museumExhibits) {
        this.activity = museumExhibits;
    }

    public void onSuccess(List<ExhibitionWithMuseumName> exhibitModels) {
        activity.refreshAllList(exhibitModels);
    }


    public void onError() {
        Toast.makeText(activity.getContext(),
                "Ошибка получения данных", Toast.LENGTH_SHORT).show();
    }

    public void getQuery(Integer userId) {
        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        exhibitFacade = new LikefacadeImpl(museumDao, this);
        exhibitFacade.getExhibitionsLikedByUser(userId);
    }

}
