//package com.example.museums.view.fragments.museum.museumExhibits;
//
//import android.widget.Toast;
//
//import androidx.fragment.app.Fragment;
//
//import com.example.museums.API.services.impl.ExhibitFacadeImpl;
//
//
//public class QueryDeleteMuseumExhibit {
//    private Fragment activity;
//    private ExhibitFacadeImpl exhibitFacadel;
//    private MuseumDao museumDao;
//
//    public QueryDeleteMuseumExhibit(Fragment museumExhibits) {
//        this.activity = museumExhibits;
//    }
//
//    public void onSuccess() {
//        Toast.makeText(activity.getContext(),
//                "Успешное удаление", Toast.LENGTH_SHORT).show();
//    }
//
//
//    public void onError() {
//        Toast.makeText(activity.getContext(),
//                "Ошибка удаления", Toast.LENGTH_SHORT).show();
//    }
//
//    public void getQuery(int id) {
//        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
//        exhibitFacadel = new ExhibitFacadeImpl(museumDao, this);
//        exhibitFacadel.deleteExhibit(id);
//    }
//
//}
