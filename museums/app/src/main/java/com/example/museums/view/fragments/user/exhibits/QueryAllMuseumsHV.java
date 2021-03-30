//package com.example.museums.view.fragments.user.exhibits;
//
//import android.widget.Toast;
//
//import com.example.museums.API.services.impl.ExhibitFacadeImpl;
//import com.example.museums.API.services.impl.MuseumFacadeImpl;
//
//import java.util.List;
//
//public class QueryAllMuseumsHV {
//    private Exhibits activity;
//    private MuseumFacadeImpl museumFacade;
//    private MuseumDao museumDao;
//    private ExhibitFacadeImpl exhibitFacade;
//
//    public QueryAllMuseumsHV(Exhibits museumExhibits) {
//        this.activity = museumExhibits;
//    }
//
//    public void onSuccess(List<Museum> listM) {
//        activity.refreshHorizontalRecView(listM);
//    }
//
//    public void onSuccessGetMuseumExhibits(List<NewExhibitModel> listM) {
//        activity.setMuseumExhibits(listM);
//    }
//
//    public void onError() {
//        Toast.makeText(activity.getContext(),
//                "Ошибка получения данных", Toast.LENGTH_SHORT).show();
//
//    }
//
//    public void getQuery() {
//        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
//        museumFacade = new MuseumFacadeImpl(museumDao, this);
//        museumFacade.getAllMuseums();
//    }
//
//    public void getMuseumExhibits(Integer museumId) {
//        exhibitFacade = new ExhibitFacadeImpl(museumDao, this);
//        exhibitFacade.getExhibitsByMuseumId(museumId);
//    }
//}
