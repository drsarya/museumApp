package com.example.museums.view.services.Listeners.textWatchers;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class TextWatcherForFirstPass implements TextWatcher {
    private TextFieldBoxes textFirstFieldBoxes;
    private TextFieldBoxes textSecondFieldBoxes;

    private EditText passSecondEditText;

    public TextWatcherForFirstPass(TextFieldBoxes textFirstFieldBoxes, TextFieldBoxes textSecondFieldBoxes, EditText passSecond) {
        this.textFirstFieldBoxes = textFirstFieldBoxes;
        this.textSecondFieldBoxes = textSecondFieldBoxes;
        this.passSecondEditText = passSecond;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!s.toString().equals(passSecondEditText.getText().toString()) && !passSecondEditText.getText().toString().isEmpty()) {
            textSecondFieldBoxes.setError("Пароли не совпадают", false);
        } else {
            textSecondFieldBoxes.removeError();
        }
        if (s.toString().isEmpty()) {
            textSecondFieldBoxes.setError("Поле обязательно для заполнения", false);
        } else if (s.toString().contains(" ")) {
            textFirstFieldBoxes.setError("Пароль содержит недопустимый символ", false);
        } else {
            textFirstFieldBoxes.removeError();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}