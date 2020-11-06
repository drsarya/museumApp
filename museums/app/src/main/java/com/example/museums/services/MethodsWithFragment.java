package com.example.museums.services;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MethodsWithFragment {

    public MethodsWithFragment() {
    }

    public void replaceFragment(Fragment myFragment , View v, AppCompatActivity activity, Integer idContainer) {

         String backStateName = myFragment.getClass().getName();
        final FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.replace(idContainer, myFragment)
                .addToBackStack(backStateName)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }
}
