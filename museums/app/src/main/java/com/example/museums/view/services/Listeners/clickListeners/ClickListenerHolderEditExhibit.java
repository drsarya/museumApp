package com.example.museums.view.services.Listeners.clickListeners;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.example.museums.view.activities.tabs.MuseumTab;
import com.example.museums.view.fragments.museum.createExhibition.CreateExhibition;
import com.example.museums.view.fragments.museum.createExhibition.EditExhibit;
import com.example.museums.view.fragments.museum.createExhibition.NewExhibitModel;
import com.example.museums.view.services.MethodsWithFragment;

public class ClickListenerHolderEditExhibit implements View.OnClickListener {
    private CreateExhibition fragment;
    private NewExhibitModel model;
    private int position;

    public ClickListenerHolderEditExhibit(CreateExhibition fragment, NewExhibitModel model, int position) {
        this.fragment = fragment;
        this.model = model;
        this.position = position;
    }

    private MethodsWithFragment mth = new MethodsWithFragment();

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onClick(View v) {
        EditExhibit myFragment = new EditExhibit().newInstance(model.dateOfCreate, model.tags
                , model.author, model.name, model.photo, model.description, position);
        myFragment.setTargetFragment(fragment, 0);
        MuseumTab activity = (MuseumTab) v.getContext();
        mth.replaceFragment(myFragment, v, activity);
    }
}
