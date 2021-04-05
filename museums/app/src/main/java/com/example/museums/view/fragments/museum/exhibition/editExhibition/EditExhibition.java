package com.example.museums.view.fragments.museum.exhibition.editExhibition;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.museums.API.models.exhibit.ExistingExhibit;
import com.example.museums.API.services.BitmapConverter;
import com.example.museums.R;
import com.example.museums.view.activities.tabs.MuseumTab;
import com.example.museums.view.fragments.museum.exhibit.createExhibit.CreateExhibit;
import com.example.museums.view.fragments.museum.museumExhibitions.MuseumExhibitions;
import com.example.museums.view.fragments.museum.museumExhibits.MuseumExhibits;
import com.example.museums.view.services.CacheManager;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHideDescription;
import com.example.museums.view.services.MethodsWithFragment;
import com.example.museums.view.services.oop.IDeletePosition;
import com.example.museums.view.services.oop.IUpdateList;
import com.example.museums.view.services.recyclerViews.NewExhibitsRecyclerViewAdapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class EditExhibition extends Fragment implements IDeletePosition, IUpdateList {

    private MethodsWithFragment mth = new MethodsWithFragment();
    private NewExhibitsRecyclerViewAdapter mAdapter;

    private static Integer idExhibition;
    public List<ExistingExhibit> exhibits = new ArrayList<>();
    private static Bitmap bitmap;
    private RecyclerView recyclerView;
    private static CheckBox onlineCheckBox;
    private TextFieldBoxes dateOfStartTFB, dateOfEndTFB, nameTFB, descriptionTFB;
    private EditText dateOfStartET, dateOfEndET, nameET, descriptionET;
    private static ImageView currImageImageView;
    private TextView chooseImageTextView, plusExhbt;
    private Button hideDescriptionBtn, createExhibitionBtn;
    public ProgressBar progressBar;
    static final int GALLERY_REQUEST = 1;
    public static final String IMAGE_KEY = "image_key";
    private String imageUrl;
    public static final String NAME_KEY = "name_key";
    public static final String DATE_START_KEY = "date_key";
    public static final String DATE_END_KEY = "date_end_key";
    public static final String DESCRIPTION_KEY = "description_key";
    public static final String ID_EXHIBITION_KEY = "id_key";
    public static final String ID_MUSEUM_KEY = "museum_id";
    private static Integer museumId;
    //  private CacheManager cacheManager = new CacheManager();
    private EditExhibitionViewModel viewModel;
    private static File file;


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_edit_exhibition, container, false);
        initViews(rootView);
        getArgumentsFromBundle();
        setListeners();
        return rootView;
    }

    public EditExhibition newInstance(Integer idExhibition, Integer museumId, String image, String name, String dataStart, String dataEnd, String description) {
        final EditExhibition myFragment = new EditExhibition();
        final Bundle args = new Bundle();
        args.putString(NAME_KEY, name);
        args.putString(DATE_START_KEY, dataStart);
        args.putString(DATE_END_KEY, dataEnd);
        args.putString(DESCRIPTION_KEY, description);
        args.putInt(ID_EXHIBITION_KEY, idExhibition);
        args.putInt(ID_MUSEUM_KEY, museumId);
        args.putString(IMAGE_KEY, image);
        myFragment.setArguments(args);
        return myFragment;
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
        plusExhbt = rootView.findViewById(R.id.create_new_exhibition_text_view);
        viewModel = ViewModelProviders.of(this).get(EditExhibitionViewModel.class);
        mAdapter = new NewExhibitsRecyclerViewAdapter(this);
        recyclerView = rootView.findViewById(R.id.create_exhibition_recycler_view);
        recyclerView.setAdapter(mAdapter);
    }

    private void getArgumentsFromBundle() {
        if (getArguments() != null) {

            museumId = getArguments().getInt(ID_MUSEUM_KEY);
            idExhibition = getArguments().getInt(ID_EXHIBITION_KEY);
            nameET.setText(getArguments().getString(NAME_KEY));
            if (getArguments().getString(DATE_START_KEY) != null) {
                dateOfStartET.setText(getArguments().getString(DATE_START_KEY));
                dateOfEndET.setText(getArguments().getString(DATE_END_KEY));
            } else {
                onlineCheckBox.setChecked(true);
            }
            imageUrl = getArguments().getString(IMAGE_KEY);
            if (bitmap != null) {
                Glide.with(getContext())
                        .asBitmap()
                        .load(bitmap)
                        .into(currImageImageView);
            } else {
                Glide.with(getContext())
                        .load(imageUrl)
                        .into(currImageImageView);
            }
            descriptionET.setText(getArguments().getString(DESCRIPTION_KEY));
            getExhibits();
        }
    }

    private void deleteExhibit(Integer id) {
        viewModel.getIsLoadingDeleteExhibit().observe(this, isLoading -> {
            if (isLoading) progressBar.setVisibility(View.VISIBLE);
            else progressBar.setVisibility(View.GONE);
        });
        viewModel.getLiveDataDeleteExhibit(id)
                .observe(this, model -> {
                    viewModel.getIsLoadingDeleteExhibit().postValue(false);
                    if (model == null) {
                        Toast.makeText(getContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
                    } else {
                        getExhibits();
                        Toast.makeText(getContext(), "Экспонат удалён", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void getExhibits() {
        viewModel.getIsLoadingListExhibits().observe(this, isLoading -> {
            if (isLoading) progressBar.setVisibility(View.VISIBLE);
            else progressBar.setVisibility(View.GONE);
        });
        viewModel.getLiveDataExhibitsFromExhibition(idExhibition)
                .observe(this, model -> {
                    viewModel.getIsLoadingListExhibits().postValue(false);
                    if (model == null) {
                        Toast.makeText(getContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
                    } else {
                        updateList(model);
                    }
                });
    }

    private void editExhibition() {
        viewModel.getIsLoadingEditExhibition().observe(this, isLoading -> {
            if (isLoading) progressBar.setVisibility(View.VISIBLE);
            else progressBar.setVisibility(View.GONE);
        });
        viewModel.getLiveDataEditExhibition(idExhibition, museumId, nameET.getText().toString(),
                descriptionET.getText().toString(), dateOfStartET.getText().toString(),
                dateOfEndET.getText().toString(), file)
                .observe(this, model -> {
                    viewModel.getIsLoadingEditExhibition().postValue(false);
                    if (model == null) {
                        Toast.makeText(getContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
                    } else {
                        file = null;
                        bitmap = null;
                        ((MuseumExhibitions)getTargetFragment()).getExhibitionsMuseum();
                        Toast.makeText(getContext(), "Выставка отредактирована", Toast.LENGTH_SHORT).show();
                    }
                });
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
//                        cacheManager.deleteItem(Integer.toString(idExhibition) + "  ");
//                        cacheManager.addBitmapToMemoryCache(Integer.toString(idExhibition) + "  ", bitmap);
                        Glide.with(getContext())
                                .asBitmap()
                                .load(bitmap)
                                .into(currImageImageView);
                        // currImageImageView.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)

    private void setListeners() {
        hideDescriptionBtn.setOnClickListener(new ClickListenerHideDescription(descriptionTFB));
        plusExhbt.setOnClickListener(v -> {
            Fragment myFragment = new CreateExhibit().newInstance(idExhibition);
            myFragment.setTargetFragment(this, 0);
            MuseumTab activity = (MuseumTab) v.getContext();
            mth.replaceFragment(myFragment, activity);
        });
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
            if (currImageImageView.getDrawable() != null && !nameTFB.isOnError() && !descriptionTFB.isOnError()
                    && !nameET.getText().toString().isEmpty()
                    && !descriptionET.getText().toString().isEmpty()) {
                hideKeyboard();
                editExhibition();
            } else {
                hideKeyboard();
                Toast.makeText(getContext(), "Проверьте введённые данные", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }


    @Override
    public void deletePosition(int position, Integer id) {
        deleteExhibit(id);
    }

    @Override
    public void updateList(List<ExistingExhibit> list) {
        exhibits = new ArrayList<>();
        exhibits.addAll(list);
        mAdapter.submitList(exhibits);
    }
}
