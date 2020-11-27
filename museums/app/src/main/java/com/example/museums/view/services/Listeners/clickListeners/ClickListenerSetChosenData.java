package com.example.museums.view.services.Listeners.clickListeners;

import android.view.View;
import android.widget.EditText;

import com.example.museums.API.models.Author;

public class ClickListenerSetChosenData implements View.OnClickListener {
    private Author author;
    private EditText authorEditText;

    public ClickListenerSetChosenData(Author author, EditText authorEditText) {
        this.author = author;
        this.authorEditText = authorEditText;
    }

    @Override
    public void onClick(View v) {
        System.out.println("dfdddddddddddddddddddddddddddddd");
        authorEditText.setText(author.fullName);
    }
}
