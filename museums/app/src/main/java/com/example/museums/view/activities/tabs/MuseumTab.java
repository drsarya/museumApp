

package com.example.museums.view.activities.tabs;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.example.museums.R;
import com.example.museums.view.fragments.museum.exhibition.createExhibition.CreateExhibition;
import com.example.museums.view.fragments.museum.museumExhibitions.MuseumExhibitions;
import com.example.museums.view.fragments.museum.mainInfoMuseumEditPage.MainInfoMuseumPageEdit.MainInfoMuseumPageEdit;
import com.example.museums.view.fragments.museum.museumExhibits.MuseumExhibits;
import com.example.museums.view.services.Listeners.KeyboardListenerHideOptionalBlock;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;

import static com.example.museums.view.ConstantKeys.ID_MUSEUM_KEY;
import static com.example.museums.view.ConstantKeys.ID_USER_KEY;


public class MuseumTab extends AppCompatActivity {
    private BottomNavigationView menuTab;
    private boolean currState = false;
    private Integer idMuseum = 1;
    private CreateExhibition createExhibition;
    private MuseumExhibits exhibits;
    private MainInfoMuseumPageEdit mainInfoMuseumPage;
    private MuseumExhibitions exhibitions;

    public static Intent newInstance(Integer userId) {
        Intent intent = new Intent();
        intent.putExtra(ID_MUSEUM_KEY, userId);
        return intent;
    }

    private void initViews() {
        menuTab = findViewById(R.id.museum_bnview);
        KeyboardVisibilityEvent.setEventListener(this, new KeyboardListenerHideOptionalBlock(menuTab));
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        setContentView(R.layout.activity_tab_museum);


        idMuseum = b.getInt(ID_MUSEUM_KEY);
        exhibitions = new MuseumExhibitions().newInstance(idMuseum);
        mainInfoMuseumPage = new MainInfoMuseumPageEdit().newInstance(idMuseum);
        exhibits = new MuseumExhibits().newInstance(idMuseum);
        createExhibition = new CreateExhibition().newInstance(idMuseum);


        initViews();
        setInitialPage();
        menuTab.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.menu_fr_create_exhbtn:
                    final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.container_tab_museum, createExhibition)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                    menuTab.getMenu().findItem(R.id.menu_fr_create_exhbtn).setChecked(true);

                    break;
                case R.id.menu_fr_exhibitions:
                    final FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                    ft1.replace(R.id.container_tab_museum, exhibitions)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                    menuTab.getMenu().findItem(R.id.menu_fr_exhibitions).setChecked(true);

                    break;
                case R.id.menu_fr_exhibits:
                    final FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                    ft2.replace(R.id.container_tab_museum, exhibits)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                    menuTab.getMenu().findItem(R.id.menu_fr_exhibits).setChecked(true);

                    break;
                case R.id.menu_fr_main_museum:
                    final FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                    ft3.replace(R.id.container_tab_museum, mainInfoMuseumPage)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                    menuTab.getMenu().findItem(R.id.menu_fr_main_museum).setChecked(true);
                    break;
            }
            return false;
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.museum_tab, menu);
        return true;

    }

    private void setInitialPage() {
        if (!currState) {
            currState = true;
            final FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
            ft3.replace(R.id.container_tab_museum, mainInfoMuseumPage)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
            menuTab.getMenu().findItem(R.id.menu_fr_main_museum).setChecked(true);
        }
    }
}

