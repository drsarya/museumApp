package com.example.museums.services;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.museums.R;


public class MethodsWithFragment {

    public MethodsWithFragment() {
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @SuppressLint("ResourceType")
    public void replaceFragment(Fragment myFragment, View v, AppCompatActivity activity) {

        String backStateName = myFragment.getClass().getName();
        final FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        activity.getComponentName().getShortClassName();
        if (activity.getComponentName().getShortClassName().equals(".activities.UserTab")) {
            ft.replace(R.id.container_tab_user, myFragment)
                    .addToBackStack(backStateName)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        } else {
            ft.replace(R.id.container_tab_museum, myFragment)
                    .addToBackStack(backStateName)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();

        }

    }
}
