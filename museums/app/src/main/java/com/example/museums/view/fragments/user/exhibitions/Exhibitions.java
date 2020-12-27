package com.example.museums.view.fragments.user.exhibitions;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.API.models.ExhibitionWithMuseumName;
import com.example.museums.R;
import com.example.museums.view.services.recyclerViews.ExhibitionsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class Exhibitions extends Fragment {
    private RecyclerView recyclerView;
    private ExhibitionsRecyclerViewAdapter mAdapter = new ExhibitionsRecyclerViewAdapter();
    public static final String LOGIN_KEY_USER = "login_key";
    private Integer userId;
    private EditText searchEditText;
    private static String copySearch ="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.fragment_main_exhibitions, container, false);
        getArgumentsFromBundle();
        initViews(rootView);
        setListeners();
        return rootView;
    }

    public static Exhibitions newInstance(Integer userId) {
        final Exhibitions myFragment = new Exhibitions();
        final Bundle args = new Bundle(1);
        args.putInt(LOGIN_KEY_USER, userId);
        myFragment.setArguments(args);
        return myFragment;
    }

    private void getArgumentsFromBundle() {
        if (getArguments() != null) {
            userId = getArguments().getInt(LOGIN_KEY_USER);
        }
    }

    private void initViews(View rootView) {
        recyclerView = rootView.findViewById(R.id.recycler_view_exhibitons);
        searchEditText = rootView.findViewById(R.id.main_exhibitions_search_edit_tsxt);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setUserId(userId);
        QueryExhibitions queryExhibitions = new QueryExhibitions(this);

        if (copySearch.isEmpty()) {
            queryExhibitions.getQuery();
        } else {
            filter(copySearch);
        }
    }


    List<ExhibitionWithMuseumName> newExhibitModels = new ArrayList<>();

    public void refreshAllList(List<ExhibitionWithMuseumName> exhibitModels) {
        this.newExhibitModels = exhibitModels;
        mAdapter.submitList(exhibitModels);
    }

    private boolean containsString(String fullName, String currText) {
        String newName = fullName.toLowerCase();
        String newCurrText = currText.toLowerCase();
        if (newName.contains(newCurrText)) {
            return true;
        } else return false;
    }

    private void setListeners() {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                copySearch="";
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
        for (ExhibitionWithMuseumName d : newExhibitModels) {
            if (containsString(d.name, text)) {
                temp.add(d);
            }
        }
        mAdapter.submitList(temp);
    }
}
