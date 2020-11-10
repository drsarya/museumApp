package com.example.museums.services.Listeners;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.museums.fragments.DetailedExhbtn;
import com.example.museums.services.MethodsWithFragment;

public class ClickOnListenerHolderExhbtn implements View.OnClickListener {
    private MethodsWithFragment mth = new MethodsWithFragment();


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onClick(View v) {
        Fragment myFragment = new DetailedExhbtn();
        AppCompatActivity ss = (AppCompatActivity) v.getContext();
        System.out.println(ss);
        mth.replaceFragment(myFragment, v, (AppCompatActivity) v.getContext());
        System.out.println(ss);
    }
}
