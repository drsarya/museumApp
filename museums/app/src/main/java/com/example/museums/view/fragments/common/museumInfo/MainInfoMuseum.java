package com.example.museums.view.fragments.common.museumInfo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.museums.API.models.Museum;
import com.example.museums.R;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHideDescription;
import com.example.museums.view.services.Listeners.onTouchListeners.OnToucLlistenerScrollViewSwipeLeftRightBack;

public class MainInfoMuseum extends Fragment {

    private static final String ID_MUSEUM = "id_museum";
    private Button museumDescriptionBtn;
    private TextView museumDescriptionTextView, nameTextView, addressTextView;
    private ImageView imageView;
    private ScrollView scrollView;

    public MainInfoMuseum newInstance(int id) {
        final MainInfoMuseum myFragment = new MainInfoMuseum();
        final Bundle args = new Bundle();
        args.putInt(ID_MUSEUM, id);
        myFragment.setArguments(args);
        return myFragment;
    }

    private int idMuseum;

    private void getArgumentsFromBundle() {
        if (getArguments() != null) {
            idMuseum = getArguments().getInt(ID_MUSEUM);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.frgment_main_info_museum, container, false);
        initViews(rootView);
        getArgumentsFromBundle();
        QueryMuseumInfo q = new QueryMuseumInfo(this);
        q.getQuery(idMuseum);
        setListeners();
        return rootView;
    }

    private void initViews(View rootView) {
        museumDescriptionBtn = rootView.findViewById(R.id.main_info_museum_btn_description_btn);
        museumDescriptionTextView = rootView.findViewById(R.id.main_info_museum_description_text_view);
        scrollView = rootView.findViewById(R.id.main_info_scroll_view);
        nameTextView = rootView.findViewById(R.id.main_info_museum_name_text_view);
        addressTextView = rootView.findViewById(R.id.main_info_museum_address_text_view);
        imageView = rootView.findViewById(R.id.main_info_museum_image_image_view);

    }

    @SuppressLint("ClickableViewAccessibility")
    private void setListeners() {
        museumDescriptionBtn.setOnClickListener(
                new ClickListenerHideDescription(museumDescriptionTextView)
        );
        scrollView.setOnTouchListener(new OnToucLlistenerScrollViewSwipeLeftRightBack(getActivity(), false));

    }

    public void setData(Museum museum) {
        museumDescriptionTextView.setText(museum.description);
        nameTextView.setText(museum.nameMuseum);
        addressTextView.setText(museum.address);
        imageView.setImageBitmap(museum.image);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }
}
