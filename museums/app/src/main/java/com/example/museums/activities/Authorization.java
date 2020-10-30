package com.example.museums.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.museums.R;

public class Authorization extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    ListView countriesList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

    }

    public void showPopup(View view) {
        Context wrapper = new ContextThemeWrapper(this, R.style.menuStyle);
        PopupMenu popup = new PopupMenu(wrapper, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_authorization, popup.getMenu());
        popup.setOnMenuItemClickListener(this);
         popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.authorization_production:
                //
                 return true;
            case R.id.authorization_register:
                //
                 return true;
            default:
                return false;
        }
    }
}
