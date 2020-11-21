package com.example.museums.view.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.museums.R;
import com.example.museums.view.fragments.common.Exhibitions;
import com.example.museums.view.fragments.common.Exhibits;
import com.example.museums.view.fragments.user.LikedExhbViewPager;
import com.example.museums.API.models.Exhibit;
import com.example.museums.API.models.Exhibition;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class UserTab extends AppCompatActivity {

    private BottomNavigationView menuTab;
    public static final String LOGIN_KEY = "login_key";
    private String loginUser;
    private boolean currState = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_user);

        Bundle b = getIntent().getExtras();

        if (b != null) {
            loginUser = b.getString(LOGIN_KEY);

        }


        menuTab = (BottomNavigationView) findViewById(R.id.user_btn_nav_menu);
        setInitialPage();
        Bundle bundle = new Bundle();

        menuTab.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_user_exhibits:
                        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                        Exhibits detailedExhibitWithListeners = new Exhibits();

                        bundle.putString(detailedExhibitWithListeners.LOGIN_KEY_USER, loginUser);
                        detailedExhibitWithListeners.setArguments(bundle);


                        ft.replace(R.id.container_tab_user, detailedExhibitWithListeners)
                                .addToBackStack(Exhibits.class.toString())
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                        menuTab.getMenu().findItem(R.id.menu_user_exhibits).setChecked(true);

                        break;
                    case R.id.menu_user_exhibitions:
                        final FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                        Exhibitions exhibitions = new Exhibitions();

                        bundle.putString(exhibitions.LOGIN_KEY_USER, loginUser);
                        exhibitions.setArguments(bundle);

                        ft1.replace(R.id.container_tab_user, exhibitions)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .addToBackStack(Exhibitions.class.toString()).commit();
                        menuTab.getMenu().findItem(R.id.menu_user_exhibitions).setChecked(true);

                        break;
                    case R.id.menu_user_home:
                        final FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                        List<Exhibit> list = new ArrayList<>();
                        List<Exhibition> list2 = new ArrayList<>();
                        LikedExhbViewPager exhibits = new LikedExhbViewPager(list, list2);

                        bundle.putString(exhibits.LOGIN_KEY_USER, loginUser);
                        exhibits.setArguments(bundle);

                        ft2.replace(R.id.container_tab_user, exhibits)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .addToBackStack(LikedExhbViewPager.class.toString()).commit();
                        menuTab.getMenu().findItem(R.id.menu_user_home).setChecked(true);

                        break;

                }
                return false;
            }

        });
    }

    private void setInitialPage() {

        if (!currState) {
            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            currState = true;
            Exhibits detailedExhibitWithListeners = new Exhibits();

            ft.replace(R.id.container_tab_user, detailedExhibitWithListeners).addToBackStack(Exhibits.class.toString())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
            menuTab.getMenu().findItem(R.id.menu_user_exhibits).setChecked(true);

        }


    }
}

