//package com.example.museums.view.fragments.museum.exhibit.createExhibit;
//
//import android.graphics.Bitmap;
//
//import com.example.museums.API.models.exhibit.BaseExhibit;
//import com.example.museums.API.presenter.specific.BasePresenterImpl;
//import com.example.museums.API.services.repository.ExhibitRepos;
//import com.example.museums.API.services.repository.impl.ExhibitReposImpl;
//
//import java.io.IOException;
//
//public class CreateExhibitPresenter extends BasePresenterImpl {
//    ExhibitRepos exhibitRepos = new ExhibitReposImpl();
//    BaseExhibit exhibit ;
//    Bitmap bitmap;
//    public void setInfo(BaseExhibit exhibit, Bitmap bitmap) {
//        this.exhibit = exhibit;
//        this.bitmap = bitmap;
//    }
//
//    @Override
//    public void loadData() throws IOException {
//        exhibitRepos.createExhibit(exhibit,bitmap, this);
//    }
//}
