package com.example.museums.view.activities.tabs;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.museums.R;
import com.example.museums.view.fragments.museum.CreateExhibition;
import com.example.museums.view.fragments.common.Exhibitions;
import com.example.museums.view.fragments.common.Exhibits;
import com.example.museums.view.fragments.museum.MainInfoMuseumEditPage.MainInfoMuseumPageEdit;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MuseumTab extends AppCompatActivity {
    private BottomNavigationView menuTab;
    public static final String LOGIN_KEY = "login_key";
    private boolean currState = false;
    private String login;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();

        if (b != null) {
            login = b.getString(LOGIN_KEY);

        }

        setContentView(R.layout.activity_tab_museum);
        menuTab = (BottomNavigationView) findViewById(R.id.museum_bnview);
        setInitialPage();
        menuTab.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.menu_fr_create_exhbtn:
                    Bundle bundle = new Bundle();

                    CreateExhibition detailedExhibitWithListeners = new CreateExhibition();
                    bundle.putString(CreateExhibition.LOGIN_USER_KEY, login);
                    detailedExhibitWithListeners.setArguments(bundle);
                    final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();


                    ft.replace(R.id.container_tab_museum, detailedExhibitWithListeners)
                            .addToBackStack(CreateExhibition.class.toString())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                    menuTab.getMenu().findItem(R.id.menu_fr_create_exhbtn).setChecked(true);

                    break;
                case R.id.menu_fr_exhibitions:
                    Bundle b1 = new Bundle();

                    Exhibitions exhibitions = new Exhibitions();
                    b1.putString(Exhibitions.LOGIN_KEY_USER, login);
                    exhibitions.setArguments(b1);
                    final FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();

                    ft1.replace(R.id.container_tab_museum, exhibitions)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .addToBackStack(Exhibitions.class.toString()).commit();
                    menuTab.getMenu().findItem(R.id.menu_fr_exhibitions).setChecked(true);

                    break;
                case R.id.menu_fr_exhibits:
                    Bundle b2 = new Bundle();


                    Exhibits exhibits = new Exhibits();
                    b2.putString(Exhibits.LOGIN_KEY_USER, login);
                    exhibits.setArguments(b2);

                    final FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();

                    ft2.replace(R.id.container_tab_museum, exhibits)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .addToBackStack(Exhibits.class.toString()).commit();
                    menuTab.getMenu().findItem(R.id.menu_fr_exhibits).setChecked(true);

                    break;
                case R.id.menu_fr_main_museum:
                    Bundle b3 = new Bundle();

                    MainInfoMuseumPageEdit mainInfoMuseumPage = new MainInfoMuseumPageEdit();
                    b3.putString(MainInfoMuseumPageEdit.LOGIN_KEY_USER, login);
                    mainInfoMuseumPage.setArguments(b3);
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
            Bundle b3 = new Bundle();
            b3.putString(MainInfoMuseumPageEdit.LOGIN_KEY_USER, login);
            MainInfoMuseumPageEdit mainInfoMuseumPage = new MainInfoMuseumPageEdit();
            mainInfoMuseumPage.setArguments(b3);
            ft3.replace(R.id.container_tab_museum, mainInfoMuseumPage)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(MainInfoMuseumPageEdit.class.toString()).commit();
            menuTab.getMenu().findItem(R.id.menu_fr_main_museum).setChecked(true);
        }
    }
}

