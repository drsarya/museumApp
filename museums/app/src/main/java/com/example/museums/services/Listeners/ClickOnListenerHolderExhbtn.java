package com.example.museums.services.Listeners;

import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.museums.R;
import com.example.museums.activities.MuseumTab;
import com.example.museums.fragments.DetailedExhbtn;
import com.example.museums.fragments.DetailedExhibit;
import com.example.museums.services.MethodsWithFragment;
import com.example.museums.services.Timers.CountDownTimerHideInfo;

public class ClickOnListenerHolderExhbtn  implements View.OnClickListener {
    private MethodsWithFragment mth = new MethodsWithFragment();

    @Override
    public void onClick(View v) {
             Fragment myFragment = new DetailedExhbtn();
            MuseumTab activity = (MuseumTab) v.getContext();
            mth.replaceFragment(myFragment, v, activity, R.id.container_tab_museum);

    }
}
