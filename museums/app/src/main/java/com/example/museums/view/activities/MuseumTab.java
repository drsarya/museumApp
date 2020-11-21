package com.example.museums.view.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.museums.R;
import com.example.museums.view.fragments.museum.CreateExhibition;
import com.example.museums.view.fragments.common.Exhibitions;
import com.example.museums.view.fragments.common.Exhibits;
import com.example.museums.view.fragments.museum.HomePageMuseum;
import com.example.museums.view.fragments.museum.MainInfoMuseumPageEdit;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MuseumTab extends AppCompatActivity {
    private BottomNavigationView menuTab;
    public static final String LOGIN_KEY = "login_key";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_museum);
        menuTab = (BottomNavigationView) findViewById(R.id.museum_bnview);


        menuTab.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.menu_fr_create_exhbtn:
                    final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                    CreateExhibition detailedExhibitWithListeners = new CreateExhibition();

                    ft.replace(R.id.container_tab_museum, detailedExhibitWithListeners)
                            .addToBackStack(CreateExhibition.class.toString())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                    menuTab.getMenu().findItem(R.id.menu_fr_create_exhbtn).setChecked(true);

                    break;
                case R.id.menu_fr_exhibitions:
                    Exhibitions exhibitions = new Exhibitions();
                    final FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();

                    ft1.replace(R.id.container_tab_museum, exhibitions)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .addToBackStack(Exhibitions.class.toString()).commit();
                    menuTab.getMenu().findItem(R.id.menu_fr_exhibitions).setChecked(true);

                    break;
                case R.id.menu_fr_exhibits:
                    final FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();

                    Exhibits exhibits = new Exhibits();

                    ft2.replace(R.id.container_tab_museum, exhibits)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .addToBackStack(Exhibits.class.toString()).commit();
                    menuTab.getMenu().findItem(R.id.menu_fr_exhibits).setChecked(true);

                    break;
                case R.id.menu_fr_main_museum:
                    final FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();

                    MainInfoMuseumPageEdit mainInfoMuseumPage = new MainInfoMuseumPageEdit();
                    ft3.replace(R.id.container_tab_museum, mainInfoMuseumPage)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .addToBackStack(MainInfoMuseumPageEdit.class.toString()).commit();
                    menuTab.getMenu().findItem(R.id.menu_fr_main_museum).setChecked(true);

                    break;
                case R.id.menu_fr_home:
                    final FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();

                    HomePageMuseum homePage = new HomePageMuseum();
                    ft4.replace(R.id.container_tab_museum, homePage)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .addToBackStack(HomePageMuseum.class.toString()).commit();
                    menuTab.getMenu().findItem(R.id.menu_fr_home).setChecked(true);
                    break;
            }
            return false;
        });
    }

}

