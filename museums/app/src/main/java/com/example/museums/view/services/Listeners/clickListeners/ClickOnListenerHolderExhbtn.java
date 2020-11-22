package com.example.museums.view.services.Listeners.clickListeners;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.museums.view.fragments.common.DetailedExhbtn;
import com.example.museums.view.services.MethodsWithFragment;

public class ClickOnListenerHolderExhbtn implements View.OnClickListener {
    private MethodsWithFragment mth = new MethodsWithFragment();


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onClick(View v) {
        Fragment myFragment = new DetailedExhbtn();
        AppCompatActivity ss = (AppCompatActivity) v.getContext();
        mth.replaceFragment(myFragment, v, (AppCompatActivity) v.getContext());
    }
}
