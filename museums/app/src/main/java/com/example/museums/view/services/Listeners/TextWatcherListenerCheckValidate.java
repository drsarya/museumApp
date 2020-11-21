package com.example.museums.view.services.Listeners;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class TextWatcherListenerCheckValidate implements TextWatcher {
    private TextFieldBoxes textFieldBoxes;

    public TextWatcherListenerCheckValidate(TextFieldBoxes textFieldBoxes) {
        this.textFieldBoxes = textFieldBoxes;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if (s.toString().trim().isEmpty()) {
            textFieldBoxes.setError("поле обязательно для заполнения", false);
        } else {
            if (s.toString().contains(" ")) {
                textFieldBoxes.setError("поле содержит запрещённые символы", false);
            } else {
                textFieldBoxes.removeError();

            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
