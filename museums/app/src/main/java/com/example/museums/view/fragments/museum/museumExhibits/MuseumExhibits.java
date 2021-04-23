package com.example.museums.view.fragments.museum.museumExhibits;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.R;
import com.example.museums.view.services.oop.IDeletePosition;
import com.example.museums.view.services.recyclerViews.MuseumExhibitsRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MuseumExhibits extends Fragment implements IDeletePosition {
    private RecyclerView recyclerView;
    private MuseumExhibitsRecyclerAdapter mAdapter = new MuseumExhibitsRecyclerAdapter(this);
    public static final String ID_MUSEUM = "id_key";
    private Integer idMuseum;
    public ProgressBar progressBar;
    private TextView emptyTV;
    private EditText searchEditText;
    private List<ExistingExhibit> newExhibitModels = new ArrayList<>();
    private static String copySearch = "";


    public MuseumExhibits newInstance(Integer idMuseum) {
        final MuseumExhibits myFragment = new MuseumExhibits();
        final Bundle args = new Bundle(1);
        args.putInt(ID_MUSEUM, idMuseum);
        myFragment.setArguments(args);
        return myFragment;
    }

    private void getArgumentsFromBundle() {
        if (getArguments() != null) {
            idMuseum = getArguments().getInt(ID_MUSEUM);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_museum_exhibits, container, false);
        getArgumentsFromBundle();
        initView(rootView);
        setListeners();
        return rootView;
    }

    private void setListeners() {
        searchEditText.addTextChangedListener(new TextWatcher() {
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


    private boolean containsString(String fullName, String currText) {
        String newName = fullName.toLowerCase();
        String newCurrText = currText.toLowerCase();
        if (newName.contains(newCurrText)) {
            return true;
        } else return false;
    }

    private void filter(String text) {
        List<ExistingExhibit> temp = new ArrayList();
        for (ExistingExhibit d : newExhibitModels) {
            if (containsString(d.getName(), text) || containsString(d.getAuthor().getFullName(), text)) {
                temp.add(d);
            }
        }
        mAdapter.submitList(temp);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    private MuseumExhibitsViewModel viewModel;

    private void initView(View rootView) {
        searchEditText = rootView.findViewById(R.id.museum_exhibits_search_edit_text);
        recyclerView = rootView.findViewById(R.id.museum_exhibits_recycler_view);
        progressBar = rootView.findViewById(R.id.museum_exhibits_recycler_progress_bar);
        emptyTV = rootView.findViewById(R.id.museum_exhibits_empty_tv);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        viewModel = ViewModelProviders.of(this).get(MuseumExhibitsViewModel.class);
        if (copySearch.isEmpty()) {
            getExhibitsMuseum();
        } else {
            filter(copySearch);
        }
    }

    public void getExhibitsMuseum() {
        viewModel.getIsLoadingExhibits().observe(this, isLoading -> {
            if (isLoading) progressBar.setVisibility(View.VISIBLE);
            else progressBar.setVisibility(View.GONE);
        });
        viewModel.getExhibitsLiveData(idMuseum)
                .observe(this, model -> {
                    viewModel.getIsLoadingExhibits().postValue(false);
                    if (model == null) {
                        Toast.makeText(getContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
                    } else {
                        if (model.isEmpty()) {
                            emptyTV.setVisibility(View.VISIBLE);
                            setExhibits(new ArrayList<>());
                        } else {
                            emptyTV.setVisibility(View.INVISIBLE);
                            setExhibits(model);
                        }
                    }
                });
    }

    public void setExhibits(List<ExistingExhibit> exhibitModels) {
        this.newExhibitModels = exhibitModels;
        mAdapter.submitList(exhibitModels);
    }

    @Override
    public void deletePosition(int position, Integer id) {
        viewModel.getIsLoadingDeleteExhibit().observe(this, isLoading -> {
            if (isLoading) progressBar.setVisibility(View.VISIBLE);
            else progressBar.setVisibility(View.GONE);
        });
        viewModel.deleteExhibitLiveData(id)
                .observe(this, model -> {
                    viewModel.getIsLoadingDeleteExhibit().postValue(false);
                    if (model == null) {
                        Toast.makeText(getContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), model.getMessage(), Toast.LENGTH_SHORT).show();
                        getExhibitsMuseum();
                    }
                });
    }


}
