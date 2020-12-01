package com.example.museums.view.services.Listeners.textWatchers;

import android.text.Editable;
import android.text.TextWatcher;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class TextWatcherEmptyField implements TextWatcher {
    private TextFieldBoxes textFieldBoxes;

    public TextWatcherEmptyField(TextFieldBoxes textFieldBoxes) {
        this.textFieldBoxes = textFieldBoxes;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if (s.toString().trim().isEmpty()) {
            textFieldBoxes.setError("Поле обязательно для заполнения", false);
        } else {
            textFieldBoxes.removeError();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
