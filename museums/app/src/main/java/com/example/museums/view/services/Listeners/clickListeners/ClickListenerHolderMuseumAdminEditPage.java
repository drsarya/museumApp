package com.example.museums.view.services.Listeners.clickListeners;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentTransaction;

import com.example.museums.API.models.museum.ExistingMuseum;
import com.example.museums.view.fragments.admin.allMuseums.AllMuseums;
import com.example.museums.view.fragments.admin.editMuseum.DialogEditMuseum;
import com.example.museums.view.services.MethodsWithFragment;
import com.example.museums.view.services.recyclerViews.MuseumsRecyclerViewAdapter;


public class ClickListenerHolderMuseumAdminEditPage implements View.OnClickListener {
    private MethodsWithFragment mth = new MethodsWithFragment();
    private MuseumsRecyclerViewAdapter.MuseumsViewHolder holder;
    private ExistingMuseum museum;
    private AllMuseums allMuseums;

    public ClickListenerHolderMuseumAdminEditPage(MuseumsRecyclerViewAdapter.MuseumsViewHolder holder, ExistingMuseum museum, AllMuseums allMuseums) {
        this.holder = holder;
        this.museum = museum;
        this.allMuseums = allMuseums;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onClick(View v) {
        DialogEditMuseum myFragment = new DialogEditMuseum().newInstance(museum.getId(), museum.getName(), museum.getAddress(), museum.getState().name());
        myFragment.show(allMuseums.getChildFragmentManager(), "dialog");
    }
}