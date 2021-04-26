package com.example.museums.view.activities.tabs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.museums.R;
import com.example.museums.view.fragments.admin.allMuseums.AllMuseums;
import com.example.museums.view.fragments.admin.createMuseum.CreateMuseum;
import com.example.museums.view.fragments.user.LikedExhbViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.museums.view.ConstantKeys.ID_MUSEUM_KEY;
import static com.example.museums.view.ConstantKeys.ID_USER_KEY;

public class AdminTab extends AppCompatActivity {
    private BottomNavigationView menuTab;
    private AllMuseums allMuseums;
    private Integer idUser;
    private CreateMuseum createMuseum;

    public static Intent newInstance(Context context, Integer userId) {
        Intent intent = new Intent(context, AdminTab.class);
        intent.putExtra(ID_USER_KEY, userId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_admin);
        menuTab =  findViewById(R.id.admin_tab_btnv);

        idUser = getIntent().getExtras().getInt(ID_USER_KEY);
        allMuseums = new AllMuseums().getInstance(idUser);
        createMuseum = new CreateMuseum();
        setInitialPage();
        menuTab.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.admin_tab_menu_all_museums:
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.container_admin, allMuseums)
                            .addToBackStack(AllMuseums.class.toString())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                    menuTab.getMenu().findItem(R.id.admin_tab_menu_all_museums).setChecked(true);
                    break;
                case R.id.admin_tab_menu_create:
                    FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();

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

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container_admin, allMuseums)
                .addToBackStack(AllMuseums.class.toString())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
        menuTab.getMenu().findItem(R.id.admin_tab_menu_all_museums).setChecked(true);

    }
}
