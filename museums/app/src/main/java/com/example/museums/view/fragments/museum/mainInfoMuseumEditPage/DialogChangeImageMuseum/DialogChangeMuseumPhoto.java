package com.example.museums.view.fragments.museum.mainInfoMuseumEditPage.DialogChangeImageMuseum;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.museums.API.services.BitmapConverter;
import com.example.museums.R;
import com.example.museums.view.fragments.admin.allMuseums.AllMuseums;
import com.example.museums.view.fragments.museum.mainInfoMuseumEditPage.MainInfoMuseumPageEdit.MainInfoMuseumPageEdit;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import lombok.SneakyThrows;

public class DialogChangeMuseumPhoto extends DialogFragment {

    private ImageView imageView, closeDialog;
    private Button buttonUpdate;
    public ProgressBar progressBar;
    private Bitmap bitmap, lastImage;

    private Integer museumId;
    private File f;

    static final String ID_MUSEUM_KEY = "login_key";
    static final String IMAGE_SOURCE_KEY = "image_source_key";
    static final int GALLERY_REQUEST = 1;

    private ChangeMuseumImageViewModel viewModel;

    public DialogChangeMuseumPhoto newInstance(final Parcelable image, final Integer idMuseum) {
        final DialogChangeMuseumPhoto myFragment = new DialogChangeMuseumPhoto();
        final Bundle args = new Bundle(2);
        args.putParcelable(IMAGE_SOURCE_KEY, image);
        args.putInt(ID_MUSEUM_KEY, idMuseum);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.dialog_change_photo_museum, container, false);
        Bundle arguments = getArguments();
        if (arguments != null) {
            museumId = arguments.getInt(ID_MUSEUM_KEY);
            lastImage = (Bitmap) arguments.getParcelable(IMAGE_SOURCE_KEY);
        }

        initViews(rootView);
        setListeners();
        return rootView;
    }


    private void initViews(View rootView) {
        closeDialog = rootView.findViewById(R.id.dialog_change_image_museum_close_image_view);
        imageView = rootView.findViewById(R.id.dialog_change_photo_museum_photo_im_view);
        buttonUpdate = rootView.findViewById(R.id.dialog_change_photo_museum_update_btn);
        progressBar = rootView.findViewById(R.id.dialog_change_photo_museum_progress_bar);
        if (lastImage != null) {
            imageView.setImageBitmap(lastImage);
            getArguments().clear();
        }
    }


    @SneakyThrows
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case GALLERY_REQUEST:
                if (resultCode == getActivity().RESULT_OK) {
                    Uri selectedImage = data.getData();
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                    imageView.setImageBitmap(bitmap);
                    f = BitmapConverter.convertBitmapToFile(bitmap, getContext());
                }
        }
    }


    private void updateImage() {
        viewModel = ViewModelProviders.of(this).get(ChangeMuseumImageViewModel.class);
        viewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading) progressBar.setVisibility(View.VISIBLE);
            else progressBar.setVisibility(View.GONE);
        });
        viewModel.getUpdateLiveData(f, museumId)
                .observe(this, model -> {
                    viewModel.getIsLoading().postValue(false);
                    if (model == null) {
                        Toast.makeText(getContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
                    } else {
                        ((MainInfoMuseumPageEdit) getParentFragment()).getMuseumInfo();
                        Toast.makeText(getContext(), model.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setListeners() {
        imageView.setOnClickListener(v -> {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
        });
        buttonUpdate.setOnClickListener(v -> {
            if (bitmap != null && museumId != null) {
                updateImage();
            } else {
                Toast.makeText(getContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
            }
        });
        closeDialog.setOnClickListener(v ->
                dismiss());
    }
}
