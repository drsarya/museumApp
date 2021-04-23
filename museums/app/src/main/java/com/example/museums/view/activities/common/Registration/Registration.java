package com.example.museums.view.activities.common.Registration;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.museums.API.models.AnswerModel;
import com.example.museums.R;
import com.example.museums.view.activities.common.Authorization.Authorization;
import com.example.museums.view.services.Listeners.GestureDetectorTurnBack;
import com.example.museums.view.services.Listeners.textWatchers.TextWatcherForFirstPass;
import com.example.museums.view.services.Listeners.textWatchers.TextWatcherListenerCheckValidate;
import com.example.museums.view.services.Listeners.textWatchers.TextWatcherListenerForSecondPass;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class Registration extends AppCompatActivity {
    private ScrollView view;
    private RelativeLayout relativeLayoutMuseumReg;
    private EditText loginEditText, passFirstEditText, passSecondEditText;
    private Button registrationBtn;
    private TextFieldBoxes secondPassTextFieldBoxes, loginTextFieldBoxes, firstPassTextFieldBoxes;
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
        passFirstEditText = (EditText) findViewById(R.id.registration_first_pass_edtext);
        loginEditText = (EditText) findViewById(R.id.registration_login_edtext);
        passSecondEditText = (EditText) findViewById(R.id.registration_second_pass_edtext);
        registrationBtn = (Button) findViewById(R.id.registration_create_btn);
        secondPassTextFieldBoxes = (TextFieldBoxes) findViewById(R.id.registration_second_pass_textFBoxes);
        firstPassTextFieldBoxes = (TextFieldBoxes) findViewById(R.id.registration_first_pass_textFieldBoxes);
        progressBar = (ProgressBar) findViewById(R.id.registration_progress_bar);
        loginTextFieldBoxes = (TextFieldBoxes) findViewById(R.id.registration_login_text_field_boxes);
    }

    private void setListeners() {
        onTouchListener();
        btnListener();
        textFieldsListeners();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void onTouchListener() {
        GestureDetectorTurnBack ndm = new GestureDetectorTurnBack();
        GestureDetector mDetector = new GestureDetector(getApplication(), ndm);
        view.setOnTouchListener((View v, MotionEvent event) -> {
            boolean b = mDetector.onTouchEvent(event);
            if (b) {
                Intent intent1 = new Intent(getApplication(), Authorization.class);
                this.startActivity(intent1);
            }
            return mDetector.onTouchEvent(event);
        });
    }

    RegistrationViewModel registrationViewModel;

    private void btnListener() {
        registrationBtn.setOnClickListener(v -> {

            registrationViewModel = ViewModelProviders.of(this).get(RegistrationViewModel.class);
            registrationViewModel.getIsLoading().observe(this, isLoading -> {
                if (isLoading) progressBar.setVisibility(View.VISIBLE);
                else progressBar.setVisibility(View.GONE);
            });
            registrationViewModel.getLiveDataUser(loginEditText.getText().toString(), passFirstEditText.getText().toString())
                    .observe(this, aBoolean -> {
                        registrationViewModel.getIsLoading().postValue(false);
                        if (aBoolean == null) {
                            Toast.makeText(getApplicationContext(), "Ошибка регистрации", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), aBoolean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    private void textFieldsListeners() {
        loginEditText.addTextChangedListener(new TextWatcherListenerCheckValidate(loginTextFieldBoxes));
        passFirstEditText.addTextChangedListener(new TextWatcherForFirstPass(firstPassTextFieldBoxes, secondPassTextFieldBoxes, passSecondEditText));
        passSecondEditText.addTextChangedListener(new TextWatcherListenerForSecondPass(secondPassTextFieldBoxes, passFirstEditText));
    }


}
