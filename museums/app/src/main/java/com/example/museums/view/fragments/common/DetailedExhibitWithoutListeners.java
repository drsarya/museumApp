package com.example.museums.view.fragments.common;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.museums.R;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerChangeColorLike;
import com.example.museums.view.services.Listeners.onTouchListeners.OnToucListenerScrollViewSwipeLeftRight;

public class DetailedExhibitWithoutListeners extends Fragment {
    public ScrollView view;
    public LinearLayout ll;
    public ImageButton likeImageButton;
    public TextView nameTextView, descriptionTextView, authorTextView, dateTextView;
    public ImageButton closeImageButton;

    private boolean state = false;
    private ScrollView scrollView;
    private ImageView imageView;
    public static final String IMAGE_KEY = "image_key";
    public static final String NAME_KEY = "name_key";
    public static final String DESCRIPTION_KEY = "description_key";
    public static final String AUTHOR_KEY = "author_key";
    public static final String DATE_OF_CREATE_KEY = "date_key";

    public DetailedExhibitWithoutListeners newInstance(  Parcelable image, String name, String author, String date, String description) {
        final DetailedExhibitWithoutListeners myFragment = new DetailedExhibitWithoutListeners();
        final Bundle args = new Bundle();
        args.putString(NAME_KEY, name);
        args.putString(DATE_OF_CREATE_KEY, date);
        args.putString(AUTHOR_KEY, author);
        args.putString(DESCRIPTION_KEY, description);
        args.putParcelable(IMAGE_KEY, image);
        myFragment.setArguments(args);
        return myFragment;
    }

    private String name, date, author, description;
    private Bitmap image;

    private void getArgumentsFromBundle() {
        if (getArguments() != null) {
            name = getArguments().getString(NAME_KEY);
            System.out.println(name);
            date = getArguments().getString(DATE_OF_CREATE_KEY);
            author = getArguments().getString(AUTHOR_KEY);
            description = getArguments().getString(DESCRIPTION_KEY);
            image = (Bitmap) getArguments().getParcelable(IMAGE_KEY);
            getArguments().clear();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.setRetainInstance(true);

        View rootView =
                inflater.inflate(R.layout.fragment_detailed_exhibit_view_page, container, false);

        initViews(rootView);
        setListeners();
        getArgumentsFromBundle();
        setData();
        return rootView;
    }

    private void setData() {
        imageView.setImageBitmap(image);
        authorTextView.setText(author);
        dateTextView.setText(date);
        descriptionTextView.setText(description);
        nameTextView.setText(name);
    }

    private void initViews(View rootView) {
        ll = rootView.findViewById(R.id.detailed_exhibit_option_pane_lin_lay);
        view = rootView.findViewById(R.id.detailed_exhibit_description_scroll_view);
        likeImageButton = rootView.findViewById(R.id.detailed_exhibit_like_btn);
        nameTextView = rootView.findViewById(R.id.detailed_exhibit_name_of_paint_text_view);
        closeImageButton = rootView.findViewById(R.id.detailed_exhb_view_pager);
        imageView = rootView.findViewById(R.id.detailed_exhibit_main_image_image_view);
        dateTextView = rootView.findViewById(R.id.detailed_exhibit_view_page_date_text_view);
        authorTextView = rootView.findViewById(R.id.detailed_exhibit_view_page_author_text_view);
        descriptionTextView = rootView.findViewById(R.id.detailed_exhibit_view_page_description);
    }

    private void setListeners() {
        likeImageButton.setOnClickListener(new ClickListenerChangeColorLike(state, likeImageButton, getActivity()));
        view.setOnTouchListener(new OnToucListenerScrollViewSwipeLeftRight(getActivity(), ll, true));
        closeImageButton.setOnClickListener(v1 -> getActivity().onBackPressed());


    }


}
