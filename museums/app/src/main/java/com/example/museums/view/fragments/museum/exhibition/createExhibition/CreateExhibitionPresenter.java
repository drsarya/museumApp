//package com.example.museums.view.fragments.museum.exhibition.createExhibition;
//
//import android.graphics.Bitmap;
//
//import com.example.museums.API.models.exhibition.BaseExhibition;
//import com.example.museums.API.presenter.specific.BasePresenterImpl;
//import com.example.museums.API.services.repository.ExhibitionRepos;
//import com.example.museums.API.services.repository.impl.ExhibitionReposImpl;
//
//import java.io.IOException;
//
//public class CreateExhibitionPresenter extends BasePresenterImpl {
//    ExhibitionRepos exhibitRepos = new ExhibitionReposImpl();
//    BaseExhibition exhibit ;
//    Bitmap bitmap;
//    public void setInfo(BaseExhibition exhibit, Bitmap bitmap) {
//        this.exhibit = exhibit;
//        this.bitmap = bitmap;
//    }
//
//    @Override
//    public void loadData() throws IOException {
//        exhibitRepos.createExhibition(exhibit,bitmap, this);
//    }
//}
