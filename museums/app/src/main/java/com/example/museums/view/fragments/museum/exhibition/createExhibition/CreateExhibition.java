//package com.example.museums.view.fragments.museum.exhibition.createExhibition;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.drawable.BitmapDrawable;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.annotation.RequiresApi;
//import androidx.fragment.app.Fragment;
//
//import com.example.museums.R;
//import com.example.museums.view.services.CacheManager;
//import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHideDescription;
//import com.example.museums.view.services.MethodsWithFragment;
//
//import java.io.IOException;
//
//import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;
//
//public class CreateExhibition extends Fragment {
//
//    private MethodsWithFragment mth = new MethodsWithFragment();
//    public static final String LOGIN_KEY_USER = "login_key";
//    private static String login;
//    private Bitmap bitmap;
//    private static CheckBox onlineCheckBox;
//    private TextFieldBoxes dateOfStartTFB, dateOfEndTFB, nameTFB, descriptionTFB;
//    private EditText dateOfStartET, dateOfEndET, nameET, descriptionET;
//    private static ImageView currImageImageView;
//    private TextView chooseImageTextView;
//    private Button hideDescriptionBtn, createExhibitionBtn;
//    public ProgressBar progressBar;
//    static final int GALLERY_REQUEST = 1;
//    private CacheManager cacheManager = new CacheManager();
//
//    public CreateExhibition newInstance(String login) {
//        final CreateExhibition myFragment = new CreateExhibition();
//        final Bundle args = new Bundle(1);
//        args.putString(LOGIN_KEY_USER, login);
//        myFragment.setArguments(args);
//        return myFragment;
//    }
//
//    private void getArgumentsFromBundle() {
//        if (getArguments() != null) {
//            login = getArguments().getString(LOGIN_KEY_USER);
//        }
//    }
//
//    private void initViews(View rootView) {
//        progressBar = rootView.findViewById(R.id.create_exhibition_progress_bar);
//        dateOfStartTFB = rootView.findViewById(R.id.create_exhibition_date_of_start_text_field_box);
//        dateOfEndTFB = rootView.findViewById(R.id.create_exhibition_date_of_end_text_field_box);
//        onlineCheckBox = rootView.findViewById(R.id.create_exhibition_online_exhbtn_check_box);
//        nameTFB = rootView.findViewById(R.id.create_exhibition_name_text_field_box);
//        descriptionTFB = rootView.findViewById(R.id.create_exhibition_description_text_field_box);
//        dateOfStartET = rootView.findViewById(R.id.create_exhibition_date_of_start_edit_text);
//        dateOfEndET = rootView.findViewById(R.id.create_exhibition_date_of_end_edit_text);
//        nameET = rootView.findViewById(R.id.create_exhibition_name_edit_text);
//        descriptionET = rootView.findViewById(R.id.create_exhibition_description_edit_text);
//        currImageImageView = rootView.findViewById(R.id.create_exhibition_main_image_image_view);
//        chooseImageTextView = rootView.findViewById(R.id.create_exhibition_choose_image_btn);
//        createExhibitionBtn = rootView.findViewById(R.id.create_exhibition_btn);
//        hideDescriptionBtn = rootView.findViewById(R.id.create_exhibition_hide_description_btn);
//    }
//
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case GALLERY_REQUEST:
//                if (resultCode == getActivity().RESULT_OK) {
//                    Uri selectedImage = data.getData();
//                    try {
//                        bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
//                        cacheManager.deleteItem("newExhibition");
//                        cacheManager.addBitmapToMemoryCache("newExhibition", bitmap);
//                        currImageImageView.setImageBitmap(cacheManager.getBitmapFromMemCache("newExhibition"));
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//        }
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.Q)
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        View rootView =
//                inflater.inflate(R.layout.fragment_create_exhibition, container, false);
//        getArgumentsFromBundle();
//        initViews(rootView);
//        setData();
//        setListeners();
//        return rootView;
//    }
//
//    private void setData() {
//        if (cacheManager.getBitmapFromMemCache("newExhibition") != null) {
//            currImageImageView.setImageBitmap(cacheManager.getBitmapFromMemCache("newExhibition"));
//        }
//    }
//
//
//    @RequiresApi(api = Build.VERSION_CODES.Q)
//
//    private void setListeners() {
//        hideDescriptionBtn.setOnClickListener(new ClickListenerHideDescription(descriptionTFB));
//        onlineCheckBox.setOnCheckedChangeListener((button, state) -> {
//            if (state) {
//                dateOfStartTFB.setVisibility(View.GONE);
//                dateOfEndTFB.setVisibility(View.GONE);
//            } else {
//                dateOfStartTFB.setVisibility(View.VISIBLE);
//                dateOfEndTFB.setVisibility(View.VISIBLE);
//            }
//        });
//
//        chooseImageTextView.setOnClickListener(v -> {
//            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//            photoPickerIntent.setType("image/*");
//            startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
//        });
//        createExhibitionBtn.setOnClickListener(v -> {
//            if (currImageImageView.getDrawable() != null && !nameTFB.isOnError() && !descriptionTFB.isOnError()
//                    && !nameET.getText().toString().isEmpty()
//                    && !descriptionET.getText().toString().isEmpty()
//            ) {
//                Exhibition exhibition = new Exhibition();
//                exhibition.name = nameET.getText().toString();
//                BitmapDrawable drawable = (BitmapDrawable) currImageImageView.getDrawable();
//                Bitmap image = drawable.getBitmap();
//                exhibition.description = descriptionET.getText().toString();
//                exhibition.image = image;
//                if (onlineCheckBox.isChecked()) {
//                    exhibition.firstDate = null;
//                    exhibition.lastDate = null;
//                } else {
//                    if (!dateOfEndTFB.isOnError() && !dateOfStartTFB.isOnError()
//                            && !dateOfStartET.getText().toString().isEmpty()
//                            && !dateOfStartET.getText().toString().isEmpty()) {
//                        exhibition.firstDate = dateOfStartET.getText().toString();
//                        exhibition.lastDate = dateOfEndET.getText().toString();
//
//                    } else {
//                        Toast.makeText(getContext(), "Проверьте введённые данные", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                hideKeyboard();
//                QueryCreateExhibition queryCreateExhibition = new QueryCreateExhibition(this);
//                queryCreateExhibition.getQuery(login, exhibition);
//
//            } else {
//                hideKeyboard();
//                Toast.makeText(getContext(), "Проверьте введённые данные", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void hideKeyboard() {
//        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
//    }
//
//}
