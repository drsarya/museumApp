package com.example.museums.view.fragments.museum.MainInfoMuseumEditPage.DialogChangeImageMuseum;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.museums.R;

import java.io.IOException;

public class DialogChangeMuseumPhoto extends DialogFragment {

    private ImageView imageView;
    private Button buttonUpdate;
    public ProgressBar progressBar;
    static final int GALLERY_REQUEST = 1;
    private String lastimage;
    private String login;
    private ImageView closeDialog;
    static final String LOGIN_KEY = "login_key";
    static final String IMAGE_SOURCE_KEY = "image_source_key";

    public DialogChangeMuseumPhoto() {

    }

    public DialogChangeMuseumPhoto newInstance(final String image, final String login) {
        final DialogChangeMuseumPhoto myFragment = new DialogChangeMuseumPhoto();
        final Bundle args = new Bundle(2);
        args.putString(IMAGE_SOURCE_KEY, image);
        args.putString(LOGIN_KEY, login);
        myFragment.setArguments(args);
        return myFragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.dialog_change_photo_museum, container, false);
        initViews(rootView);
        setListeners();
        Bundle arguments = getArguments();
        if (arguments != null) {
            login = arguments.getString(LOGIN_KEY);
            lastimage = arguments.getString(IMAGE_SOURCE_KEY);
            System.out.println(lastimage);

        }
        return rootView;
    }

    private void initViews(View rootView) {
        closeDialog = rootView.findViewById(R.id.dialog_change_image_museum_close_image_view);
        imageView = rootView.findViewById(R.id.dialog_change_photo_museum_photo_im_view);
        buttonUpdate = rootView.findViewById(R.id.dialog_change_photo_museum_update_btn);
        progressBar = rootView.findViewById(R.id.dialog_change_photo_museum_progress_bar);
    }

    Bitmap bitmap;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode) {
            case GALLERY_REQUEST:
                if (resultCode == getActivity().RESULT_OK) {
                    Uri selectedImage = data.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imageView.setImageBitmap(bitmap);
                }
        }
    }


    private void setListeners() {
        imageView.setOnClickListener(v -> {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
        });
        buttonUpdate.setOnClickListener(v -> {
            if (bitmap != null && login != null) {
                QueryChangeMuseumImage queryChangeMuseumImage = new QueryChangeMuseumImage(this);
                queryChangeMuseumImage.getQuery(login, bitmap);
            } else {
                Toast.makeText(getContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
            }
        });
        closeDialog.setOnClickListener(v ->
                dismiss());
    }
}
