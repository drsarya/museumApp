package com.example.museums.view.services.Listeners;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.museums.view.fragments.admin.EditMuseumDialog;
import com.example.museums.view.services.MethodsWithFragment;
import com.example.museums.view.services.recyclerViews.MuseumsRecyclerViewAdapter;

public class ClickListenerHolderMuseum implements View.OnClickListener {
    private MethodsWithFragment mth = new MethodsWithFragment();
    private MuseumsRecyclerViewAdapter.MuseumsViewHolder holder;

    public ClickListenerHolderMuseum(MuseumsRecyclerViewAdapter.MuseumsViewHolder holder) {
        this.holder = holder;
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onClick(View v) {
        DialogFragment myFragment = new EditMuseumDialog();
        AppCompatActivity ss = (AppCompatActivity) v.getContext();
        final FragmentTransaction ft = ss.getSupportFragmentManager().beginTransaction();
        myFragment.show(ft, "dialog");
       // mth.replaceFragment(myFragment, v, (AppCompatActivity) v.getContext());
    }
}