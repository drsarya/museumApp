package com.example.museums.view.fragments.museum.mainInfoMuseumEditPage.DialogChangeDescriptionMuseum;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.museums.R;
import com.example.museums.view.fragments.museum.mainInfoMuseumEditPage.MainInfoMuseumPageEdit.MainInfoMuseumPageEdit;

public class DialogChangeDescriptionMuseum extends DialogFragment {

    static final String LOGIN_KEY = "login_key";
    private Button buttonUpdate;
    private ImageView closeDialog;
    public ProgressBar progressBar;
    private EditText descriptionEditText;
    static final String DESCRIPTION_SOURCE_KEY = "description_key";
    private String login;
    private String description;


    public DialogChangeDescriptionMuseum() {

    }

    public DialogChangeDescriptionMuseum newInstance(final String description, final String login) {
        final DialogChangeDescriptionMuseum myFragment = new DialogChangeDescriptionMuseum();
        final Bundle args = new Bundle(2);
        args.putString(DESCRIPTION_SOURCE_KEY, description);
        args.putString(LOGIN_KEY, login);
        myFragment.setArguments(args);
        return myFragment;
    }

    private void initViews(View rootVew) {

        progressBar = rootVew.findViewById(R.id.dialog_edit_description_museum_progress_bar);
        buttonUpdate = rootVew.findViewById(R.id.dialog_edit_description_museum_update_btn);
        descriptionEditText = rootVew.findViewById(R.id.dialog_edit_description_edit_text);
        closeDialog = rootVew.findViewById(R.id.dialog_edit_description_museum_close_image_view);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.dialog_edit_description_museum, container, false);
        Bundle arguments = getArguments();
        initViews(rootView);

        if (arguments != null) {
            login = arguments.getString(LOGIN_KEY);
            description = arguments.getString(DESCRIPTION_SOURCE_KEY);
            if(!description.equals(MainInfoMuseumPageEdit.descriptionIsEmpty)){

                descriptionEditText.setText(description);
            }

        }
        setListeners();
        return rootView;
    }

    private void hideKeyboard() {
        InputMethodManager imm =
                (InputMethodManager) descriptionEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive())
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void setListeners() {
        closeDialog.setOnClickListener(v ->
                dismiss());
        buttonUpdate.setOnClickListener(v -> {
            hideKeyboard();
            if (!descriptionEditText.getText().toString().isEmpty()) {
                QueryDialogChangeDescriptionMuseum queryChangeMuseumImage = new QueryDialogChangeDescriptionMuseum(this);
                queryChangeMuseumImage.getQuery(login, descriptionEditText.getText().toString());
            } else {
                Toast.makeText(getContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
            }
        });

    }
}