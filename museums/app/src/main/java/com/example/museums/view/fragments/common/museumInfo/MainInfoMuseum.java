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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.museums.API.models.museum.ExistingMuseum;
import com.example.museums.R;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHideDescription;
import com.example.museums.view.services.Listeners.onTouchListeners.OnTouchListenerScrollViewSwipeLeftRightBack;

import static com.example.museums.view.ConstantKeys.ID_MUSEUM_KEY;

public class MainInfoMuseum extends Fragment {

    private Button museumDescriptionBtn;
    private TextView museumDescriptionTextView, nameTextView, addressTextView;
    private ImageView imageView;
    private ScrollView scrollView;
    private MainInfoMuseumViewModel viewModel;

    public MainInfoMuseum newInstance(int id) {
        final MainInfoMuseum myFragment = new MainInfoMuseum();
        final Bundle args = new Bundle();
        args.putInt(ID_MUSEUM_KEY, id);
        myFragment.setArguments(args);
        return myFragment;
    }

    private int idMuseum;

    private void getArgumentsFromBundle() {
        if (getArguments() != null) {
            idMuseum = getArguments().getInt(ID_MUSEUM_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_main_info_museum, container, false);
        initViews(rootView);
        getArgumentsFromBundle();
        setListeners();
        getMuseumInfo();
        return rootView;
    }

    private void getMuseumInfo() {
        viewModel.getLiveDataMuseumInfo(idMuseum)
                .observe(this, museum -> {
                    if (museum == null) {
                        Toast.makeText(getContext(), "Ошибка загрузки данных", Toast.LENGTH_SHORT).show();
                    } else {
                        setData(museum);
                    }
                });
    }

    private void initViews(View rootView) {
        museumDescriptionBtn = rootView.findViewById(R.id.main_info_museum_btn_description_btn);
        museumDescriptionTextView = rootView.findViewById(R.id.main_info_museum_description_text_view);
        scrollView = rootView.findViewById(R.id.main_info_scroll_view);
        nameTextView = rootView.findViewById(R.id.main_info_museum_name_text_view);
        addressTextView = rootView.findViewById(R.id.main_info_museum_address_text_view);
        imageView = rootView.findViewById(R.id.main_info_museum_image_image_view);
        viewModel = ViewModelProviders.of(this).get(MainInfoMuseumViewModel.class);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setListeners() {
        museumDescriptionBtn.setOnClickListener(
                new ClickListenerHideDescription(museumDescriptionTextView)
        );
        scrollView.setOnTouchListener(new OnTouchListenerScrollViewSwipeLeftRightBack(getActivity(), false));

    }

    public void setData(ExistingMuseum museum) {
        museumDescriptionTextView.setText(museum.getDescription());
        nameTextView.setText(museum.getName());
        addressTextView.setText(museum.getAddress());
        Glide.with(getContext())
                .load(museum.getImageUrl())
                .into(imageView);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }
}
