package com.example.museums.view.services.Listeners.textWatchers;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.API.models.Author;
import com.example.museums.view.services.recyclerViews.AuthorsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

//search для всех сделать recycler view наследником интерфейса с методом update all и два конструктра или просто два конструктора
// если authorAdapter == null и вызвать метод иногда для обновления списка с авторами

public class TextWatcherSearchAuthorRecyclerView implements TextWatcher {
    private RecyclerView recyclerView;
    private List<Author> list;
    private AuthorsRecyclerViewAdapter authorAdapter;

    public TextWatcherSearchAuthorRecyclerView(RecyclerView recyclerView, List<Author> list, AuthorsRecyclerViewAdapter authorAdapter) {
        this.recyclerView = recyclerView;
        this.list = list;
        this.authorAdapter = authorAdapter;
    }

    public void setList(List<Author> list) {
        this.list = list;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        System.out.println(s.length());
         System.out.println(list.size());
        System.out.println(authorAdapter);

        if (s.toString().isEmpty()) {
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
        }
        filter(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void filter(String text) {
        List<Author> temp = new ArrayList<>();
        for (Author d : list) {
            if (containsString(d.fullName, text)) {
                temp.add(d);
            }
        }
        authorAdapter.updateList(temp);
    }

    private boolean containsString(String fullName, String currText) {
        String newName = fullName.toLowerCase();
        String newCurrText = currText.toLowerCase();
        if (newName.contains(newCurrText)) {
            return true;
        } else return false;
    }
}
