//package com.example.museums.view.fragments.museum.museumExhibits;
//
//import com.example.museums.API.RetrofitConnect;
//import com.example.museums.API.services.api.ExhibitService;
//
//public class MuseumExhibitsPresenter {
//
//
//    ExhibitService exhibitService = RetrofitConnect.createRetrofitConnection(ExhibitService.class);
//
//    public MuseumExhibitsPresenter() {
//        exhibitService = RetrofitConnect.createRetrofitConnection(ExhibitService.class);
//    }
//
//    public void getAllExhibits(Integer museumID) {
//        exhibitService.getExhibitsByMuseumId(museumID);
//
//    }
//
//    public void deleteExhibit(Integer exhibitID) {
//        exhibitService.deleteExhibit(exhibitID);
//    }
//
//
//}