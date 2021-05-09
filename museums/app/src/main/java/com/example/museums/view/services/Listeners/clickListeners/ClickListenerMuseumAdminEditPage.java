package com.example.museums.view.services.Listeners.clickListeners;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.example.museums.API.models.museum.ExistingMuseum;
import com.example.museums.view.fragments.admin.allMuseums.AllMuseums;
import com.example.museums.view.fragments.admin.editMuseum.DialogEditMuseum;


public class ClickListenerMuseumAdminEditPage implements View.OnClickListener {
    private ExistingMuseum museum;
    private AllMuseums allMuseums;

    public ClickListenerMuseumAdminEditPage(ExistingMuseum museum, AllMuseums allMuseums) {
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