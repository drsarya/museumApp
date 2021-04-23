package com.example.museums.view.fragments.museum.exhibit.createExhibit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
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
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.API.models.author.Author;
import com.example.museums.API.services.BitmapConverter;
import com.example.museums.R;
import com.example.museums.view.fragments.museum.exhibition.editExhibition.EditExhibition;
import com.example.museums.view.fragments.museum.museumExhibits.MuseumExhibits;
import com.example.museums.view.services.Listeners.onTouchListeners.OnTouchListenerScrollViewSwipeLeftRightBack;
import com.example.museums.view.services.Listeners.textWatchers.TextWatcherEmptyField;
import com.example.museums.view.services.recyclerViews.AuthorsRecyclerViewAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

import static com.example.museums.view.ConstantKeys.ID_EXHIBITION_KEY;

public class CreateExhibit extends Fragment {
    private ScrollView view;
    private EditText nameEditText, authorEditText, dateOfCreateEditText, descriptionEditText;
    private TextFieldBoxes nameTextFieldBoxes, authorTextFieldBoxes, dateOfCreateTextFieldBoxes, descriptionTextFieldBoxes;
    static final int GALLERY_REQUEST = 1;
    private Bitmap bitmap;
    private List<Author> authorList = new ArrayList<>();
    private ImageView mainImageView;
    private Button createBtn;
    private AuthorsRecyclerViewAdapter authorAdapter;
    public ProgressBar progressBar;
    private TextView choosePhotoBtn;
    private RecyclerView authorRecyclerView;
     private Integer exhibitionId;
    private CreateExhibitViewModel viewModel;
    private File file;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_create_exhibit, container, false);
        getArgumentsFromBundle();
        initViews(rootView);
        getAuthors();
        setListeners();
        return rootView;
    }

    public CreateExhibit newInstance(Integer idExhibition) {
        final CreateExhibit myFragment = new CreateExhibit();
        final Bundle args = new Bundle();
        args.putInt(ID_EXHIBITION_KEY, idExhibition);
        myFragment.setArguments(args);
        return myFragment;
    }

    private void getArgumentsFromBundle() {
        if (getArguments() != null) {
            exhibitionId = getArguments().getInt(ID_EXHIBITION_KEY);
        }
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
        authorRecyclerView = rootView.findViewById(R.id.create_exhibit_authors_recycler_view);
        authorRecyclerView.setVisibility(View.GONE);
        progressBar = rootView.findViewById(R.id.create_exhibit_progress_bar);
        authorAdapter = new AuthorsRecyclerViewAdapter(authorList, authorEditText, authorRecyclerView);
        authorRecyclerView.setAdapter(authorAdapter);
        viewModel = ViewModelProviders.of(this).get(CreateExhibitViewModel.class);
    }

    private void getAuthors() {
        viewModel.getLiveDataAuthorList()
                .observe(this, model -> {
                    viewModel.getIsLoading().postValue(false);
                    if (model == null) {
                        Toast.makeText(getContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
                    } else {
                        refreshAllList(model);
                    }
                });
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
        createBtn.setOnClickListener(v -> {
            if (file != null && !nameTextFieldBoxes.isOnError() && !authorTextFieldBoxes.isOnError() && !descriptionTextFieldBoxes.isOnError()
                    && !dateOfCreateTextFieldBoxes.isOnError()
                    && !nameEditText.getText().toString().isEmpty() && !authorEditText.getText().toString().isEmpty()
                    && !descriptionEditText.getText().toString().isEmpty() && !dateOfCreateEditText.getText().toString().isEmpty()
            ) {
                hideKeyboard();
                authorRecyclerView.setVisibility(View.GONE);
                createExhibit();
            } else {
                hideKeyboard();
                Toast.makeText(getContext(), "Проверьте введённые данные", Toast.LENGTH_SHORT).show();
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
        view.setOnTouchListener(new OnTouchListenerScrollViewSwipeLeftRightBack(getActivity(), false));
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
                    file = BitmapConverter.convertBitmapToFile(bitmap, getContext());

                    mainImageView.setImageBitmap(bitmap);
                }
        }
    }

    private void createExhibit() {
        viewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading) progressBar.setVisibility(View.VISIBLE);
            else progressBar.setVisibility(View.GONE);
        });
        viewModel.getLiveDataCreateExhibit(new Author(authorEditText.getText().toString()),
                nameEditText.getText().toString(), descriptionEditText.getText().toString(),
                dateOfCreateEditText.getText().toString(), exhibitionId, file)
                .observe(this, model -> {
                    viewModel.getIsLoading().postValue(false);
                    if (model == null) {
                        Toast.makeText(getContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Успешное создание экспоната", Toast.LENGTH_SHORT).show();

                        if (getTargetFragment().getClass().toString().equals(EditExhibition.class.toString())) {
                            ((EditExhibition) getTargetFragment()).getExhibits();
                        } else {
                            ((MuseumExhibits) getTargetFragment()).getExhibitsMuseum();
                        }
                    }
                });
    }

}
