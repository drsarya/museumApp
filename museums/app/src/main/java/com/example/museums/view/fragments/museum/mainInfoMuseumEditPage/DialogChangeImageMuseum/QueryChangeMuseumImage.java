package com.example.museums.view.fragments.museum.mainInfoMuseumEditPage.DialogChangeImageMuseum;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.MuseumFacadeImpl;
import com.example.museums.R;

public class QueryChangeMuseumImage {

    private MuseumFacadeImpl museumFacade;
    private MuseumDao museumDao;
    private DialogChangeMuseumPhoto activity;
    private Bitmap bitmap;

    public QueryChangeMuseumImage(DialogChangeMuseumPhoto fragment) {
        this.activity = fragment;
    }

    public void onSuccess() {
        ImageView in = (ImageView) activity.getActivity().findViewById(R.id.main_info_museum_edit_image_view);

        in.setImageBitmap(bitmap);

        Toast.makeText(activity.getContext(),
                "Успешное обновление", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);
    }


    public void onError() {
        Toast.makeText(activity.getContext(),
                "Ошибка обновления", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

    }

    public void getQuery(String login, Bitmap image) {
        bitmap = image.copy(image.getConfig(), image.isMutable());
        activity.progressBar.setVisibility(View.GONE);
        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        museumFacade = new MuseumFacadeImpl(museumDao, this);
        museumFacade.updateMuseumImage(login, image);
    }
}
