package com.example.museums.services;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.museums.R;
import com.example.museums.activities.MuseumTab;
import com.example.museums.fragments.DetailedExhibit;

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
