package com.example.museums.view.fragments.museum.exhibit.editExhibit;

import android.annotation.SuppressLint;
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

import com.bumptech.glide.Glide;
import com.example.museums.API.models.author.Author;
import com.example.museums.API.models.exhibit.ExistingExhibit;
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

import static com.example.museums.view.ConstantKeys.AUTHOR_KEY;
import static com.example.museums.view.ConstantKeys.DATE_KEY;
import static com.example.museums.view.ConstantKeys.DESCRIPTION_KEY;
import static com.example.museums.view.ConstantKeys.ID_EXHIBIT_KEY;
import static com.example.museums.view.ConstantKeys.EXHIBIT_POSITION_MODEL;
import static com.example.museums.view.ConstantKeys.IMAGE_KEY;
import static com.example.museums.view.ConstantKeys.NAME_KEY;

public class EditExhibit extends Fragment {
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
    public ProgressBar progressBar;


    private RecyclerView authorRecyclerView;
    private AuthorsRecyclerViewAdapter authorAdapter;
    private ImageView mainImageView;
    private Button createBtn;
    private Integer idExhibit;
    private int positionExh;
    private String image;
    private TextView choosePhotoBtn;
    private List<Author> authorList = new ArrayList<>();
    private EditExhibitViewModel viewModel;
    private File file;

    public EditExhibit newInstance(Integer id, String dateOfCreate, String author, String name, String photo, String description, int positionExh) {
        final EditExhibit myFragment = new EditExhibit();
        final Bundle args = new Bundle(2);
        args.putString(IMAGE_KEY, photo);
        args.putString(DESCRIPTION_KEY, description);
        args.putString(AUTHOR_KEY, author);
        args.putInt(ID_EXHIBIT_KEY, id);
        args.putString(NAME_KEY, name);
        args.putString(DATE_KEY, dateOfCreate);
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
            nameEditText.setText(arguments.getString(NAME_KEY));
            authorEditText.setText(arguments.getString(AUTHOR_KEY));
            descriptionEditText.setText(arguments.getString(DESCRIPTION_KEY));
            idExhibit = arguments.getInt(ID_EXHIBIT_KEY);
            dateOfCreateEditText.setText(arguments.getString(DATE_KEY));
            positionExh = arguments.getInt(EXHIBIT_POSITION_MODEL);
            image = arguments.getString(IMAGE_KEY);
            Glide.with(getContext())
                    .load(image)
                    .into(mainImageView);
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
        viewModel = ViewModelProviders.of(this).get(EditExhibitViewModel.class);

        authorAdapter = new AuthorsRecyclerViewAdapter(authorList, authorEditText, authorRecyclerView);
        authorRecyclerView.setAdapter(authorAdapter);
        getAuthors();

    }

    private void getAuthors() {
        viewModel.getLiveDataAuthorList()
                .observe(this, model -> {
                    viewModel.getIsLoading().postValue(false);
                    if (model == null) {
                        Toast.makeText(getContext(), "Ошибка получения данных об авторах", Toast.LENGTH_SHORT).show();
                    } else {
                        refreshAllList(model);
                    }
                });
    }

    public void refreshAllList(List<Author> authors) {
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
            if (mainImageView.getDrawable() != null && !nameTextFieldBoxes.isOnError() && !authorTextFieldBoxes.isOnError() && !descriptionTextFieldBoxes.isOnError()
                    && !dateOfCreateTextFieldBoxes.isOnError()
                    && !nameEditText.getText().toString().isEmpty() && !authorEditText.getText().toString().isEmpty()
                    && !descriptionEditText.getText().toString().isEmpty() && !dateOfCreateEditText.getText().toString().isEmpty()
            ) {
                ExistingExhibit ex = new ExistingExhibit(
                        new Author(authorEditText.getText().toString()),
                        nameEditText.getText().toString(), null, descriptionEditText.getText().toString(),
                        dateOfCreateEditText.getText().toString(), null, idExhibit);
                editExhibit();
            } else {
                Toast.makeText(getContext(), "Проверьте введённые данные", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void editExhibit() {
        viewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading) progressBar.setVisibility(View.VISIBLE);
            else progressBar.setVisibility(View.GONE);
        });
        viewModel.getLiveDataUpdateExhibit(new Author(authorEditText.getText().toString()),
                nameEditText.getText().toString(), descriptionEditText.getText().toString(),
                dateOfCreateEditText.getText().toString(), idExhibit, file)
                .observe(this, model -> {
                    viewModel.getIsLoading().postValue(false);
                    if (model == null) {
                        Toast.makeText(getContext(), "Ошибка получения данных обновления", Toast.LENGTH_SHORT).show();
                    } else {
                        if (getTargetFragment().getClass().toString().equals(EditExhibition.class.toString())) {
                            ((EditExhibition) getTargetFragment()).getExhibits();
                        } else {
                            ((MuseumExhibits) getTargetFragment()).getExhibitsMuseum();
                        }
                        Toast.makeText(getContext(), "Успешное обновление", Toast.LENGTH_SHORT).show();

                        bitmap = null;
                        file = null;
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
                    Glide.with(getContext())
                            .asBitmap()
                            .load(bitmap)
                            .into(mainImageView);
                }
        }
    }
}
