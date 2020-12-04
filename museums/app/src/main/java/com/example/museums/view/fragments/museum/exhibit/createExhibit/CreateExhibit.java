package com.example.museums.view.fragments.museum.exhibit.createExhibit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.API.models.Author;
import com.example.museums.API.models.NewExhibitModel;
import com.example.museums.R;
import com.example.museums.view.fragments.museum.authors.QueryAuthor;
import com.example.museums.view.fragments.museum.exhibition.editExhibition.EditExhibtion;
import com.example.museums.view.services.Listeners.onTouchListeners.OnToucLlistenerScrollViewSwipeLeftRightBack;
import com.example.museums.view.services.Listeners.textWatchers.TextWatcherEmptyField;
import com.example.museums.view.services.recyclerViews.AuthorsRecyclerViewAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class CreateExhibit extends Fragment {
    private ScrollView view;
    private EditText nameEditText, authorEditText, dateOfCreateEditText, wordKeysEditText, descriptionEditText;
    private TextFieldBoxes nameTextFieldBoxes, authorTextFieldBoxes, dateOfCreateTextFieldBoxes, wordKeysTextFieldBoxes, descriptionTextFieldBoxes;
    static final int GALLERY_REQUEST = 1;
    private Bitmap bitmap;
    private List<Author> authorList = new ArrayList<>();
    private ImageView mainImageView;
    private Button createBtn;
    private AuthorsRecyclerViewAdapter authorAdapter;
    public ProgressBar progressBar;
    private TextView choosePhotoBtn;
    private RecyclerView authorRecyclerView;
    public static final String ID_EXHIBITION = "exhibition_id_key";
    private Integer exhibitionId;

    public CreateExhibit newInstance(Integer idExhibition) {
        final CreateExhibit myFragment = new CreateExhibit();
        final Bundle args = new Bundle();
        if (idExhibition == null) {
            args.putInt(ID_EXHIBITION, -1);
        } else {
            args.putInt(ID_EXHIBITION, idExhibition);
        }
        myFragment.setArguments(args);
        return myFragment;
    }

    private void getArgumentsFromBundle() {
        if (getArguments() != null) {
            if (getArguments().getInt(ID_EXHIBITION) == -1) {
                exhibitionId = null;
            } else {
                exhibitionId = getArguments().getInt(ID_EXHIBITION);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_create_exhibit, container, false);
        initViews(rootView);
        setListeners();
        getArgumentsFromBundle();

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
        authorRecyclerView = rootView.findViewById(R.id.create_exhibit_authors_recycler_view);
        authorRecyclerView.setVisibility(View.GONE);
        progressBar = rootView.findViewById(R.id.create_exhibit_progress_bar);

        authorAdapter = new AuthorsRecyclerViewAdapter(authorList, authorEditText, authorRecyclerView);
        authorRecyclerView.setAdapter(authorAdapter);

        //Получить список авторов
        QueryAuthor queryAuthor = new QueryAuthor(this);
        queryAuthor.getQuery();
    }

    public void refreshAllList(List<Author> authors) {
        authorList = new ArrayList<>();
        authorList.addAll(authors);
        authorAdapter.updateAll(authors);
    }


    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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

                NewExhibitModel ex = new NewExhibitModel(null,
                        dateOfCreateEditText.getText().toString(),
                        wordKeysEditText.getText().toString(),
                        authorEditText.getText().toString(), nameEditText.getText().toString()
                        , bitmap, descriptionEditText.getText().toString()
                );

                hideKeyboard();
                authorRecyclerView.setVisibility(View.GONE);
                QueryInsertExhibit queryAddExhibit = new QueryInsertExhibit(this);
                queryAddExhibit.getQuery(ex, exhibitionId);

            } else {
                hideKeyboard();
                Toast.makeText(getContext(), "Проверьте введённые данные", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void insertNewExhibit(NewExhibitModel newEx) {
        EditExhibtion c = (EditExhibtion) getTargetFragment();
        c.addNewExhibit(newEx);
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
}
