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
import androidx.recyclerview.widget.RecyclerView;

import com.example.museums.API.models.Exhibition;
import com.example.museums.API.models.NewExhibitModel;
import com.example.museums.R;
import com.example.museums.view.activities.tabs.MuseumTab;
import com.example.museums.view.fragments.museum.exhibit.createExhibit.CreateExhibit;
import com.example.museums.view.fragments.museum.museumExhibits.QueryDeleteMuseumExhibit;
import com.example.museums.view.services.CacheManager;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHideDescription;
import com.example.museums.view.services.MethodsWithFragment;
import com.example.museums.view.services.oop.IDeletePosition;
import com.example.museums.view.services.oop.IUpdateList;
import com.example.museums.view.services.recyclerViews.NewExhibitsRecyclerViewAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class EditExhibtion extends Fragment implements IDeletePosition, IUpdateList {

    private MethodsWithFragment mth = new MethodsWithFragment();
    private NewExhibitsRecyclerViewAdapter mAdapter;
    public static final String LOGIN_KEY_USER = "login_key";
    private static String login;
    public List<NewExhibitModel> exhibits = new ArrayList<>();
    private Bitmap bitmap;
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
    public static final String NAME_KEY = "name_key";
    public static final String DATE_START_KEY = "date_key";
    public static final String DATE_END_KEY = "date_end_key";
    public static final String DESCRIPTION_KEY = "description_key";
    public static final String ID_EXHIBITION_KEY = "id_key";
    public static final String ID_MUSEUM_KEY = "museum_id";
    private static String museumId;
    private CacheManager cacheManager = new CacheManager();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new NewExhibitsRecyclerViewAdapter(this);
    }

    public EditExhibtion newInstance(String login) {
        final EditExhibtion myFragment = new EditExhibtion();
        final Bundle args = new Bundle(1);
        args.putString(LOGIN_KEY_USER, login);
        myFragment.setArguments(args);
        return myFragment;
    }


    public EditExhibtion newInstance(String login, int id, String museumId, Parcelable image, String name, String dataStart, String dataEnd, String description) {
        final EditExhibtion myFragment = new EditExhibtion();
        final Bundle args = new Bundle();
        args.putString(NAME_KEY, name);
        args.putString(DATE_START_KEY, dataStart);
        args.putString(LOGIN_KEY_USER, login);
        args.putString(DATE_END_KEY, dataEnd);
        args.putString(DESCRIPTION_KEY, description);
        args.putInt(ID_EXHIBITION_KEY, id);
        args.putString(ID_MUSEUM_KEY, museumId);
        args.putParcelable(IMAGE_KEY, image);
        myFragment.setArguments(args);
        return myFragment;
    }

    private Integer idExhibition;

    private void getArgumentsFromBundle() {
        if (getArguments() != null) {
            if (getArguments().getString(LOGIN_KEY_USER) != null) {
                login = getArguments().getString(LOGIN_KEY_USER);
            }
            if (getArguments().getString(ID_MUSEUM_KEY) != null) {
                museumId = getArguments().getString(ID_MUSEUM_KEY);
            }
            if (getArguments().getString(DESCRIPTION_KEY) != null) {
                String name = getArguments().getString(NAME_KEY);
                nameET.setText(name);
                if (getArguments().getString(DATE_START_KEY) != null) {
                    dateOfStartET.setText(getArguments().getString(DATE_START_KEY));
                    dateOfEndET.setText(getArguments().getString(DATE_END_KEY));
                } else {
                    onlineCheckBox.setChecked(true);
                }
                descriptionET.setText(getArguments().getString(DESCRIPTION_KEY));
                idExhibition = getArguments().getInt(ID_EXHIBITION_KEY);
                Bitmap b = (Bitmap) getArguments().getParcelable(IMAGE_KEY);
                bitmap = b.copy(b.getConfig(), true);
                currImageImageView.setImageBitmap(bitmap);
                cacheManager.addBitmapToMemoryCache(Integer.toString(idExhibition) + "  ", bitmap);
                getArguments().clear();
                if (exhibits.isEmpty()) {

                    QueryGetExhibitsFromExhibition q = new QueryGetExhibitsFromExhibition(this, this);
                    q.getQuery(idExhibition);
                }
            }

        }
        if (cacheManager.getBitmapFromMemCache(Integer.toString(idExhibition) + "  ") != null) {
            currImageImageView.setImageBitmap(cacheManager.getBitmapFromMemCache(Integer.toString(idExhibition) + "  "));

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
        recyclerView.setAdapter(mAdapter);
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
                        cacheManager.deleteItem(Integer.toString(idExhibition) + "  ");
                        cacheManager.addBitmapToMemoryCache(Integer.toString(idExhibition) + "  ", bitmap);
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
                inflater.inflate(R.layout.fragment_edit_exhibition, container, false);
        initViews(rootView);
        getArgumentsFromBundle();
        setListeners();
        return rootView;
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
                exhibition.idMuseum = museumId;
                exhibition.id = idExhibition;
                QueryEditExhibition queryCreateExhibition = new QueryEditExhibition(this);
                queryCreateExhibition.getQuery(exhibition);

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
        mAdapter.submitList(exhibits);

    }


    public void updateExhibit(int position, NewExhibitModel exhibit) {
        exhibits.remove(position);
        exhibits.add(position, exhibit);
        mAdapter.submitList(exhibits);
    }


    private QueryDeleteMuseumExhibit queryDeleteMuseumExhibit = new QueryDeleteMuseumExhibit(this);

    @Override
    public void deletePosition(int position, Integer id) {
        if (id != null) {
            queryDeleteMuseumExhibit.getQuery(id);
            exhibits.remove(position);
            mAdapter.notifyItemRemoved(position);

        } else {
            exhibits.remove(position);
            mAdapter.notifyItemRemoved(position);
            mAdapter.notifyItemRangeChanged(position, exhibits.size());

        }
    }

    @Override
    public void updateList(List<NewExhibitModel> list) {
        if (exhibits.size() == 0) {
            exhibits.addAll(list);
        }
        mAdapter.submitList(exhibits);

    }
}
