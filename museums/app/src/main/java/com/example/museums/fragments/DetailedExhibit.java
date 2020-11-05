package com.example.museums.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.museums.R;
import com.example.museums.services.Listeners.ClickListenerChangeColorLike;
import com.example.museums.services.Listeners.ClickListenerTurnBack;
import com.example.museums.services.Listeners.ScrollChangeListenerDetailedExhbt;

public class DetailedExhibit extends Fragment {
    ScrollView view;
    LinearLayout ll;
    ImageView like;
    ImageView close;
    boolean state;
    //GestureOverlayView mainImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.setRetainInstance(true);
        View rootView =
                inflater.inflate(R.layout.fragment_detailed_exhibit_museum, container, false);
        return rootView;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        super.setRetainInstance(true);
        state = true;
        ll = (LinearLayout) getActivity().findViewById(R.id.detailed_exhibit_option_pane_lin_lay);
        view = (ScrollView) getActivity().findViewById(R.id.detailed_exhibit_description_scroll_view);
        like = (ImageView) getActivity().findViewById(R.id.detailed_exhibit_like_image_view);
        close = (ImageView) getActivity().findViewById(R.id.detailed_exhibit_close_image_view);

        //mainImage = (GestureOverlayView) getActivity().findViewById(R.id.detailed_exhibit_image_gesture_view);

        setListeners();
    }




    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setListeners() {
        like.setOnClickListener(new ClickListenerChangeColorLike(state, like, getActivity(), true));
        close.setOnClickListener(new ClickListenerTurnBack(getActivity()));
        view.setOnScrollChangeListener(new ScrollChangeListenerDetailedExhbt(ll));
    }

}
