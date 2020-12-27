package com.example.museums.view.activities.common.Authorization;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.museums.R;
import com.example.museums.view.activities.common.Registration.Registration;
import com.example.museums.view.activities.common.RegistrationMuseum.RegistrationMuseum;

import java.sql.Time;
import java.time.Instant;

public class Authorization extends AppCompatActivity {
    private Button regPerson;
    private Button reMuseum;
    public Button authBtn;
    private EditText logEditText;
    private EditText passEditText;
    private QueryAuthorization queryAuthorization;
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
        queryAuthorization = new QueryAuthorization(this);
        regPerson = (Button) findViewById(R.id.authorization_reg_person);
        reMuseum = (Button) findViewById(R.id.authorization_reg_museum);
        authBtn = (Button) findViewById(R.id.authorization_btn_auth);
        logEditText = (EditText) findViewById(R.id.authorization_log_text_view);
        passEditText = (EditText) findViewById(R.id.authorization_password_text_view);
    }

    private Instant time = null;

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
            if (time == null) {
                time = Instant.now();
            }


            if (!logEditText.getText().toString().isEmpty() && !passEditText.getText().toString().isEmpty()) {
                queryAuthorization.setUserInfo(logEditText.getText().toString(), passEditText.getText().toString());
                queryAuthorization.getQuery();
            } else {
                if (time.isBefore(time.plusSeconds(10))) {
                    count++;
                    if (count < 2) {
                        Toast.makeText(getApplicationContext(), "Проверьте введённые данные", Toast.LENGTH_SHORT).show();
                    } else {
                        if (count == 8) {
                            Toast.makeText(getApplicationContext(), "До добавления учетной записи администратора 2 нажатие", Toast.LENGTH_SHORT).show();
                        }  else if (count == 10) {
                            try {
                                time= null;
                                count = 0;
                                queryAuthorization.insertAdmin();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }else{
                    time= null;
                    count = 0;
                }
            }
        });
    }
}
