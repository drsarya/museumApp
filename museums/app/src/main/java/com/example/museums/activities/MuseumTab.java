package com.example.museums.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.museums.R;
import com.example.museums.fragments.DetailedExhibit;
import com.example.museums.fragments.museum.CreateExhibition;
import com.example.museums.fragments.museum.Exhibitions;
import com.example.museums.fragments.museum.Exhibits;
import com.example.museums.fragments.museum.MainInfoMuseumPage;
import com.example.services.models.Exhibit;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MuseumTab extends AppCompatActivity {
    private BottomNavigationView munuTab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_museum);
        munuTab = (BottomNavigationView) findViewById(R.id.museum_bnview);


        munuTab.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_fr_create_exhbtn:
                        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                        DetailedExhibit detailedExhibit = new DetailedExhibit();

                        ft.replace(R.id.container, detailedExhibit)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                        munuTab.getMenu().findItem(R.id.menu_fr_create_exhbtn).setChecked(true);

                        break;
                    case R.id.menu_fr_exhibitions:
                        final FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();

                        Exhibitions exhibitions = new Exhibitions();
                        ft1.replace(R.id.container, exhibitions)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                        munuTab.getMenu().findItem(R.id.menu_fr_exhibitions).setChecked(true);

                        break;
                    case R.id.menu_fr_exhibits:
                        final FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();

                        Exhibits exhibits = new Exhibits();
                        ft2.replace(R.id.container, exhibits)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                        munuTab.getMenu().findItem(R.id.menu_fr_exhibits).setChecked(true);

                        break;
                    case R.id.menu_fr_main_museum:
                        final FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();

                        MainInfoMuseumPage mainInfoMuseumPage = new MainInfoMuseumPage();
                        ft3.replace(R.id.container, mainInfoMuseumPage)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                        munuTab.getMenu().findItem(R.id.menu_fr_main_museum).setChecked(true);


                        break;
                }
                return false;
            }

        });
    }

}

