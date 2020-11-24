package com.example.museums.view.fragments.admin.createMuseum;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.museums.R;
import com.example.museums.view.services.Listeners.textWatchers.TextWatcherListenerCheckValidate;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class CreateMuseum extends Fragment {


    private Button regMuseumBtn;
    private EditText logEditText;
    private EditText nameEditText;
    private EditText addressEditText;
    private TextFieldBoxes logTextFieldBoxes;
    private TextFieldBoxes nameTextFieldBoxes;
    private TextFieldBoxes addressTextFieldBoxes;
    private QueryCreateMuseum queryCreateMuseum;
    public ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.fragment_admin_create_museum, container, false);

        return rootView;
    }

    private void initViews() {
        regMuseumBtn = getActivity().findViewById(R.id.admin_create_museum_create_museum_btn);
        logEditText = getActivity().findViewById(R.id.admin_create_museum_login_editText);
        nameEditText = getActivity().findViewById(R.id.admin_create_museum_name_of_museum_editText);
        addressEditText = getActivity().findViewById(R.id.admin_create_museum_address_of_museum_editText);
        logTextFieldBoxes = getActivity().findViewById(R.id.admin_create_museum_login_ext_field);
        nameTextFieldBoxes = getActivity().findViewById(R.id.admin_create_museum_name_of_museum_text_field);
        addressTextFieldBoxes = getActivity().findViewById(R.id.admin_create_museum_address_of_museum_text_field);
        progressBar = getActivity().findViewById(R.id.admin_create_museum_progress_bar);

    }

    private void setListeners() {
        logEditText.addTextChangedListener(new TextWatcherListenerCheckValidate(logTextFieldBoxes));
         regMuseumBtn.setOnClickListener(

                v -> {
                    queryCreateMuseum = new QueryCreateMuseum(this);
                    if (!addressTextFieldBoxes.isOnError() && !nameTextFieldBoxes.isOnError() && !logTextFieldBoxes.isOnError() &&
                            !logEditText.getText().toString().isEmpty() && !nameEditText.getText().toString().isEmpty() && !addressEditText.getText().toString().isEmpty() ) {
                        queryCreateMuseum.getQuery(logEditText.getText().toString(), nameEditText.getText().toString(), addressEditText.getText().toString());
                    } else {
                        Toast.makeText(getContext(), "Проверьте поля", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        initViews();
        setListeners();
    }
}
