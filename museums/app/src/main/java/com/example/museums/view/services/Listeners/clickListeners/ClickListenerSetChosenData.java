package com.example.museums.view.services.Listeners.clickListeners;

import android.view.View;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

public class ClickListenerSetChosenData implements View.OnClickListener {
    private Author author;
    private EditText authorEditText;
    private RecyclerView recyclerView;

    public ClickListenerSetChosenData(Author author, EditText authorEditText, RecyclerView recyclerView) {
        this.author = author;
        this.authorEditText = authorEditText;
        this.recyclerView = recyclerView;
    }

    @Override
    public void onClick(View v) {
        authorEditText.setText(author.fullName);
        recyclerView.setVisibility(View.GONE);
    }
}
