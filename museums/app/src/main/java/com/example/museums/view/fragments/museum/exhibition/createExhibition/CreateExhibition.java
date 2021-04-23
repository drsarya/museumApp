package com.example.museums.view.fragments.museum.exhibition.createExhibition;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.museums.API.services.BitmapConverter;
import com.example.museums.R;
import com.example.museums.view.services.CacheManager;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHideDescription;

import java.io.File;
import java.io.IOException;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

import static com.example.museums.view.ConstantKeys.ID_MUSEUM_KEY;

public class CreateExhibition extends Fragment {

    private static Integer museumId;
    private Bitmap bitmap;
    private static CheckBox onlineCheckBox;
    private TextFieldBoxes dateOfStartTFB, dateOfEndTFB, nameTFB, descriptionTFB;
    private EditText dateOfStartET, dateOfEndET, nameET, descriptionET;
    private static ImageView currImageImageView;
    private TextView chooseImageTextView;
    private Button hideDescriptionBtn, createExhibitionBtn;
    public ProgressBar progressBar;
    static final int GALLERY_REQUEST = 1;
    private CacheManager cacheManager = new CacheManager();

    private File file;

    public CreateExhibition newInstance(Integer museumId) {
        final CreateExhibition myFragment = new CreateExhibition();
        final Bundle args = new Bundle(1);
        args.putInt(ID_MUSEUM_KEY, museumId);
        myFragment.setArguments(args);
        return myFragment;
    }

    private void getArgumentsFromBundle() {
        if (getArguments() != null) {
            museumId = getArguments().getInt(ID_MUSEUM_KEY);
        }
    }

    private void initViews(View rootView) {
        progressBar = rootView.findViewById(R.id.create_exhibition_progress_bar);
        dateOfStartTFB = rootView.findViewById(R.id.create_exhibition_date_of_start_text_field_box);
        dateOfEndTFB = rootView.findViewById(R.id.create_exhibition_date_of_end_text_field_box);
        onlineCheckBox = rootView.findViewById(R.id.create_exhibition_online_exhbtn_check_box);
        nameTFB = rootView.findViewById(R.id.create_exhibition_name_text_field_box);
        descriptionTFB = rootView.findViewById(R.id.create_exhibition_description_text_field_box);
        dateOfStartET = rootView.findViewById(R.id.create_exhibition_date_of_start_edit_text);
        dateOfEndET = rootView.findViewById(R.id.create_exhibition_date_of_end_edit_text);
        nameET = rootView.findViewById(R.id.create_exhibition_name_edit_text);
        descriptionET = rootView.findViewById(R.id.create_exhibition_description_edit_text);
        currImageImageView = rootView.findViewById(R.id.create_exhibition_main_image_image_view);
        chooseImageTextView = rootView.findViewById(R.id.create_exhibition_choose_image_btn);
        createExhibitionBtn = rootView.findViewById(R.id.create_exhibition_btn);
        hideDescriptionBtn = rootView.findViewById(R.id.create_exhibition_hide_description_btn);
        viewModel = ViewModelProviders.of(this).get(CreateExhibitionViewModel.class);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case GALLERY_REQUEST:
                if (resultCode == getActivity().RESULT_OK) {
                    Uri selectedImage = data.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                        file = BitmapConverter.convertBitmapToFile(bitmap, getContext());
                        cacheManager.deleteItem("newExhibition");
                        cacheManager.addBitmapToMemoryCache("newExhibition", bitmap);
                        currImageImageView.setImageBitmap(cacheManager.getBitmapFromMemCache("newExhibition"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.fragment_create_exhibition, container, false);
        getArgumentsFromBundle();
        initViews(rootView);
        setData();
        setListeners();
        return rootView;
    }

    private void setData() {
        if (cacheManager.getBitmapFromMemCache("newExhibition") != null) {
            currImageImageView.setImageBitmap(cacheManager.getBitmapFromMemCache("newExhibition"));
        }
    }

    private CreateExhibitionViewModel viewModel;

    private void createExhibition() {
        viewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading) progressBar.setVisibility(View.VISIBLE);
            else progressBar.setVisibility(View.GONE);
        });
        viewModel.liveDataCreateExhibition(museumId, nameET.getText().toString(), descriptionET.getText().toString(),
                dateOfStartET.getText().toString(), dateOfEndET.getText().toString(), file)
                .observe(this, model -> {
                    viewModel.getIsLoading().postValue(false);
                    if (model == null) {
                        Toast.makeText(getContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Успешное создание выставки", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)

    private void setListeners() {
        hideDescriptionBtn.setOnClickListener(new ClickListenerHideDescription(descriptionTFB));
        onlineCheckBox.setOnCheckedChangeListener((button, state) -> {
            if (state) {
                dateOfStartTFB.setVisibility(View.GONE);
                dateOfEndTFB.setVisibility(View.GONE);
            } else {
                dateOfStartTFB.setVisibility(View.VISIBLE);
                dateOfEndTFB.setVisibility(View.VISIBLE);
            }
        });
        chooseImageTextView.setOnClickListener(v -> {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
        });
        createExhibitionBtn.setOnClickListener(v -> {
            hideKeyboard();
            if (file != null && !nameTFB.isOnError() && !descriptionTFB.isOnError()
                    && !nameET.getText().toString().isEmpty()
                    && !descriptionET.getText().toString().isEmpty()
            ) {
                if (onlineCheckBox.isChecked()) {
                    dateOfStartET.setText("");
                    dateOfEndET.setText("");
                }
                createExhibition();
            } else {
                Toast.makeText(getContext(), "Проверьте введённые данные", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }


}
