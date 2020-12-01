package com.example.museums.view.fragments.museum.createExhibition;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.API.models.Exhibition;
import com.example.museums.R;
import com.example.museums.view.activities.tabs.MuseumTab;
import com.example.museums.view.fragments.museum.createExhibit.CreateExhibit;
import com.example.museums.view.fragments.museum.createExhibit.NewExhibitModel;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHideDescription;
import com.example.museums.view.services.MethodsWithFragment;
import com.example.museums.view.services.oop.IDeletePosition;
import com.example.museums.view.services.recyclerViews.NewExhibitsRecyclerViewAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class CreateExhibition extends Fragment implements IDeletePosition {

    private MethodsWithFragment mth = new MethodsWithFragment();
    private NewExhibitsRecyclerViewAdapter mAdapter;
    public static final String LOGIN_KEY_USER = "login_key";
    private String login;
    public static List<NewExhibitModel> exhibits = new ArrayList<>();
    private Bitmap bitmap;
    private TextView plusExhbt;
    private RecyclerView recyclerView;
    private static CheckBox onlineCheckBox;
    private TextFieldBoxes dateOfStartTFB;
    private TextFieldBoxes dateOfEndTFB;
    private TextFieldBoxes nameTFB;
    private TextFieldBoxes descriptionTFB;
    private EditText dateOfStartET;
    private EditText dateOfEndET;
    private EditText nameET;
    private EditText descriptionET;
    private static ImageView currImageImageView;
    private TextView chooseImageTextView;

    private Button hideDescriptionBtn;
    public ProgressBar progressBar;
    private Button createExhibitionBtn;
    static final int GALLERY_REQUEST = 1;


    public CreateExhibition newInstance(String login) {
        final CreateExhibition myFragment = new CreateExhibition();
        final Bundle args = new Bundle(1);
        args.putString(LOGIN_KEY_USER, login);
        myFragment.setArguments(args);
        return myFragment;
    }

    private void getArgumentsFromBundle() {
        if (getArguments() != null) {
            login = getArguments().getString(LOGIN_KEY_USER);
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
        plusExhbt = rootView.findViewById(R.id.create_new_exhibition_text_view);
        recyclerView = rootView.findViewById(R.id.create_exhibition_recycler_view);
        mAdapter = new NewExhibitsRecyclerViewAdapter(exhibits, this);
        recyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//
//        if (getArguments().getParcelable("image") != null) {
//
//            Bitmap n = (Bitmap) data.getParcelableExtra("image");
//            currImageImageView.setImageBitmap(n);
//            getArguments().clear();
//
//        }

        switch (requestCode) {
            case GALLERY_REQUEST:
                if (resultCode == getActivity().RESULT_OK) {
                    Uri selectedImage = data.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                        //                      Bundle b = getArguments();
//                        if (b != null) {
//                            b.putParcelable("image", bitmap);
//                            System.out.println("gjkj;bhsbdsbdshdshbd");
//                        }
                        currImageImageView.setImageBitmap(bitmap);

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
        initViews(rootView);
        setListeners();
        getArgumentsFromBundle();
        return rootView;
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)

    private void setListeners() {
        hideDescriptionBtn.setOnClickListener(new ClickListenerHideDescription(descriptionTFB));
        plusExhbt.setOnClickListener(v -> {
            Fragment myFragment = new CreateExhibit();
            myFragment.setTargetFragment(CreateExhibition.this, 0);
            MuseumTab activity = (MuseumTab) v.getContext();
            mth.replaceFragment(myFragment, v, activity);
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
                    && !exhibits.isEmpty() && !nameET.getText().toString().isEmpty()
                    && !descriptionET.getText().toString().isEmpty()
            ) {
                Exhibition exhibition = new Exhibition();
                exhibition.name = nameET.getText().toString();
                BitmapDrawable drawable = (BitmapDrawable) currImageImageView.getDrawable();
                Bitmap image = drawable.getBitmap();
                exhibition.description = descriptionET.getText().toString();
                exhibition.image = image;
                if (onlineCheckBox.isChecked()) {
                    exhibition.firstDate = null;
                    exhibition.lastDate = null;
                } else {
                    if (!dateOfEndTFB.isOnError() && !dateOfStartTFB.isOnError()
                            && !dateOfStartET.getText().toString().isEmpty()
                            && !dateOfStartET.getText().toString().isEmpty()) {
                        exhibition.firstDate = dateOfStartET.getText().toString();
                        exhibition.lastDate = dateOfEndET.getText().toString();

                    } else {
                        Toast.makeText(getContext(), "Проверьте введённые данные", Toast.LENGTH_SHORT).show();
                    }
                }
                hideKeyboard();
                QueryCreateExhibition queryCreateExhibition = new QueryCreateExhibition(this);
                queryCreateExhibition.getQuery(login, exhibition, exhibits);
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

    public void addNewExhibit(NewExhibitModel exhibit) {
        exhibits.add(exhibit);
        mAdapter.updateAll(exhibits);
    }

    public void updateExhibit(int position, NewExhibitModel exhibit) {
        exhibits.remove(position);
        exhibits.add(position, exhibit);
        mAdapter.updateAll(exhibits);
    }


    @Override
    public void deletePosition(int position, int id) {
        exhibits.remove(position);
        mAdapter.notifyItemRemoved(position);
        mAdapter.notifyItemRangeChanged(position, exhibits.size());
    }
}
