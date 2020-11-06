package com.example.museums.fragments;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.museums.R;
import com.example.museums.activities.MuseumTab;
import com.example.museums.fragments.museum.MainInfoMuseum;
import com.example.museums.fragments.museum.MainInfoMuseumPageEdit;
import com.example.museums.services.Listeners.ClickListenerChangeColorLike;
import com.example.museums.services.Listeners.ClickListenerHideDescription;
import com.example.museums.services.Listeners.ClickListenerTurnBack;
import com.example.museums.services.Listeners.OnToucLlistenerScrollViewSwipeLeftRight;
import com.example.museums.services.MethodsWithFragment;

public class DetailedExhbtn extends Fragment {
    private ImageButton like;
    private Button museumInfo;
    private Button exhbtnDescriptionBtn;
    private TextView exhbtnDescriptionTextView;
    private MethodsWithFragment mth = new MethodsWithFragment();
    private ImageView close;
    private ScrollView scrollView;
    private boolean state = true;
    private boolean stateDescription = true;
    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        like = (ImageButton) getActivity().findViewById(R.id.detailed_exhibition_like_btn);
        close = (ImageView) getActivity().findViewById(R.id.detailed_exhibition_close_image_view);
        museumInfo = (Button) getActivity().findViewById(R.id.detailed_exhibition_btn_museum_info);
        exhbtnDescriptionBtn = (Button) getActivity().findViewById(R.id.detailed_exhbtn_descriprion_btn);
        exhbtnDescriptionTextView = (TextView) getActivity().findViewById(R.id.detailed_exhbtn_descriprion_text_view);
        scrollView = (ScrollView) getActivity().findViewById(R.id.detailed_exhbtn_scroll_view);

        setListeners();
        scrollView.setOnTouchListener(new OnToucLlistenerScrollViewSwipeLeftRight(getActivity(), false));

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
        like.setOnClickListener(new ClickListenerChangeColorLike(state, like, getActivity()));
        close.setOnClickListener(new ClickListenerTurnBack(getActivity()));
        museumInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment myFragment = new MainInfoMuseum();
                MuseumTab activity = (MuseumTab) v.getContext();
                mth.replaceFragment(myFragment, v, activity, R.id.container_tab_museum);
            }
        });
        exhbtnDescriptionBtn.setOnClickListener(
                new ClickListenerHideDescription(exhbtnDescriptionTextView, stateDescription)
        );
    }
}
