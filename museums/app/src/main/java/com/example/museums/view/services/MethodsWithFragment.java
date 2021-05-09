package com.example.museums.view.services;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.museums.R;
import com.example.museums.view.activities.tabs.MuseumTab;
import com.example.museums.view.activities.tabs.UserTab;

public class MethodsWithFragment {

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @SuppressLint("ResourceType")
    public void replaceFragment(Fragment myFragment, AppCompatActivity activity) {
        String backStateName = myFragment.getClass().getName();
        final FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        if (activity.getComponentName().getClassName().equals(UserTab.class.getTypeName())) {
            ft.replace(R.id.container_tab_user, myFragment)
                    .addToBackStack(backStateName)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        } else if (activity.getComponentName().getClassName().equals(MuseumTab.class.getTypeName())) {
            ft.replace(R.id.container_tab_museum, myFragment)
                    .addToBackStack(backStateName)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        }
    }
}
