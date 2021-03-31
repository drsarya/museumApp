package com.example.museums.view.activities.common.RegistrationMuseum;

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

import com.example.museums.API.models.OkModel;
import com.example.museums.API.presenter.BasePresenter;
import com.example.museums.R;
import com.example.museums.view.activities.common.Authorization.Authorization;
import com.example.museums.view.activities.common.Registration.RegistrationPresenter;
import com.example.museums.view.services.Listeners.GestureDetectorTurnBack;
import com.example.museums.view.services.Listeners.KeyboardListenerHideOptionalBlock;
import com.example.museums.view.services.Listeners.textWatchers.TextWatcherForFirstPass;
import com.example.museums.view.services.Listeners.textWatchers.TextWatcherListenerCheckValidate;
import com.example.museums.view.services.Listeners.textWatchers.TextWatcherListenerForSecondPass;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class RegistrationMuseum extends AppCompatActivity implements BasePresenter.View {
    private ScrollView view;
    private EditText idCodeEditText;
    private EditText loginEditText;
    private EditText firstPassEditText;
    private EditText secondPassEditText;
    private TextFieldBoxes idCodeTextFieldBoxes;
    private TextFieldBoxes loginTextFieldBoxes;
    private TextFieldBoxes firstPassTextFieldBoxes;
    private TextFieldBoxes secondPassTextFieldBoxes;
    private RelativeLayout layoutBtn;
    private Button regMuseumBtn;
    public ProgressBar progressBar;
    private RegistrationMuseumPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_museum);
        initViews();
        setListeners();
    }

    private void initViews() {
        idCodeEditText = (EditText) findViewById(R.id.reg_museum_id_code_edit_text);
        loginEditText = (EditText) findViewById(R.id.reg_museum_login_edit_text);
        firstPassEditText = (EditText) findViewById(R.id.reg_museum_first_pass_edit_text);
        secondPassEditText = (EditText) findViewById(R.id.reg_museum_second_pass_edit_text);
        idCodeTextFieldBoxes = (TextFieldBoxes) findViewById(R.id.reg_museum_id_code_text_field_boxes);
        loginTextFieldBoxes = (TextFieldBoxes) findViewById(R.id.reg_museum_login_text_field_boxes);
        firstPassTextFieldBoxes = (TextFieldBoxes) findViewById(R.id.reg_museum_first_pass_text_field_boxes);
        secondPassTextFieldBoxes = (TextFieldBoxes) findViewById(R.id.reg_museum_second_pass_text_field_boxes);
        view = (ScrollView) findViewById(R.id.registration_museum_scroll_view);
        layoutBtn = (RelativeLayout) findViewById(R.id.registration_museum_relative_layout);
        regMuseumBtn = (Button) findViewById(R.id.reg_museum_reg_btn);
        progressBar = (ProgressBar) findViewById(R.id.registration_museum_progress_bar);
        presenter = new RegistrationMuseumPresenter();
        presenter.attach(this);
    }

    private void setListeners() {
        onTouchListener();
        btnListener();
        idCodeEditText.addTextChangedListener(new TextWatcherListenerCheckValidate(idCodeTextFieldBoxes));
        loginEditText.addTextChangedListener(new TextWatcherListenerCheckValidate(loginTextFieldBoxes));
        firstPassEditText.addTextChangedListener(new TextWatcherForFirstPass(firstPassTextFieldBoxes, secondPassTextFieldBoxes, secondPassEditText));
        secondPassEditText.addTextChangedListener(new TextWatcherListenerForSecondPass(secondPassTextFieldBoxes, firstPassEditText));
        KeyboardVisibilityEvent.setEventListener(this, new KeyboardListenerHideOptionalBlock(layoutBtn));
    }

    private void btnListener() {
        regMuseumBtn.setOnClickListener(v ->
        {
            presenter.setInfo(Integer.parseInt(idCodeEditText.getText().toString()),
                    loginEditText.getText().toString(),
                    firstPassEditText.getText().toString()
            );
            presenter.loadData();
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void onTouchListener() {
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

    @Override
    public <T> void showData(T data) {
        Toast.makeText(getApplicationContext(), ((OkModel) data).getMessage(), Toast.LENGTH_SHORT).show();
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
        presenter.detach();
    }
}
