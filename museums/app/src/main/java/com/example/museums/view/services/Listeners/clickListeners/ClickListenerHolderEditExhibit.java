package com.example.museums.view.services.Listeners.clickListeners;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.example.museums.API.models.ExhibitWithAuthor;
import com.example.museums.view.activities.tabs.MuseumTab;
import com.example.museums.view.fragments.museum.createExhibition.CreateExhibition;
import com.example.museums.view.fragments.museum.editExhibit.EditExhibit;
import com.example.museums.view.fragments.museum.createExhibit.NewExhibitModel;
import com.example.museums.view.fragments.museum.museumExhibits.MuseumExhibits;
import com.example.museums.view.services.MethodsWithFragment;

public class ClickListenerHolderEditExhibit implements View.OnClickListener {
    private CreateExhibition fragment;
    private NewExhibitModel model;
    private MuseumExhibits museumExhibits;
    private ExhibitWithAuthor exhibitWithAuthor;
    private int position;

    public ClickListenerHolderEditExhibit(CreateExhibition fragment, NewExhibitModel model, int position) {
        this.fragment = fragment;
        this.model = model;
        this.position = position;
    }

    public ClickListenerHolderEditExhibit(ExhibitWithAuthor model, int position, MuseumExhibits museumExhibits) {
        this.museumExhibits = museumExhibits;
        this.exhibitWithAuthor = model;
        this.position = position;
    }

    private MethodsWithFragment mth = new MethodsWithFragment();

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onClick(View v) {
        if (fragment == null) {
            EditExhibit myFragment = new EditExhibit().newInstance(exhibitWithAuthor.id, exhibitWithAuthor.dateOfCreate, exhibitWithAuthor.tags
                    , exhibitWithAuthor.fullName, exhibitWithAuthor.name, exhibitWithAuthor.photo, exhibitWithAuthor.description, position);

            myFragment.setTargetFragment(museumExhibits, 0);
            MuseumTab activity = (MuseumTab) v.getContext();
            mth.replaceFragment(myFragment, v, activity);
        } else {
            EditExhibit myFragment = new EditExhibit().newInstance( model.dateOfCreate, model.tags
                    , model.author, model.name, model.photo, model.description, position);
            myFragment.setTargetFragment(fragment, 0);
            MuseumTab activity = (MuseumTab) v.getContext();
            mth.replaceFragment(myFragment, v, activity);
        }
    }
}
