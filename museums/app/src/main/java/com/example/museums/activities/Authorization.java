package com.example.museums.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.museums.R;


public class Authorization extends AppCompatActivity {
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

    private void setListeners() {
        reMuseum.setOnClickListener(v -> {
            Intent intent1 = new Intent(getApplication(), RegistrationMuseum.class);
            startActivity(intent1);
        });
        regPerson.setOnClickListener(v -> {
            Intent intent2 = new Intent(getApplication(), Registration.class);
            startActivity(intent2);
        });
    }
}
