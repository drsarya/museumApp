package com.example.museums.fragments.museum;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.museums.R;
import com.example.museums.services.Listeners.ClickListenerHideDescription;
import com.example.museums.services.Listeners.OnToucLlistenerScrollViewSwipeLeftRightBack;

public class MainInfoMuseum extends Fragment {

    private Button museumDescriptionBtn;
    private TextView museumDescriptionTextView;
    private ScrollView scrollView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        //return super.onCreateView(inflater container, savedInstanceState);
        View rootView =
                inflater.inflate(R.layout.frgment_main_info_museum, container, false);
        return rootView;
    }

    private boolean state = true;
    private Float downx = null;
    private Float downy = null;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        museumDescriptionBtn = (Button) getActivity().findViewById(R.id.main_info_museum_btn_description_btn);
        museumDescriptionTextView = (TextView) getActivity().findViewById(R.id.main_info_museum_description_text_view);
        scrollView = (ScrollView) getActivity().findViewById(R.id.main_info_scroll_view);
        museumDescriptionBtn.setOnClickListener(
                new ClickListenerHideDescription(museumDescriptionTextView, state)
        );


        scrollView.setOnTouchListener(new OnToucLlistenerScrollViewSwipeLeftRightBack(getActivity(), false));
    }
}
