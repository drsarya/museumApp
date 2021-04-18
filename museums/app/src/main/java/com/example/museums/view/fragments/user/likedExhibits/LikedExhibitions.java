package com.example.museums.view.fragments.user.likedExhibits;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.API.models.exhibition.ExistingExhibition;
import com.example.museums.R;
import com.example.museums.view.fragments.user.likedExhibitions.LikedExhibitsViewModel;
import com.example.museums.view.services.recyclerViews.ExhibitionsRecyclerViewAdapter;

import java.util.List;

public class LikedExhibitions extends Fragment {

    private RecyclerView recyclerView;
    private ExhibitionsRecyclerViewAdapter mAdapter = new ExhibitionsRecyclerViewAdapter();
    private Integer userId;
    public static final String LOGIN_KEY_USER = "login_key";

    public static LikedExhibitions newInstance(Integer idUser) {
        final LikedExhibitions myFragment = new LikedExhibitions();
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
                inflater.inflate(R.layout.fragment_liked_exhibitions, container, false);
        return rootView;
    }

    private LikedExhibitionsViewModel viewModel;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        getArgumentsFromBundle();
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view_liked_exhibitons);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setUserId(userId);
        viewModel = ViewModelProviders.of(this).get(LikedExhibitionsViewModel.class);
        getLikedExhibitionsByUserId();

    }

    private void getLikedExhibitionsByUserId() {
        viewModel.getExhibitionsLiveData(userId).observe(this, model -> {
            viewModel.getIsLoadingExhibits().postValue(false);
            if (model == null) {
                Toast.makeText(getContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
            } else {
                if (model != null) {
                    refreshAllList(model);
                }
            }
        });
    }

    public void refreshAllList(List<ExistingExhibition> exhibitModels) {
        mAdapter.submitList(exhibitModels);
    }
}
