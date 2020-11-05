package com.example.museums.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.museums.R;
import com.example.museums.services.Listeners.ClickListenerChangeColorLike;
import com.example.museums.services.Listeners.ClickListenerTurnBack;
import com.example.museums.services.Listeners.ScrollChangeListenerDetailedExhbt;

public class DetailedExhbtn extends Fragment {
    ImageView like;
    ImageView close;
    boolean state;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        like = (ImageView) getActivity().findViewById(R.id.detailed_exhibition_like_image_view);
        close = (ImageView) getActivity().findViewById(R.id.detailed_exhibition_close_image_view);
        setListeners();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_detailed_exhibition, container, false);
        return rootView;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setListeners() {
        like.setOnClickListener(new ClickListenerChangeColorLike(state, like, getActivity(), false));
        close.setOnClickListener(new ClickListenerTurnBack(getActivity()));
    }
}
