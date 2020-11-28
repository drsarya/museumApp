package com.example.museums.view.fragments.museum.createExhibition;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.museums.R;
import com.example.museums.view.services.Listeners.onTouchListeners.OnToucLlistenerScrollViewSwipeLeftRightBack;
import com.example.museums.view.services.Listeners.textWatchers.TextWatcherEmptyField;

import java.io.IOException;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class EditExhibit extends Fragment {
    private ScrollView view;
    private NewExhibitModel newExhibitModel;
    private EditText nameEditText;
    private EditText authorEditText;
    private EditText dateOfCreateEditText;
    private EditText wordKeysEditText;
    private EditText descriptionEditText;
    private TextFieldBoxes nameTextFieldBoxes;
    private TextFieldBoxes authorTextFieldBoxes;
    private TextFieldBoxes dateOfCreateTextFieldBoxes;
    private TextFieldBoxes wordKeysTextFieldBoxes;
    private TextFieldBoxes descriptionTextFieldBoxes;
    static final int GALLERY_REQUEST = 1;
    private Bitmap bitmap;
    static final String EXHIBIT_DATA_MODEL = "exhibit_data_model";

    static final String EXHIBIT_NAME_MODEL = "exhibit_name_model";
    static final String EXHIBIT_DESCRIPTION_MODEL = "exhibit_description_model";
    static final String EXHIBIT_IMAGE_MODEL = "exhibit_image_model";
    static final String EXHIBIT_TAGS_MODEL = "exhibit_tags_model";
    static final String EXHIBIT_POSITION_MODEL = "exhibit_position_model";

    static final String EXHIBIT_AUTHOR_MODEL = "exhibit_author_model";
    private NewExhibitModel exhibitModel;
    private ImageView mainImageView;
    private Button createBtn;
    private int positionExh;
    private TextView choosePhotoBtn;

    public EditExhibit newInstance(String dateOfCreate, String tags, String author, String name, Parcelable photo, String description, int positionExh) {


        final EditExhibit myFragment = new EditExhibit();
        final Bundle args = new Bundle(2);
        args.putParcelable(EXHIBIT_IMAGE_MODEL, photo);
        args.putString(EXHIBIT_DESCRIPTION_MODEL, description);
        args.putString(EXHIBIT_TAGS_MODEL, tags);
        args.putString(EXHIBIT_AUTHOR_MODEL, author);
        args.putString(EXHIBIT_NAME_MODEL, name);
        args.putString(EXHIBIT_DESCRIPTION_MODEL, description);
        args.putString(EXHIBIT_DATA_MODEL, dateOfCreate);
        args.putInt(EXHIBIT_POSITION_MODEL, positionExh);


        myFragment.setArguments(args);
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_create_exhibit, container, false);

        initViews(rootView);
        setListeners();
        Bundle arguments = getArguments();
        if (arguments != null) {
            nameEditText.setText(arguments.getString(EXHIBIT_NAME_MODEL));
            authorEditText.setText(arguments.getString(EXHIBIT_AUTHOR_MODEL));
            descriptionEditText.setText(arguments.getString(EXHIBIT_DESCRIPTION_MODEL));
            wordKeysEditText.setText(arguments.getString(EXHIBIT_TAGS_MODEL));
            if (arguments.getParcelable(EXHIBIT_IMAGE_MODEL) != null) {
                mainImageView.setImageBitmap(arguments.getParcelable(EXHIBIT_IMAGE_MODEL));
            }
            dateOfCreateEditText.setText(arguments.getString(EXHIBIT_DATA_MODEL));
            positionExh = arguments.getInt(EXHIBIT_POSITION_MODEL);
            arguments.clear();
        }
        return rootView;
    }

    private void initViews(View rootView) {
        nameEditText = rootView.findViewById(R.id.create_exhibit_name_edit_text);
        authorEditText = rootView.findViewById(R.id.create_exhibit_author_edit_text);
        dateOfCreateEditText = rootView.findViewById(R.id.create_exhibit_date_of_create_edit_text);
        wordKeysEditText = rootView.findViewById(R.id.create_exhibit_key_words_edit_text);
        descriptionEditText = rootView.findViewById(R.id.create_exhibit_description_of_exhibit_edit_text);
        nameTextFieldBoxes = rootView.findViewById(R.id.create_exhibit_name_text_field_box);
        authorTextFieldBoxes = rootView.findViewById(R.id.create_exhibit_author_text_field_box);
        dateOfCreateTextFieldBoxes = rootView.findViewById(R.id.create_exhibit_date_of_create_text_field_box);
        wordKeysTextFieldBoxes = rootView.findViewById(R.id.create_exhibit_key_words_text_field_box);
        descriptionTextFieldBoxes = rootView.findViewById(R.id.create_exhibit_description_of_exhibit_text_field_box);
        choosePhotoBtn = rootView.findViewById(R.id.create_exhibit_choose_photo_text_view);
        mainImageView = rootView.findViewById(R.id.create_exhibit_chosen_photo_image_view);
        createBtn = rootView.findViewById(R.id.create_exhibit_create_exhibit_btn);
        createBtn.setText("Обновить");
    }


    private void setListeners() {
        choosePhotoBtn.setOnClickListener(v -> {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
        });
        nameEditText.addTextChangedListener(new TextWatcherEmptyField(nameTextFieldBoxes));
        authorEditText.addTextChangedListener(new TextWatcherEmptyField(authorTextFieldBoxes));
        descriptionEditText.addTextChangedListener(new TextWatcherEmptyField(descriptionTextFieldBoxes));
        dateOfCreateEditText.addTextChangedListener(new TextWatcherEmptyField(dateOfCreateTextFieldBoxes));
        wordKeysEditText.addTextChangedListener(new TextWatcherEmptyField(wordKeysTextFieldBoxes));
        createBtn.setOnClickListener(v -> {
            if (mainImageView.getDrawable() != null && !nameTextFieldBoxes.isOnError() && !authorTextFieldBoxes.isOnError() && !descriptionTextFieldBoxes.isOnError()
                    && !dateOfCreateTextFieldBoxes.isOnError() && !wordKeysTextFieldBoxes.isOnError()
                    && !nameEditText.getText().toString().isEmpty() && !authorEditText.getText().toString().isEmpty()
                    && !descriptionEditText.getText().toString().isEmpty() && !dateOfCreateEditText.getText().toString().isEmpty()
                    && !wordKeysEditText.getText().toString().isEmpty()) {
                BitmapDrawable drawable = (BitmapDrawable) mainImageView.getDrawable();
                Bitmap bitmap = drawable.getBitmap();

                NewExhibitModel ex = new NewExhibitModel(
                        dateOfCreateEditText.getText().toString(),
                        wordKeysEditText.getText().toString(),
                        authorEditText.getText().toString(), nameEditText.getText().toString()
                        , bitmap, descriptionEditText.getText().toString()
                );

                CreateExhibition c = (CreateExhibition) getTargetFragment();

                c.updateExhibit(positionExh, ex);
                Toast.makeText(getContext(), "Успешное обновление", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Проверьте введённые данные", Toast.LENGTH_SHORT).show();
            }


        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        view = (ScrollView) getActivity().findViewById(R.id.create_exhibit_scroll_view);
        view.setOnTouchListener(new OnToucLlistenerScrollViewSwipeLeftRightBack(getActivity(), false));
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
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mainImageView.setImageBitmap(bitmap);
                }
        }
    }

}
