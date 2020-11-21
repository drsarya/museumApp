package com.example.museums.view.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.museums.R;
import com.example.museums.view.fragments.admin.allMuseums.AllMuseums;
import com.example.museums.view.fragments.admin.createMuseum.CreateMuseum;
import com.example.museums.view.fragments.museum.HomePageMuseum;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminTab  extends AppCompatActivity {

    private BottomNavigationView menuTab;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tab_admin);

        menuTab = (BottomNavigationView) findViewById(R.id.admin_tab_btnv);

        menuTab.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.admin_tab_menu_all_museums:
                          FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                        AllMuseums detailedExhibitWithListeners = new AllMuseums();

                        ft.replace(R.id.container_admin, detailedExhibitWithListeners)
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
                    case R.id.admin_tab_menu_home:
                        final FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();

                        HomePageMuseum homePageUser = new HomePageMuseum( );

                        ft3.replace(R.id.container_admin, homePageUser)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .addToBackStack(HomePageMuseum.class.toString()).commit();
                        menuTab.getMenu().findItem(R.id.admin_tab_menu_home).setChecked(true);

                        break;

                }
                return false;
            }

        });

    }
}
