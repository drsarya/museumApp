package com.example.museums.services.Listeners;

import android.os.CountDownTimer;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.museums.R;
import com.example.museums.activities.MuseumTab;
import com.example.museums.fragments.DetailedExhibit;
import com.example.museums.services.MethodsWithFragment;
import com.example.museums.services.Timers.CountDownTimerHideInfo;
import com.example.museums.services.recyclerViews.ExhibitsRecyclerViewAdapter;

public class ClickListenerHolderExhibitis implements View.OnClickListener {
    public ClickListenerHolderExhibitis(ExhibitsRecyclerViewAdapter.ExhibitsViewHolder holder) {
        this.holder = holder;
    }

    private CountDownTimer ctimte = null;
    private MethodsWithFragment mth = new MethodsWithFragment();
    private ExhibitsRecyclerViewAdapter.ExhibitsViewHolder holder;

    @Override
    public void onClick(View v) {
        if (holder.textView.getVisibility() == View.VISIBLE) {
            Fragment myFragment = new DetailedExhibit();
            MuseumTab activity = (MuseumTab) v.getContext();
            mth.replaceFragment(myFragment, v, activity, R.id.container_tab_museum);
        } else {
            ctimte = new CountDownTimerHideInfo(3000, 3000, holder);
            ctimte.start();
        }
    }
}
