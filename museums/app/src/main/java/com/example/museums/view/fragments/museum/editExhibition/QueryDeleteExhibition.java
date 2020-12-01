package com.example.museums.view.fragments.museum.editExhibition;

import android.view.View;
import android.widget.Toast;

import com.example.museums.API.AppDelegate;
import com.example.museums.API.MuseumDao;
import com.example.museums.API.interfaces.impl.ExhibitFacadeImpl;
import com.example.museums.API.interfaces.impl.ExhibitionFacadeImpl;
import com.example.museums.API.models.ExhibitionWithMuseumName;

import java.util.List;

public class QueryDeleteExhibition {

    private EditExhibition activity;
    private ExhibitFacadeImpl exhibitFacade;

    private ExhibitionFacadeImpl exhibitionFacade;
    private MuseumDao museumDao;

    public QueryDeleteExhibition(EditExhibition editExhibition) {
        this.activity = editExhibition;
    }

    public void onSuccess() {
        exhibitionFacade = new ExhibitionFacadeImpl(museumDao, this);
        exhibitionFacade.deleteExhibition(idExhibition);


    }
    public void onSuccessDeleteExhibition() {

        Toast.makeText(activity.getContext(),
                "Выставка успешно удалена", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

    }


    public void onError() {
        Toast.makeText(activity.getContext(),
                "Ошибка удаления", Toast.LENGTH_SHORT).show();
        activity.progressBar.setVisibility(View.GONE);

    }

    private int idExhibition;


    public void getQuery(int id) {
        museumDao = ((AppDelegate) activity.getActivity().getApplicationContext()).getMuseumDb().museumDao();
        activity.progressBar.setVisibility(View.VISIBLE);
        idExhibition = id;
        exhibitFacade = new ExhibitFacadeImpl(museumDao, this);
        exhibitFacade.deleteExhibits(id);
    }
}
