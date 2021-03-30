package com.example.museums.view.fragments.museum.museumExhibitions;


import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.R;
import com.example.museums.view.services.oop.IDeletePosition;
import com.example.museums.view.services.recyclerViews.EditExhibitionRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;


public class MuseumExhibitions extends Fragment implements IDeletePosition {
    public ProgressBar progressBar;
    private RecyclerView recyclerView;
    private EditExhibitionRecyclerAdapter adapter = new EditExhibitionRecyclerAdapter(this);
    public static final String LOGIN_KEY_USER = "login_key";
    public String login;
    private static String copySearch = "";
    private EditText searchText;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_museum_exhibitions, container, false);
        getArgumentsFromBundle();
        initViews(rootView);
        setListeners();
        return rootView;
    }

    public MuseumExhibitions newInstance(String login) {
        final MuseumExhibitions myFragment = new MuseumExhibitions();
        final Bundle args = new Bundle(1);
        args.putString(LOGIN_KEY_USER, login);
        myFragment.setArguments(args);
        return myFragment;
    }

    private void getArgumentsFromBundle() {
        if (getArguments() != null) {
            login = getArguments().getString(LOGIN_KEY_USER);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private List<ExhibitionWithMuseumName> exhibitions = new ArrayList<>();

    private QueryDeleteExhibition queryDeleteExhibition;

    private void initViews(View rootView) {
        progressBar = rootView.findViewById(R.id.museum_exhibitions_progress_bar);
        recyclerView = rootView.findViewById(R.id.recycler_view_museum_exhibitions);
        QueryMuseumExhibitions queryMuseumExhibitions = new QueryMuseumExhibitions(this);
        queryDeleteExhibition = new QueryDeleteExhibition(this);
        recyclerView.setAdapter(adapter);
        searchText = rootView.findViewById(R.id.museum_exhibitions_search_exhibition);
        if (copySearch.isEmpty()) {
            queryMuseumExhibitions.getQuery(login);
        } else {
            filter(copySearch);
        }
    }

    private boolean containsString(String fullName, String currText) {
        String newName = fullName.toLowerCase();
        String newCurrText = currText.toLowerCase();
        if (newName.contains(newCurrText)) {
            return true;
        } else return false;
    }

    private void setListeners() {
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                copySearch = "";
                recyclerView.setVisibility(View.VISIBLE);
                copySearch += s;
                filter(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void filter(String text) {
        List<ExhibitionWithMuseumName> temp = new ArrayList();
        for (ExhibitionWithMuseumName d : exhibitions) {
            if (containsString(d.name, text)) {
                temp.add(d);
            }
        }
        adapter.submitList(temp);
    }


    public void updateAll(List<ExhibitionWithMuseumName> exhibitions) {
        this.exhibitions = exhibitions;
        adapter.submitList(exhibitions);
    }

    @Override
    public void deletePosition(int position, Integer id) {
        queryDeleteExhibition.getQuery(id);
    }
}
