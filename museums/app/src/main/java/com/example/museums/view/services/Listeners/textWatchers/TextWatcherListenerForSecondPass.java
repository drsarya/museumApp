//package com.example.museums.view.services.Listeners.textWatchers;
//
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.widget.EditText;
//
//import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;
//
//public class TextWatcherListenerForSecondPass implements TextWatcher {
//    private TextFieldBoxes textFieldBoxes;
//    private EditText passFirst;
//
//    public TextWatcherListenerForSecondPass(TextFieldBoxes textFieldBoxes, EditText passFirst) {
//        this.textFieldBoxes = textFieldBoxes;
//        this.passFirst = passFirst;
//    }
//
//    @Override
//    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//    }
//
//    @Override
//    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        if (!s.toString().equals(passFirst.getText().toString())) {
//            textFieldBoxes.setError("Пароли не совпадают", true);
//        } else {
//            textFieldBoxes.removeError();
//        }
//    }
//
//    @Override
//    public void afterTextChanged(Editable s) {
//    }
//}