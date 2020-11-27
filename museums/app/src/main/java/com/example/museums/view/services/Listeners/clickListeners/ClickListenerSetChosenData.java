package com.example.museums.view.services.Listeners.clickListeners;

import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.API.models.Author;

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
