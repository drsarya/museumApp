package com.example.museums.view.activities.common.Authorization;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.museums.API.models.enums.RoleEnum;
import com.example.museums.API.models.user.ExistingUser;
import com.example.museums.R;
import com.example.museums.view.activities.common.Registration.Registration;
import com.example.museums.view.activities.common.RegistrationMuseum.RegistrationMuseum;

public class Authorization extends AppCompatActivity {
    private Button regPerson, authBtn, reMuseum;
    private EditText logEditText, passEditText;
    public ProgressBar progressBar;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        initViews();
        setListeners();


    }


    private void initViews() {
        progressBar = (ProgressBar) findViewById(R.id.authorization_progress_bar);
        regPerson = (Button) findViewById(R.id.authorization_reg_person);
        reMuseum = (Button) findViewById(R.id.authorization_reg_museum);
        authBtn = (Button) findViewById(R.id.authorization_btn_auth);
        logEditText = (EditText) findViewById(R.id.authorization_log_text_view);
        passEditText = (EditText) findViewById(R.id.authorization_password_text_view);
    }

    AuthorizationViewModel authorizationViewModel;

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setListeners() {
        reMuseum.setOnClickListener(v -> {
            Intent intent1 = new Intent(getApplication(), RegistrationMuseum.class);
            startActivity(intent1);
        });
        regPerson.setOnClickListener(v -> {
            Intent intent2 = new Intent(getApplication(), Registration.class);
            startActivity(intent2);
        });

        authBtn.setOnClickListener(v -> {
            authorizationViewModel = ViewModelProviders.of(this).get(AuthorizationViewModel.class);
            authorizationViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean isLoading) {
                    if (isLoading) progressBar.setVisibility(View.VISIBLE);
                    else progressBar.setVisibility(View.GONE);
                }
            });
            authorizationViewModel.getLiveDataUser(logEditText.getText().toString(), passEditText.getText().toString())
                    .observe(this, new Observer<ExistingUser>() {
                        @Override
                        public void onChanged(@Nullable ExistingUser aBoolean) {
                            authorizationViewModel.getIsLoading().postValue(false);

                            if (aBoolean == null) {
                                Toast.makeText(getApplicationContext(), "Неверные данные", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), aBoolean.login + " " + aBoolean.role, Toast.LENGTH_SHORT).show();
                                checkData(aBoolean);
                            }
                        }
                    });


        });
    }


    private void checkData(ExistingUser existingUser) {
        if (existingUser.getRole() == RoleEnum.MUSEUM) {
            //  Toast.makeText(this.getApplicationContext(), "museum", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(getApplicationContext(), MuseumTab.class);
//            intent.putExtra(MuseumTab.LOGIN_KEY_USER, exhistingUser.museum.museumId);
//            activity.startActivity(intent);
        } else if (existingUser.getRole() == RoleEnum.ADMIN) {
            //  Toast.makeText(this.getApplicationContext(), "admin", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(getApplicationContext(), AdminTab.class);
//            intent.putExtra(AdminTab.LOGIN_USER_KEY, idUser);
//            activity.startActivity(intent);
        } else {
            //  Toast.makeText(this.getApplicationContext(), "user", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(getApplicationContext(), UserTab.class);
//            intent.putExtra(UserTab.ID_USER_KEY, idUser);
//            intent.putExtra(UserTab.LOGIN_USER_KEY, login);
        }

    }


}
