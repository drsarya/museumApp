package com.example.museums.view.fragments.museum.exhibit.editExhibit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.API.models.author.Author;
import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.R;
import com.example.museums.view.services.Listeners.onTouchListeners.OnToucLlistenerScrollViewSwipeLeftRightBack;
import com.example.museums.view.services.Listeners.textWatchers.TextWatcherEmptyField;
import com.example.museums.view.services.recyclerViews.AuthorsRecyclerViewAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class EditExhibit extends Fragment   {
    private ScrollView view;
    private EditText nameEditText;
    private EditText authorEditText;
    private EditText dateOfCreateEditText;
    private TextView firstLine;
    private EditText descriptionEditText;
    private TextFieldBoxes nameTextFieldBoxes;
    private TextFieldBoxes authorTextFieldBoxes;
    private TextFieldBoxes dateOfCreateTextFieldBoxes;
    private TextFieldBoxes descriptionTextFieldBoxes;
    static final int GALLERY_REQUEST = 1;
    private Bitmap bitmap;
    static final String EXHIBIT_DATA_MODEL = "exhibit_data_model";
    public ProgressBar progressBar;
    static final String EXHIBIT_NAME_MODEL = "exhibit_name_model";
    static final String EXHIBIT_DESCRIPTION_MODEL = "exhibit_description_model";
    static final String EXHIBIT_IMAGE_MODEL = "exhibit_image_model";
    static final String EXHIBIT_POSITION_MODEL = "exhibit_position_model";
    static final String EXHIBIT_ID_KEY = "id_exhibit";
    private RecyclerView authorRecyclerView;
    private AuthorsRecyclerViewAdapter authorAdapter;
    static final String EXHIBIT_AUTHOR_MODEL = "exhibit_author_model";
    private ImageView mainImageView;
    private Button createBtn;
    private Integer idExhibit;
    private int positionExh;
    private TextView choosePhotoBtn;
    private List<Author> authorList = new ArrayList<>();
    private EditExhibitPresenter editExhibitPresenter = new EditExhibitPresenter();

    public EditExhibit newInstance(Integer id, String dateOfCreate, String author, String name, Parcelable photo, String description, int positionExh) {
        final EditExhibit myFragment = new EditExhibit();
        final Bundle args = new Bundle(2);
        args.putParcelable(EXHIBIT_IMAGE_MODEL, photo);
        args.putString(EXHIBIT_DESCRIPTION_MODEL, description);

        args.putString(EXHIBIT_AUTHOR_MODEL, author);
        if (id != null) {
            args.putInt(EXHIBIT_ID_KEY, id);
        } else {
            args.putInt(EXHIBIT_ID_KEY, -1);
        }
        args.putString(EXHIBIT_NAME_MODEL, name);
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
            if (arguments.getParcelable(EXHIBIT_IMAGE_MODEL) != null) {
                mainImageView.setImageBitmap(arguments.getParcelable(EXHIBIT_IMAGE_MODEL));
            }
            if (arguments.getInt(EXHIBIT_ID_KEY) == -1) {
                idExhibit = null;
            } else {
                idExhibit = arguments.getInt(EXHIBIT_ID_KEY);
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
        descriptionEditText = rootView.findViewById(R.id.create_exhibit_description_of_exhibit_edit_text);
        nameTextFieldBoxes = rootView.findViewById(R.id.create_exhibit_name_text_field_box);
        authorTextFieldBoxes = rootView.findViewById(R.id.create_exhibit_author_text_field_box);
        dateOfCreateTextFieldBoxes = rootView.findViewById(R.id.create_exhibit_date_of_create_text_field_box);
        descriptionTextFieldBoxes = rootView.findViewById(R.id.create_exhibit_description_of_exhibit_text_field_box);
        choosePhotoBtn = rootView.findViewById(R.id.create_exhibit_choose_photo_text_view);
        mainImageView = rootView.findViewById(R.id.create_exhibit_chosen_photo_image_view);
        createBtn = rootView.findViewById(R.id.create_exhibit_create_exhibit_btn);
        createBtn.setText("Обновить");
        firstLine = rootView.findViewById(R.id.create_exhibit_first_line_text_view);
        firstLine.setText("Обновить информацию");
        authorRecyclerView = rootView.findViewById(R.id.create_exhibit_authors_recycler_view);
        progressBar = rootView.findViewById(R.id.create_exhibit_progress_bar);

        authorAdapter = new AuthorsRecyclerViewAdapter(authorList, authorEditText, authorRecyclerView);
        authorRecyclerView.setAdapter(authorAdapter);
        //authorPresenter.loadData();

    }

    public void refreshAllList(List<Author> authors) {
        System.out.println(authors.size() + "refresh");
        authorList = new ArrayList<>();
        authorList.addAll(authors);
        authorAdapter.updateAll(authors);
        authorRecyclerView.setVisibility(View.GONE);
    }

    private void setListeners() {
        choosePhotoBtn.setOnClickListener(v -> {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
        });

        authorEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                authorRecyclerView.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    authorRecyclerView.setVisibility(View.GONE);
                } else {
                    authorRecyclerView.setVisibility(View.VISIBLE);
                }
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        nameEditText.addTextChangedListener(new TextWatcherEmptyField(nameTextFieldBoxes));
        authorEditText.addTextChangedListener(new TextWatcherEmptyField(authorTextFieldBoxes));
        descriptionEditText.addTextChangedListener(new TextWatcherEmptyField(descriptionTextFieldBoxes));
        dateOfCreateEditText.addTextChangedListener(new TextWatcherEmptyField(dateOfCreateTextFieldBoxes));
        createBtn.setOnClickListener(v -> {

            BitmapDrawable drawable = (BitmapDrawable) mainImageView.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            ExistingExhibit ex = new ExistingExhibit(
                    new Author(authorEditText.getText().toString()),
                    nameEditText.getText().toString(), null, descriptionEditText.getText().toString(),
                    dateOfCreateEditText.getText().toString(), null, idExhibit);


            editExhibitPresenter.setInfo(ex, bitmap);
            try {
                editExhibitPresenter.loadData();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }

    private boolean containsString(String fullName, String currText) {
        String newName = fullName.toLowerCase();
        String newCurrText = currText.toLowerCase();
        if (newName.contains(newCurrText)) {
            return true;
        } else return false;
    }

    private void filter(String text) {
        List<Author> temp = new ArrayList();
        for (Author d : authorList) {
            if (containsString(d.fullName, text)) {
                temp.add(d);
            }
        }
        authorAdapter.updateList(temp);
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


 //   public <T> void showData(T data) {

//        if (getTargetFragment().getClass().toString().equals(EditExhibtion.class.toString())) {
//            ((EditExhibtion) getParentFragment()).presenter.loadData();
//
//        } else {
//            ((MuseumExhibits) getParentFragment()).presenter.loadData();
//        }
 //   }


}
