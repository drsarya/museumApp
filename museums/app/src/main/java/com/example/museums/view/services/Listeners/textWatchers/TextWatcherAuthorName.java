package com.example.museums.view.services.Listeners.textWatchers;

import android.text.Editable;
import android.text.TextWatcher;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class TextWatcherAuthorName implements TextWatcher {
    private  TextFieldBoxes authorTextFieldBoxes ;
    public TextWatcherAuthorName(TextFieldBoxes authorTextFieldBoxes) {

        this.authorTextFieldBoxes = authorTextFieldBoxes;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String [] name =  s.toString().split(" ");
        if (name.length == 3) {
            //ФИО
        } else if (name.length == 2) {
            //ИФ
        } else if (name.length == 1) {
            //И
        }else{authorTextFieldBoxes.setError("ВВодите имя автора в соответствии с форматом Ф И О или И Ф   ", false);}
    }
}
