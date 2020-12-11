package com.example.museums.view.fragments.user.exhibits;

import android.annotation.SuppressLint;
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

import com.example.museums.API.models.NewExhibitModel;
import com.example.museums.R;
import com.example.museums.view.fragments.common.museumInfo.QueryMuseumInfo;
import com.example.museums.view.services.recyclerViews.ExhibitsRecyclerViewAdapter;
import com.example.museums.view.services.recyclerViews.TagsRecyclerViewAdapter;
import com.example.museums.API.models.Exhibit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exhibits extends Fragment {

    private RecyclerView recyclerView;
    private ExhibitsRecyclerViewAdapter mAdapter = new ExhibitsRecyclerViewAdapter();
    private RecyclerView.Adapter horizontalAdapter;
    public RecyclerView listView;
    public static final String LOGIN_KEY_USER = "login_key";

    private Integer userId;
    private EditText searchEditText;
    private List<String> names = Arrays.asList("Третьяковская галерея", "Воронежская галерея", "Графика", "Животные", "Живопись", "Люди");
    private List<Exhibit> in = new ArrayList<>();


    public static Exhibits newInstance(Integer idUser) {
        final Exhibits myFragment = new Exhibits();
        final Bundle args = new Bundle(1);
        args.putInt(LOGIN_KEY_USER, idUser);
        myFragment.setArguments(args);
        return myFragment;
    }

    private void getArgumentsFromBundle() {
        if (getArguments() != null) {
            userId = getArguments().getInt(LOGIN_KEY_USER);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_main_exhibits, container, false);
        getArgumentsFromBundle();
        initViews(rootView);

        setListeners();
        return rootView;
    }

    private void initViews(View rootView) {

        horizontalAdapter = new TagsRecyclerViewAdapter(names);
        recyclerView = rootView.findViewById(R.id.recycler_view_exhibits);
        listView = rootView.findViewById(R.id.main_exhibits_recycler_view);
        recyclerView.setAdapter(mAdapter);
        listView.setAdapter(horizontalAdapter);
        searchEditText = rootView.findViewById(R.id.main_exhibits_search_edit_text);
        mAdapter.setUserId(userId);
        QueryExhibits queryExhibits = new QueryExhibits(this);
        queryExhibits.getQuery();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    List<NewExhibitModel> newExhibitModels = new ArrayList<>();

    public void refreshAllList(List<NewExhibitModel> exhibitModels) {
        this.newExhibitModels = exhibitModels;
        mAdapter.submitList(exhibitModels);
    }

    private void setListeners() {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                recyclerView.setVisibility(View.VISIBLE);
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private boolean containsString(String fullName, String currText) {
        String newName = fullName.toLowerCase();
        String newCurrText = currText.toLowerCase();
        if (newName.contains(newCurrText)) {
            return true;
        } else return false;
    }

    private void filter(String text) {
        List<NewExhibitModel> temp = new ArrayList();
        for (NewExhibitModel d : newExhibitModels) {
            if (containsString(d.name, text) || containsString(d.author, text)) {
                temp.add(d);
            }
        }
        mAdapter.submitList(temp);
    }
}
