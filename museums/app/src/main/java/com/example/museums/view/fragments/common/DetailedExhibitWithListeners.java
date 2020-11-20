package com.example.museums.view.fragments.common;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.museums.R;
import com.example.museums.view.services.Listeners.ClickListenerChangeColorLike;
import com.example.museums.view.services.Listeners.OnToucLlistenerScrollViewSwipeLeftRightBack;

public class DetailedExhibitWithListeners extends Fragment {
    private ScrollView view;
    private LinearLayout ll;
    private ImageButton like;

    private boolean state = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.setRetainInstance(true);
        View rootView =
                inflater.inflate(R.layout.fragment_detailed_exhibit, container, false);
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
        like = (ImageButton) getActivity().findViewById(R.id.detailed_exhibit_like_btn);
        setListeners();
    }


    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setListeners() {

        like.setOnClickListener(new ClickListenerChangeColorLike(state, like, getActivity()));
        view.setOnTouchListener(new OnToucLlistenerScrollViewSwipeLeftRightBack(getActivity(), true, ll));
    }

}
