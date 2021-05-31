package com.example.museums.view.fragments.user.likedExhibitions;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.API.models.exhibition.ExistingExhibition;
import com.example.museums.R;
import com.example.museums.view.services.recyclerViews.ExhibitionsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.museums.view.ConstantKeys.ID_USER_KEY;

public class LikedExhibitions extends Fragment {

    private RecyclerView recyclerView;
    private ExhibitionsRecyclerViewAdapter mAdapter = new ExhibitionsRecyclerViewAdapter();
    private Integer userId;
    private TextView empty;
    private LikedExhibitionsViewModel viewModel;

    public static LikedExhibitions newInstance(Integer idUser) {
        final LikedExhibitions myFragment = new LikedExhibitions();
        final Bundle args = new Bundle(1);
        args.putInt(ID_USER_KEY, idUser);
        myFragment.setArguments(args);
        return myFragment;
    }

    private void getArgumentsFromBundle() {
        if (getArguments() != null) {
            userId = getArguments().getInt(ID_USER_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_liked_exhibitions, container, false);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        getArgumentsFromBundle();
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view_liked_exhibitions);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setUserId(userId);
        empty = getActivity().findViewById(R.id.liked_exhibitions_empty_tv);
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
                    if (model.size() > 0) {
                        empty.setVisibility(View.INVISIBLE);
                        refreshAllList(model);
                    } else {
                        mAdapter.submitList(new ArrayList<>());
                        empty.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    public void refreshAllList(List<ExistingExhibition> exhibitModels) {
        mAdapter.submitList(exhibitModels);
    }
}
