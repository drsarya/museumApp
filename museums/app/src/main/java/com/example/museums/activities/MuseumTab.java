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
import com.example.museums.fragments.museum.CreateExhibition;
import com.example.museums.fragments.museum.Exhibitions;
import com.example.museums.fragments.museum.Exhibits;
import com.example.museums.fragments.museum.MainInfoMuseumPage;
import com.example.services.models.Exhibit;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MuseumTab extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_museum);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.museum_bnview);
        final FragmentManager fm = getSupportFragmentManager();
        final FragmentTransaction ft = fm.beginTransaction();
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_fr_create_exhbtn:
                                CreateExhibition createExh  = new CreateExhibition();
                                ft.replace(R.id.container, createExh);
                                ft.commit();
                                break;
                            case R.id.menu_fr_exhibitions:
                                Exhibitions exhibitions  = new Exhibitions();
                                ft.replace(R.id.container, exhibitions);
                                ft.commit();
                                break;
                            case R.id.menu_fr_exhibits:
                                Exhibits exhibits  = new Exhibits();
                                ft.replace(R.id.container, exhibits);
                                ft.commit();
                                break;
                            case R.id.menu_fr_main_museum:
                                MainInfoMuseumPage mainInfoMuseumPage  = new MainInfoMuseumPage();
                                ft.replace(R.id.container, mainInfoMuseumPage);
                                ft.commit();
                                break;
                        }
                        return false;
                    }
                });
    }
}
