package com.example.museums.view.activities.common.Registration;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.museums.R;
import com.example.museums.view.activities.common.Authorization.Authorization;
import com.example.museums.view.services.Listeners.GestureDetectorTurnBack;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class Registration extends AppCompatActivity {
    private ScrollView view;
    private RelativeLayout relativeLayoutMuseumReg;
    private EditText logEditText;
    private EditText passEditText;
    private EditText passSecondEditText;
    private Button registartionBtn;
    private TextFieldBoxes secondPassTextFieldBoxes;
    private TextFieldBoxes fisrtPassTextFieldBoxes;
    private QueryRegistration queryRegistration;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticvity_reg);
        initViews();
        setListeners();
    }

    private void initViews() {
        view = (ScrollView) findViewById(R.id.registration_scroll_view);
        relativeLayoutMuseumReg = (RelativeLayout) findViewById(R.id.registration_relative_layout);
        passEditText = (EditText) findViewById(R.id.registration_first_pass_edtext);
        logEditText = (EditText) findViewById(R.id.registration_login_edtext);
        passSecondEditText = (EditText) findViewById(R.id.registration_second_pass_edtext);
        registartionBtn = (Button) findViewById(R.id.registration_create_btn);
        secondPassTextFieldBoxes = (TextFieldBoxes) findViewById(R.id.registration_second_pass_textFBoxes);
        fisrtPassTextFieldBoxes = (TextFieldBoxes) findViewById(R.id.registration_first_pass_textFieldBoxes);
        queryRegistration = new QueryRegistration(this);
        progressBar = (ProgressBar) findViewById(R.id.registration_progress_bar);

    }

    private void setListeners() {
        onTouchlistener();
        btnListener();
        textFieldsListeners();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void onTouchlistener() {
        GestureDetectorTurnBack ndm = new GestureDetectorTurnBack();
        GestureDetector mDetector = new GestureDetector(getApplication(), ndm);

        view.setOnTouchListener((View v, MotionEvent event) -> {

            boolean b = mDetector.onTouchEvent(event);
            System.out.println(b);
            if (b) {
                Intent intent1 = new Intent(getApplication(), Authorization.class);
                this.startActivity(intent1);
            }
            return mDetector.onTouchEvent(event);
        });


    }


    private void btnListener() {
        registartionBtn.setOnClickListener(v -> {
            if (passSecondEditText.getText().toString().equals(passEditText.getText().toString()) && !logEditText.getText().toString().isEmpty() && !passEditText.getText().toString().isEmpty() && !passEditText.getText().toString().contains(" ")
                    && !passSecondEditText.getText().toString().contains(" ") && !logEditText.getText().toString().contains(" ")) {

                queryRegistration.getQuery(logEditText.getText().toString(), passEditText.getText().toString(), false);
            } else {
                Toast.makeText(getApplicationContext(), "Проверьте введённые данные", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void textFieldsListeners() {
        passEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(passSecondEditText.getText().toString()) && !passSecondEditText.getText().toString().isEmpty()) {
                    secondPassTextFieldBoxes.setError("Пароли не совпадают", false);
                } else {

                    secondPassTextFieldBoxes.setError("", false);
                }
                if (s.toString().contains(" ")) {
                    fisrtPassTextFieldBoxes.setError("Пароль содержит недопустимый символ", false);
                } else {
                    fisrtPassTextFieldBoxes.setError("", false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        passSecondEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(passEditText.getText().toString())) {
                    secondPassTextFieldBoxes.setError("Пароли не совпадают", true);
                } else {
                    secondPassTextFieldBoxes.setError("", true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
