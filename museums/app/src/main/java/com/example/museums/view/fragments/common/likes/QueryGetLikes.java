//package com.example.museums.view.fragments.common.likes;
//
//import android.widget.Toast;
//
//import androidx.fragment.app.Fragment;
//
//import com.example.museums.API.services.impl.LikefacadeImpl;
//import com.example.museums.view.services.oop.ILike;
//
//public class QueryGetLikes {
//    private ILike activity;
//    private Fragment fragment;
//    private LikefacadeImpl likefacade;
//    private MuseumDao museumDao;
//
//    public QueryGetLikes(ILike activity, Fragment fragment) {
//        this.activity = activity;
//        this.fragment = fragment;
//    }
//
//    public void onSuccess(String exhibitModels) {
//        activity.setCountLikesTextView(exhibitModels);
//    }
//
//    public void onError() {
//        Toast.makeText(fragment.getContext(),
//                "Ошибка получения данных", Toast.LENGTH_SHORT).show();
//    }
//
//    public void getCountLikes() {
//        museumDao = ((AppDelegate) fragment.getActivity().getApplicationContext()).getMuseumDb().museumDao();
//        likefacade = new LikefacadeImpl(museumDao, this);
//        likefacade.getLikesByExhId(idExhibit.toString(), type);
//    }
//
//    public void deleteLike() {
//        likefacade.deleteLikesByExhbtId(idUser, idExhibit.toString(), type);
//    }
//
//    public Integer idUser;
//    public Integer idExhibit;
//    public boolean type;
//
//    public void getState() {
//        museumDao = ((AppDelegate) fragment.getActivity().getApplicationContext()).getMuseumDb().museumDao();
//        likefacade = new LikefacadeImpl(museumDao, this);
//        likefacade.getLikeByUserId(idUser, idExhibit.toString(), type);
//    }
//
//    public void getInsertLike() {
//        museumDao = ((AppDelegate) fragment.getActivity().getApplicationContext()).getMuseumDb().museumDao();
//        likefacade = new LikefacadeImpl(museumDao, this);
//        likefacade.insertLike(idUser, idExhibit.toString(), type);
//    }
//
//    public void setLike() {
//        activity.setLiked();
//    }
//
//    public void setDontLike() {
//        activity.setDontLiked();
//    }
//
//    //true - экпонат
//    //false- выставка
//    public void setData(Integer userId, Integer idExhibit, boolean type) {
//        this.idUser = userId;
//        this.idExhibit = idExhibit;
//        this.type = type;
//    }
//
//    public void setData(Integer idExhibit, boolean type) {
//        this.type = type;
//        this.idExhibit = idExhibit;
//    }
//}
