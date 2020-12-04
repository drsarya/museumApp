package com.example.museums.view.fragments.museum.museumExhibitions;


import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.API.models.ExhibitionWithMuseumName;
import com.example.museums.R;
import com.example.museums.view.services.oop.IDeletePosition;
import com.example.museums.view.services.recyclerViews.EditExhibitionRecyclerAdapter;

import java.util.List;


public class MuseumExhibitions extends Fragment implements IDeletePosition {
    public ProgressBar progressBar;
    private RecyclerView recyclerView;
    private EditExhibitionRecyclerAdapter adapter = new EditExhibitionRecyclerAdapter(this);
    public static final String LOGIN_KEY_USER = "login_key";
    public String login;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_museum_exhibitions, container, false);
        getArgumentsFromBundle();


        initViews(rootView);
        System.out.println(login);
        //получить инфу о  выставке
        // установить в адаптере
        //при нажатии на редактирование и удаление экспоната - открыть edit exhibit
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

    private QueryDeleteExhibition queryDeleteExhibition;

    private void initViews(View rootView) {
        progressBar = rootView.findViewById(R.id.museum_exhibitions_progress_bar);
        recyclerView = rootView.findViewById(R.id.recycler_view_museum_exhibitions);
        QueryMuseumExhibitions queryMuseumExhibitions = new QueryMuseumExhibitions(this);
        queryMuseumExhibitions.getQuery(login);
        queryDeleteExhibition = new QueryDeleteExhibition(this);
        recyclerView.setAdapter(adapter);
    }

    public void updateAll(List<ExhibitionWithMuseumName> exhibitWithAuthors) {
        System.out.println(exhibitWithAuthors.size());
        adapter.submitList(exhibitWithAuthors);
    }

    @Override
    public void deletePosition(int position, Integer id) {
        queryDeleteExhibition.getQuery(id);
    }
}
