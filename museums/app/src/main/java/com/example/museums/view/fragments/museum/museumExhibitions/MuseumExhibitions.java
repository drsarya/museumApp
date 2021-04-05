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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.API.models.exhibition.ExistingExhibition;
import com.example.museums.R;
import com.example.museums.view.services.oop.IDeletePosition;
import com.example.museums.view.services.recyclerViews.EditExhibitionRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;


public class MuseumExhibitions extends Fragment implements IDeletePosition {
    public ProgressBar progressBar;
    private RecyclerView recyclerView;
    private EditExhibitionRecyclerAdapter adapter = new EditExhibitionRecyclerAdapter(this);
    public static final String ID_MUSEUM = "id_key";
    public Integer idMuseum;
    private static String copySearch = "";
    private EditText searchText;
    private TextView emptyTV;
    private MuseumExhibitionsViewModel viewModel;

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

    public MuseumExhibitions newInstance(Integer idMuseum) {
        final MuseumExhibitions myFragment = new MuseumExhibitions();
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private List<ExistingExhibition> exhibitions = new ArrayList<>();


    private void initViews(View rootView) {
        progressBar = rootView.findViewById(R.id.museum_exhibitions_progress_bar);
        recyclerView = rootView.findViewById(R.id.recycler_view_museum_exhibitions);
        emptyTV = rootView.findViewById(R.id.museum_exhibitions_empty_tv);
        viewModel = ViewModelProviders.of(this).get(MuseumExhibitionsViewModel.class);
        recyclerView.setAdapter(adapter);
        searchText = rootView.findViewById(R.id.museum_exhibitions_search_exhibition);
        if (copySearch.isEmpty()) {
            getExhibitionsMuseum();
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
        List<ExistingExhibition> temp = new ArrayList();
        for (ExistingExhibition d : exhibitions) {
            if (containsString(d.getName(), text)) {
                temp.add(d);
            }
        }
        adapter.submitList(temp);
    }


    public void setExhibitionsMuseum(List<ExistingExhibition> exhibitions) {
        this.exhibitions = exhibitions;

        adapter.submitList(exhibitions);
    }

    public void getExhibitionsMuseum() {
        viewModel.getIsLoadingExhibitions().observe(this, isLoading -> {
            if (isLoading) progressBar.setVisibility(View.VISIBLE);
            else progressBar.setVisibility(View.GONE);
        });
        viewModel.getExhibitionsLiveData(idMuseum)
                .observe(this, model -> {
                    viewModel.getIsLoadingExhibitions().postValue(false);
                    if (model == null) {
                        Toast.makeText(getContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
                    } else {
                        if (model.isEmpty()) {
                            emptyTV.setVisibility(View.VISIBLE);
                            setExhibitionsMuseum(new ArrayList<>());
                        } else {
                            emptyTV.setVisibility(View.INVISIBLE);
                            setExhibitionsMuseum(model);
                        }
                    }
                });
    }

    @Override
    public void deletePosition(int position, Integer id) {
        viewModel.getIsLoadingDeleteExhibition().observe(this, isLoading -> {
            if (isLoading) progressBar.setVisibility(View.VISIBLE);
            else progressBar.setVisibility(View.GONE);
        });
        viewModel.deleteExhibitionLiveData(id)
                .observe(this, model -> {
                    viewModel.getIsLoadingDeleteExhibition().postValue(false);
                    if (model == null) {
                        Toast.makeText(getContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
                    } else {
                        getExhibitionsMuseum();
                        Toast.makeText(getContext(), model.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
