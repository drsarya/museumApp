package com.example.museums.view.services.Listeners.clickListeners;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentTransaction;

import com.example.museums.API.models.Museum;
import com.example.museums.view.fragments.admin.allMuseums.AllMuseums;
import com.example.museums.view.fragments.admin.editMuseum.DialogEditMuseum;
import com.example.museums.view.fragments.museum.createExhibition.NewExhibitModel;
import com.example.museums.view.services.MethodsWithFragment;
import com.example.museums.view.services.recyclerViews.MuseumsRecyclerViewAdapter;
import com.example.museums.view.services.recyclerViews.NewExhibitsRecyclerViewAdapter;

import java.util.List;

public class ClickListenerHolderDeletePosition implements View.OnClickListener {
    private NewExhibitsRecyclerViewAdapter adapter;
    private int position;
private List<NewExhibitModel> newExhibitsModelList;
    public ClickListenerHolderDeletePosition(NewExhibitsRecyclerViewAdapter adapter, int position, List<NewExhibitModel> newExhibitsModelList) {
        this.adapter = adapter;
        this.newExhibitsModelList = newExhibitsModelList;
        this.position = position;
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onClick(View v) {
        adapter.notifyItemRemoved(position);
        adapter.notifyDataSetChanged();

        newExhibitsModelList.remove(position);


    }
}