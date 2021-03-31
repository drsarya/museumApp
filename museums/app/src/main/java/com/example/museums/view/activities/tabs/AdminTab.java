package com.example.museums.view.activities.tabs;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.museums.R;
import com.example.museums.view.fragments.admin.allMuseums.AllMuseums;
import com.example.museums.view.fragments.admin.createMuseum.CreateMuseum;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminTab extends AppCompatActivity {


    private BottomNavigationView menuTab;
    private boolean currState = false;
    private AllMuseums allMuseums  ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_admin);
        menuTab = (BottomNavigationView) findViewById(R.id.admin_tab_btnv);
        allMuseums = new AllMuseums();
        setInitialPage();
        menuTab.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.admin_tab_menu_all_museums:
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                    ft.replace(R.id.container_admin, allMuseums  )
                            .addToBackStack(AllMuseums.class.toString())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                    menuTab.getMenu().findItem(R.id.admin_tab_menu_all_museums).setChecked(true);
                    break;
                case R.id.admin_tab_menu_create:
                    FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                    CreateMuseum createMuseum = new CreateMuseum();
                    ft2.replace(R.id.container_admin, createMuseum)
                            .addToBackStack(CreateMuseum.class.toString())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                    menuTab.getMenu().findItem(R.id.admin_tab_menu_create).setChecked(true);
                    break;
            }
            return false;
        });

    }

    private void setInitialPage() {
        if (!currState) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container_admin, allMuseums)
                    .addToBackStack(AllMuseums.class.toString())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
            menuTab.getMenu().findItem(R.id.admin_tab_menu_all_museums).setChecked(true);
        }
    }
}
