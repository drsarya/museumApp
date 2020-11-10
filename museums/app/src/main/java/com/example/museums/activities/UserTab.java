package com.example.museums.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.museums.R;
import com.example.museums.fragments.museum.Exhibitions;
import com.example.museums.fragments.museum.Exhibits;
import com.example.museums.fragments.user.LikedExhbViewPager;
import com.example.services.models.Exhibit;
import com.example.services.models.Exhibition;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class UserTab extends AppCompatActivity {

    private BottomNavigationView menuTab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_user);

        menuTab = (BottomNavigationView) findViewById(R.id.user_btn_nav_menu);


        menuTab.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_user_exhibits:
                        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                        Exhibits detailedExhibitWithListeners = new Exhibits();

                        ft.replace(R.id.container_tab_user, detailedExhibitWithListeners).addToBackStack(Exhibits.class.toString())
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                        menuTab.getMenu().findItem(R.id.menu_user_exhibits).setChecked(true);

                        break;
                    case R.id.menu_user_exhibitions:
                        final FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                        Exhibitions exhibitions = new Exhibitions();

                        ft1.replace(R.id.container_tab_user, exhibitions)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(Exhibitions.class.toString()).commit();
                        menuTab.getMenu().findItem(R.id.menu_user_exhibitions).setChecked(true);

                        break;
                    case R.id.menu_user_like:
                        final FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                        List<Exhibit> list = new ArrayList<>();
                        List<Exhibition> list2 = new ArrayList<>();
                        LikedExhbViewPager exhibits = new LikedExhbViewPager(list, list2);
                        ft2.replace(R.id.container_tab_user, exhibits)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(Exhibits.class.toString()).commit();
                        menuTab.getMenu().findItem(R.id.menu_user_like).setChecked(true);

                        break;

                }
                return false;
            }

        });
    }
}

