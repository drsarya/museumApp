package com.example.museums.view.services.Listeners.clickListeners;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.view.activities.tabs.MuseumTab;
import com.example.museums.view.fragments.museum.exhibit.editExhibit.EditExhibit;
 import com.example.museums.view.fragments.museum.museumExhibits.MuseumExhibits;
import com.example.museums.view.services.MethodsWithFragment;

public class ClickListenerHolderEditExhibit implements View.OnClickListener {
    private Fragment fragment;
    private ExistingExhibit model;
    private int position;

    public ClickListenerHolderEditExhibit(Fragment fragment, ExistingExhibit model, int position) {
        this.fragment = fragment;
        this.model = model;
        this.position = position;
    }


    private MethodsWithFragment mth = new MethodsWithFragment();

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onClick(View v) {

        EditExhibit myFragment = new EditExhibit().newInstance(model.getId(), model.getDateOfCreate()
                , model.getAuthor().fullName, model.getName(), model.getImageUrl(), model.getDescription(), position);
        if (fragment.getClass().toString().equals(MuseumExhibits.class.toString())) {
            myFragment.setTargetFragment((MuseumExhibits) fragment, 0);
        } else {
           // myFragment.setTargetFragment((EditExhibition) fragment, 0);
        }
        MuseumTab activity = (MuseumTab) v.getContext();
        mth.replaceFragment(myFragment, activity);


    }
}
