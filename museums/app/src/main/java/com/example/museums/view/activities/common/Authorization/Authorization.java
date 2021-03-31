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

import com.example.museums.API.models.enums.RoleEnum;
import com.example.museums.API.models.user.NewUser;
import com.example.museums.API.presenter.BasePresenter;
import com.example.museums.API.presenter.BaseViewImpl;
import com.example.museums.R;
import com.example.museums.view.activities.common.Registration.Registration;
import com.example.museums.view.activities.common.RegistrationMuseum.RegistrationMuseum;


import java.time.Instant;

public class Authorization extends AppCompatActivity implements BasePresenter.View {
    private Button regPerson, authBtn, reMuseum;
    private EditText logEditText, passEditText;
    private AuthorizationPresenter authorizationPresenter;
    public ProgressBar progressBar;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        initViews();
        setListeners();
    }

    Integer count = 0;

    private void initViews() {
        progressBar = (ProgressBar) findViewById(R.id.authorization_progress_bar);
        authorizationPresenter = new AuthorizationPresenter();
        authorizationPresenter.attach(this);
        regPerson = (Button) findViewById(R.id.authorization_reg_person);
        reMuseum = (Button) findViewById(R.id.authorization_reg_museum);
        authBtn = (Button) findViewById(R.id.authorization_btn_auth);
        logEditText = (EditText) findViewById(R.id.authorization_log_text_view);
        passEditText = (EditText) findViewById(R.id.authorization_password_text_view);
    }


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
            authorizationPresenter.setUserInfo(logEditText.getText().toString(), passEditText.getText().toString());
            authorizationPresenter.loadData();
        });
    }

    @Override
    public <T> void showData(T data) {
        if (((NewUser) data).getRole() == RoleEnum.MUSEUM) {
            //  Toast.makeText(this.getApplicationContext(), "museum", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(getApplicationContext(), MuseumTab.class);
//            intent.putExtra(MuseumTab.LOGIN_KEY_USER, login);
//            activity.startActivity(intent);
        } else if (((NewUser) data).getRole() == RoleEnum.ADMIN) {
            //  Toast.makeText(this.getApplicationContext(), "admin", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(getApplicationContext(), AdminTab.class);
//            intent.putExtra(AdminTab.LOGIN_USER_KEY, login);
//            activity.startActivity(intent);
        } else {
            //  Toast.makeText(this.getApplicationContext(), "user", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(getApplicationContext(), UserTab.class);
//            intent.putExtra(UserTab.ID_USER_KEY, idUser);
//            intent.putExtra(UserTab.LOGIN_USER_KEY, login);
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        authorizationPresenter.detach();
    }


}
