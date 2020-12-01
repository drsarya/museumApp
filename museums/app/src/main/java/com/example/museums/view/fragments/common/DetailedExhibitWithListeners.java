package com.example.museums.view.fragments.common;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
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
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.museums.R;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerChangeColorLike;
import com.example.museums.view.services.Listeners.onTouchListeners.OnToucLlistenerScrollViewSwipeLeftRightBack;

public class DetailedExhibitWithListeners extends Fragment {
    private ScrollView view;
    private LinearLayout ll;
    private ImageButton like;
    public static final String IMAGE_KEY = "image_key";
    public static final String NAME_KEY = "name_key";
    public static final String AUTHOR_KEY = "author_key";
    public static final String DATE_OF_CREATE = "date_key";
    public static final String DESCRIPTION_KEY = "description_key";
    public static final String ID_EXHIBIT_KEY = "id_key";
    private String name, author, date, description;
    private int idExhibit;
    private Bitmap image;
    private ImageView mainImageImageView;
    private TextView nameTextView, authorTextView, dateTextView, descriptionTextView;
    private boolean state = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.setRetainInstance(true);
        View rootView =
                inflater.inflate(R.layout.fragment_detailed_exhibit, container, false);

        initView(rootView);
        getArgumentsFromBundle();
        return rootView;
    }

    public DetailedExhibitWithListeners newInstance(int id, Parcelable image, String name, String author, String date, String description) {
        final DetailedExhibitWithListeners myFragment = new DetailedExhibitWithListeners();
        final Bundle args = new Bundle();
        args.putString(NAME_KEY, name);
        args.putString(DATE_OF_CREATE, date);
        args.putString(AUTHOR_KEY, author);
        args.putString(DESCRIPTION_KEY, description);
        args.putInt(ID_EXHIBIT_KEY, id);
        args.putParcelable(IMAGE_KEY, image);
        myFragment.setArguments(args);
        return myFragment;
    }

    private void getArgumentsFromBundle() {
        if (getArguments() != null) {
            name = getArguments().getString(NAME_KEY);
            author = getArguments().getString(AUTHOR_KEY);
            date = getArguments().getString(DATE_OF_CREATE);
            description = getArguments().getString(DESCRIPTION_KEY);
            image = (Bitmap) getArguments().getParcelable(IMAGE_KEY);
            idExhibit = getArguments().getInt(ID_EXHIBIT_KEY);
            mainImageImageView.setImageBitmap(image);
            authorTextView.setText(author);
            dateTextView.setText(date);
            descriptionTextView.setText(description);
            nameTextView.setText(name);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        super.setRetainInstance(true);
        state = true;
        setListeners();
    }

    private void initView(View rootView) {
        ll = (LinearLayout) rootView.findViewById(R.id.detailed_exhibit_option_pane_lin_lay);
        view = (ScrollView) rootView.findViewById(R.id.detailed_exhibit_description_scroll_view);
        like = (ImageButton) rootView.findViewById(R.id.detailed_exhibit_like_btn);
        mainImageImageView = (ImageView) rootView.findViewById(R.id.detailed_exhibit_main_image_image_view);
        nameTextView = (TextView) rootView.findViewById(R.id.detailed_exhibit_name_of_paint_text_view);
        authorTextView = (TextView) rootView.findViewById(R.id.detailed_exhibit_author_text_view);
        dateTextView = (TextView) rootView.findViewById(R.id.detailed_exhibit_date_of_create_text_view);
        descriptionTextView = (TextView) rootView.findViewById(R.id.detailed_exhibit_description_text_view);
    }


    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setListeners() {

        like.setOnClickListener(new ClickListenerChangeColorLike(state, like, getActivity()));
        view.setOnTouchListener(new OnToucLlistenerScrollViewSwipeLeftRightBack(getActivity(), true, ll));
    }

}
