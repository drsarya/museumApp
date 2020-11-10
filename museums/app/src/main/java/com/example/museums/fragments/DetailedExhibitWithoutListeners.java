package com.example.museums.fragments;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.museums.R;
import com.example.museums.services.Listeners.ClickListenerChangeColorLike;
import com.example.museums.services.Listeners.OnToucListenerScrollViewSwipeLeftRight;

public class DetailedExhibitWithoutListeners extends Fragment {
    public ScrollView view;
    public LinearLayout ll;
    public ImageButton like;
    public TextView name;
    public ImageButton close;

    private boolean state = false;
    private ScrollView scrollView;

    public static final String PAINT_DESCRIPTIONS = "paint_descriptions";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.setRetainInstance(true);

        View rootView =
                inflater.inflate(R.layout.fragment_detailed_exhibit_view_page, container, false);
        Bundle arguments = getArguments();
        if (arguments != null) {
            String catName = arguments.getString(PAINT_DESCRIPTIONS);
            displayValues(rootView, catName);

        }

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

        name = (TextView) getActivity().findViewById(R.id.detailed_exhibit_name_of_paint);

    }

    @SuppressLint("ClickableViewAccessibility")
    private void displayValues(View v, String s
    ) {
        name = (TextView) v.findViewById(R.id.detailed_exhibit_name_of_paint);
        like = (ImageButton) v.findViewById(R.id.detailed_exhibit_like_btn);
        like.setOnClickListener(new ClickListenerChangeColorLike(state, like, getActivity()));
        view = (ScrollView) v.findViewById(R.id.detailed_exhibit_description_scroll_view);
        ll = (LinearLayout) v.findViewById(R.id.detailed_exhibit_option_pane_lin_lay);
        close = (ImageButton) v.findViewById(R.id.detailed_exhb_view_pager);
        view.setOnTouchListener(new OnToucListenerScrollViewSwipeLeftRight(getActivity(), ll, true));
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        name.setText(s);
    }

}
