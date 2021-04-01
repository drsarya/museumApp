//package com.example.museums.view.fragments.museum.exhibit.editExhibit;
//
//import android.graphics.Bitmap;
//
//import com.example.museums.API.models.exhibit.ExistingExhibit;
//import com.example.museums.API.presenter.specific.BasePresenterImpl;
//import com.example.museums.API.services.repository.ExhibitRepos;
//import com.example.museums.API.services.repository.impl.ExhibitReposImpl;
//
//import java.io.IOException;
//
//public class EditExhibitPresenter   extends BasePresenterImpl {
//    ExhibitRepos exhibitRepos = new ExhibitReposImpl();
//    ExistingExhibit exhibit ;
//    Bitmap bitmap;
//    public void setInfo(ExistingExhibit exhibit, Bitmap bitmap) {
//        this.exhibit = exhibit;
//        this.bitmap = bitmap;
//    }
//
//    @Override
//    public void loadData() throws IOException {
//        exhibitRepos.updateExhibit(exhibit,bitmap, this);
//    }
//}