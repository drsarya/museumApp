package com.example.museums.view.activities.tabs;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.museums.R;
import com.example.museums.view.fragments.museum.exhibition.createExhibition.CreateExhibition;
import com.example.museums.view.fragments.common.Exhibitions;
import com.example.museums.view.fragments.common.Exhibits;
import com.example.museums.view.fragments.museum.museumExhibitions.MuseumExhibitions;
import com.example.museums.view.fragments.museum.mainInfoMuseumEditPage.MainInfoMuseumPageEdit.MainInfoMuseumPageEdit;
import com.example.museums.view.fragments.museum.museumExhibits.MuseumExhibits;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MuseumTab extends AppCompatActivity {
    private BottomNavigationView menuTab;
    public static final String LOGIN_KEY_USER = "login_key";
    private boolean currState = false;
    private String login;
    private CreateExhibition createExhibition;
    private MuseumExhibits exhibits;
    private MainInfoMuseumPageEdit mainInfoMuseumPage;
    private MuseumExhibitions exhibitions;


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();

        if (b != null) {
            login = b.getString(LOGIN_KEY_USER);
            exhibitions = new MuseumExhibitions().newInstance(login);

            mainInfoMuseumPage = new MainInfoMuseumPageEdit().newInstance(login);
            exhibits = new MuseumExhibits().newInstance(login);
            createExhibition = new CreateExhibition().newInstance(login);

        } else {
            exhibitions = new MuseumExhibitions();
            mainInfoMuseumPage = new MainInfoMuseumPageEdit();
            exhibits = new MuseumExhibits();
            createExhibition = new CreateExhibition();
        }


        setContentView(R.layout.activity_tab_museum);
        menuTab = (BottomNavigationView) findViewById(R.id.museum_bnview);
        setInitialPage();
        menuTab.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.menu_fr_create_exhbtn:
                    final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.container_tab_museum, createExhibition)
                            .addToBackStack(CreateExhibition.class.toString())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                    menuTab.getMenu().findItem(R.id.menu_fr_create_exhbtn).setChecked(true);

                    break;
                case R.id.menu_fr_exhibitions:
                    final FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                    ft1.replace(R.id.container_tab_museum, exhibitions)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .addToBackStack(Exhibitions.class.toString()).commit();
                    menuTab.getMenu().findItem(R.id.menu_fr_exhibitions).setChecked(true);

                    break;
                case R.id.menu_fr_exhibits:
                    final FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                    ft2.replace(R.id.container_tab_museum, exhibits)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .addToBackStack(Exhibits.class.toString()).commit();
                    menuTab.getMenu().findItem(R.id.menu_fr_exhibits).setChecked(true);

                    break;
                case R.id.menu_fr_main_museum:
                    final FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                    ft3.replace(R.id.container_tab_museum, mainInfoMuseumPage)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .addToBackStack(MainInfoMuseumPageEdit.class.toString()).commit();
                    menuTab.getMenu().findItem(R.id.menu_fr_main_museum).setChecked(true);
                    break;
            }
            return false;
        });
    }


    private void setInitialPage() {
        if (!currState) {
            currState = true;
            final FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
            ft3.replace(R.id.container_tab_museum, mainInfoMuseumPage)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(MainInfoMuseumPageEdit.class.toString()).commit();
            menuTab.getMenu().findItem(R.id.menu_fr_main_museum).setChecked(true);
        }
    }
}

