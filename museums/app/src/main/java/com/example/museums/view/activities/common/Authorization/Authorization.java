package com.example.museums.view.activities.common.Authorization;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.museums.R;
import com.example.museums.view.activities.common.Registration.Registration;
import com.example.museums.view.activities.common.RegistrationMuseum.RegistrationMuseum;

public class Authorization extends AppCompatActivity {
    private Button regPerson;
    private Button reMuseum;
    public Button authBtn;
    private EditText logEditText;
    private EditText passEditText;
    private QueryAuthorization queryAuthorization;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        initViews();
        setListeners();
    }

    private void initViews() {
        progressBar = (ProgressBar) findViewById(R.id.authorization_progress_bar);
        queryAuthorization = new QueryAuthorization(this);
        regPerson = (Button) findViewById(R.id.authorization_reg_person);
        reMuseum = (Button) findViewById(R.id.authorization_reg_museum);
        authBtn = (Button) findViewById(R.id.authorization_btn_auth);
        logEditText = (EditText) findViewById(R.id.authorization_log_text_view);
        passEditText = (EditText) findViewById(R.id.authorization_password_text_view);
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
        authBtn.setOnClickListener(v -> {
            if (!logEditText.getText().toString().isEmpty() && !passEditText.getText().toString().isEmpty()) {
                queryAuthorization.setUserInfo(logEditText.getText().toString(), passEditText.getText().toString());
                queryAuthorization.getQuery();
            } else {
                Toast.makeText(getApplicationContext(), "Проверьте введённые данные", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
