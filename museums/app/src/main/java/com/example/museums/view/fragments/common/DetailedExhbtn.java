package com.example.museums.view.fragments.common;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.museums.R;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerChangeColorLike;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHideDescription;
import com.example.museums.view.services.Listeners.onTouchListeners.OnToucLlistenerScrollViewSwipeLeftRightBack;
import com.example.museums.view.services.MethodsWithFragment;
import com.example.museums.API.models.Exhibit;

import java.util.ArrayList;
import java.util.List;

public class DetailedExhbtn extends Fragment {
    private ImageButton like;
    private Button museumInfo;
    private Button allExhibits;

    private Button exhbtnDescriptionBtn;
    private TextView exhbtnDescriptionTextView;
    private MethodsWithFragment mth = new MethodsWithFragment();
    private ScrollView scrollView;
    private boolean state = true;
    private boolean stateDescription = true;

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        like = (ImageButton) getActivity().findViewById(R.id.detailed_exhibition_like_btn);
        museumInfo = (Button) getActivity().findViewById(R.id.detailed_exhibition_btn_museum_info);
        exhbtnDescriptionBtn = (Button) getActivity().findViewById(R.id.detailed_exhbtn_descriprion_btn);
        exhbtnDescriptionTextView = (TextView) getActivity().findViewById(R.id.detailed_exhbtn_descriprion_text_view);
        scrollView = (ScrollView) getActivity().findViewById(R.id.detailed_exhbtn_scroll_view);
        allExhibits = (Button) getActivity().findViewById(R.id.detailed_exhbtn_all_exhbts_btn);
        setListeners();
        scrollView.setOnTouchListener(new OnToucLlistenerScrollViewSwipeLeftRightBack(getActivity(), false));

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_detailed_exhibition, container, false);
        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)

     private void setListeners() {
        like.setOnClickListener(new ClickListenerChangeColorLike(state, like, getActivity()));
        museumInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment myFragment = new MainInfoMuseum();
                mth.replaceFragment(myFragment, v, (AppCompatActivity) v.getContext());
            }
        });
        exhbtnDescriptionBtn.setOnClickListener(
                new ClickListenerHideDescription(exhbtnDescriptionTextView )
        );
        allExhibits.setOnClickListener(v -> {

            List<Exhibit> lisr = new ArrayList<>();
            lisr.add(new Exhibit());
            lisr.add(new Exhibit());
            lisr.add(new Exhibit());
            lisr.add(new Exhibit());
            lisr.add(new Exhibit());

            final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ExhibitViewPager exhibitViewPager = new ExhibitViewPager(lisr);
            mth.replaceFragment(exhibitViewPager, v, (AppCompatActivity) v.getContext());

        });
    }
}
