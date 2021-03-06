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
import androidx.lifecycle.ViewModelProviders;

import com.example.museums.R;
import com.example.museums.view.activities.common.Authorization.Authorization;
import com.example.museums.view.services.Listeners.GestureDetectorTurnBack;
import com.example.museums.view.services.Listeners.KeyboardListenerHideOptionalBlock;
import com.example.museums.view.services.Listeners.textWatchers.TextWatcherForFirstPass;
import com.example.museums.view.services.Listeners.textWatchers.TextWatcherListenerCheckValidate;
import com.example.museums.view.services.Listeners.textWatchers.TextWatcherListenerForSecondPass;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class RegistrationMuseum extends AppCompatActivity {
    private ScrollView view;
    private EditText idCodeEditText, loginEditText, firstPassEditText, secondPassEditText;
    private TextFieldBoxes idCodeTextFieldBoxes, loginTextFieldBoxes, firstPassTextFieldBoxes, secondPassTextFieldBoxes;
    private RelativeLayout layoutBtn;
    private Button regMuseumBtn;
    public ProgressBar progressBar;
    private RegistrationMuseumViewModel viewModel;

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
            if (!idCodeTextFieldBoxes.isOnError() && !loginTextFieldBoxes.isOnError() && !firstPassTextFieldBoxes.isOnError() && !secondPassTextFieldBoxes.isOnError() &&
                    !idCodeEditText.getText().toString().isEmpty() && !loginEditText.getText().toString().isEmpty() && !firstPassEditText.getText().toString().isEmpty() && !secondPassEditText.getText().toString().isEmpty()) {
                registration();
            } else {
                Toast.makeText(getApplicationContext(), "Проверьте введённые данные", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void registration() {
        viewModel = ViewModelProviders.of(this).get(RegistrationMuseumViewModel.class);
        viewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading) progressBar.setVisibility(View.VISIBLE);
            else progressBar.setVisibility(View.GONE);
        });
        viewModel.getLiveDataUser(Integer.parseInt(idCodeEditText.getText().toString()), loginEditText.getText().toString(), firstPassEditText.getText().toString())
                .observe(this, model -> {
                    viewModel.getIsLoading().postValue(false);
                    if (model == null) {
                        Toast.makeText(getApplicationContext(), "Ошибка регистрации", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), model.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

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
}
