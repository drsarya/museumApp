package com.example.museums.view.services.Listeners.clickListeners;

import android.os.Build;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.museums.API.models.ExhibitWithAuthor;
import com.example.museums.view.activities.tabs.MuseumTab;
import com.example.museums.view.fragments.common.DetailedExhibitWithListeners;
import com.example.museums.view.fragments.museum.createExhibit.NewExhibitModel;
import com.example.museums.view.services.MethodsWithFragment;
import com.example.museums.view.services.Timers.CountDownTimerHideInfo;
import com.example.museums.view.services.recyclerViews.NewExhibitsRecyclerViewAdapter;

public class ClickListenerHolderNewExhibit implements View.OnClickListener {
    public ClickListenerHolderNewExhibit(View view, ExhibitWithAuthor exhibitWithAuthor) {
        this.view = view;
        this.exhibitWithAuthor = exhibitWithAuthor;
    }
    public ClickListenerHolderNewExhibit(View view, NewExhibitModel newExhibitModel) {
        this.view = view;
        this.newExhibitModel = newExhibitModel;
    }

    private CountDownTimer ctimte = null;
    private View view;
    private   ExhibitWithAuthor exhibitWithAuthor;

    private   NewExhibitModel newExhibitModel;
    private MethodsWithFragment mth = new MethodsWithFragment();

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onClick(View v) {
        if ( view.getVisibility() == View.VISIBLE) {
             Fragment myFragment = new DetailedExhibitWithListeners().newInstance(exhibitWithAuthor.id, exhibitWithAuthor.photo,
                    exhibitWithAuthor.name, exhibitWithAuthor.fullName, exhibitWithAuthor.dateOfCreate,  exhibitWithAuthor.description);
            MuseumTab activity = (MuseumTab) v.getContext();
            mth.replaceFragment(myFragment, v, activity);
        } else {
            ctimte = new CountDownTimerHideInfo(3000, 3000, view);
            ctimte.start();
        }

    }

}