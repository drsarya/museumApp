package com.example.museums.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.museums.R;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

public class Authorization extends AppCompatActivity  {
    private Button regPerson;
    private Button reMuseum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        initViews();
        setListeners();
    }

    private void initViews() {

        regPerson = (Button) findViewById(R.id.authorization_reg_person);
        reMuseum = (Button) findViewById(R.id.authorization_reg_museum);

    }

//    public void showPopup(View view) {
//        Context wrapper = new ContextThemeWrapper(this, R.style.menuStyle);
//        PopupMenu popup = new PopupMenu(wrapper, view);
//        MenuInflater inflater = popup.getMenuInflater();
//        inflater.inflate(R.menu.menu_authorization, popup.getMenu());
//        popup.setOnMenuItemClickListener(this);
//        popup.show();
//    }

    private void setListeners() {
        reMuseum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplication(), RegistrationMuseum.class);
                startActivity(intent1);
            }
        });
        regPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplication(), Registration.class);
                startActivity(intent2);
            }
        });
    }

//    @Override
//    public boolean onMenuItemClick(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.authorization_production:
//                Intent intent1 = new Intent(getApplication(), RegistrationMuseum.class);
//                this.startActivity(intent1);
//                return true;
//            case R.id.authorization_register:
//                Intent intent2 = new Intent(getApplication(), Registration.class);
//                this.startActivity(intent2);
//                return true;
//            default:
//                return false;
//        }
//    }
}
