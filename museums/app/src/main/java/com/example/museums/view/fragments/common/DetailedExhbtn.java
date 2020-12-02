package com.example.museums.view.fragments.common;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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
    private TextView nameTextView, dateTextView;
    private ImageView imageView;

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListeners();
    }

private Bitmap bitmap ;
    public static final String IMAGE_KEY = "image_key";
    public static final String NAME_KEY = "name_key";
    public static final String DATE_KEY = "date_key";
    public static final String DESCRIPTION_KEY = "description_key";
    public static final String ID_EXHIBIT_KEY = "id_key";

    public DetailedExhbtn newInstance(int id, Parcelable image, String name, String date, String description) {
        final DetailedExhbtn myFragment = new DetailedExhbtn();
        final Bundle args = new Bundle();
        args.putString(NAME_KEY, name);
        args.putString(DATE_KEY, date);
        args.putString(DESCRIPTION_KEY, description);
        args.putInt(ID_EXHIBIT_KEY, id);
        args.putParcelable(IMAGE_KEY, image);
        myFragment.setArguments(args);
        return myFragment;
    }

    private int idExhibition;

    private void getArgumentsFromBundle() {
        if (getArguments() != null) {
            nameTextView.setText(getArguments().getString(NAME_KEY));
            dateTextView.setText(getArguments().getString(DATE_KEY));
            exhbtnDescriptionTextView.setText(getArguments().getString(DESCRIPTION_KEY));
            idExhibition = getArguments().getInt(ID_EXHIBIT_KEY);
            bitmap = (Bitmap) getArguments().getParcelable(IMAGE_KEY);
            imageView.setImageBitmap(bitmap);
          //  getArguments().clear();
        }
    }

    private void initViews(View rootView) {
        like = rootView.findViewById(R.id.detailed_exhibition_like_btn);
        museumInfo = rootView.findViewById(R.id.detailed_exhibition_btn_museum_info);
        exhbtnDescriptionBtn = rootView.findViewById(R.id.detailed_exhbtn_descriprion_btn);
        exhbtnDescriptionTextView = rootView.findViewById(R.id.detailed_exhbtn_descriprion_text_view);
        scrollView = rootView.findViewById(R.id.detailed_exhbtn_scroll_view);
        allExhibits = rootView.findViewById(R.id.detailed_exhbtn_all_exhbts_btn);
        nameTextView = rootView.findViewById(R.id.detailed_exhbtn_name_text_view);
        dateTextView = rootView.findViewById(R.id.detailed_exhbtn_date_text_view);
        imageView = rootView.findViewById(R.id.detailed_exhbtn_image_image_view);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_detailed_exhibition, container, false);
        initViews(rootView);
        getArgumentsFromBundle();
        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)

    private void setListeners() {
        like.setOnClickListener(new ClickListenerChangeColorLike(state, like, getActivity()));
        museumInfo.setOnClickListener(v -> {
            Fragment myFragment = new MainInfoMuseum();
            mth.replaceFragment(myFragment,  (AppCompatActivity) v.getContext());
        });
        exhbtnDescriptionBtn.setOnClickListener(
                new ClickListenerHideDescription(exhbtnDescriptionTextView)
        );
        allExhibits.setOnClickListener(v -> {
            List<Exhibit> lisr = new ArrayList<>();
            lisr.add(new Exhibit());
            lisr.add(new Exhibit());
            lisr.add(new Exhibit());
            lisr.add(new Exhibit());
            lisr.add(new Exhibit());
            ExhibitViewPager exhibitViewPager = new ExhibitViewPager(lisr);
            mth.replaceFragment(exhibitViewPager,   (AppCompatActivity) v.getContext());

        });
        scrollView.setOnTouchListener(new OnToucLlistenerScrollViewSwipeLeftRightBack(getActivity(), false));

    }
}
