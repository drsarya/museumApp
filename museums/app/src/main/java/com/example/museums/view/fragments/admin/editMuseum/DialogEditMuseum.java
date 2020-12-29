package com.example.museums.view.fragments.admin.editMuseum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.museums.R;
import com.example.museums.view.services.Listeners.textWatchers.TextWatcherListenerCheckValidate;


import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class DialogEditMuseum extends DialogFragment {
    public final String KEY_NAME_MUSEUM = "key_name_museum";
    public final String KEY_ID_CODE = "key_id_code";
    public final String KEY_ADDRESS = "key_address";
    private EditText nameEditText, addressEditText;
    private TextView idCodeEditText;
    private String address, name, idCode;
    private Button updateInfo;
    public ProgressBar progressBar;
    private QueryEditMuseum queryEditMuseum;
    private TextFieldBoxes nameTextFieldBoxes, addressTextFieldBoxes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.dialog_detailed_museum_edit, container, false);
        initViews(rootView);
        getData();
        setListeners();
        return rootView;
    }

    private void getData() {
        Bundle bd = getArguments();
        if (bd != null) {
            address = bd.getString(KEY_ADDRESS);
            name = bd.getString(KEY_NAME_MUSEUM);
            idCode = bd.getString(KEY_ID_CODE);
            setData();
        }
    }

    private void initViews(View rootView) {
        nameEditText = rootView.findViewById(R.id.dialog_museum_edit_name_edit_text);
        addressEditText = rootView.findViewById(R.id.dialog_museum_edit_address_edit_text);
        progressBar = rootView.findViewById(R.id.dialog_museum_edit_progress_bar);
        nameTextFieldBoxes = rootView.findViewById(R.id.dialog_museum_edit_name_text_field_boxes);
        addressTextFieldBoxes = rootView.findViewById(R.id.dialog_museum_edit_address_text_field_boxes);
        idCodeEditText = rootView.findViewById(R.id.dialog_museum_edit_id_code);
        updateInfo = rootView.findViewById(R.id.dialog_museum_edit_update_btn);
        queryEditMuseum = new QueryEditMuseum(this);
    }

    private void setData() {
        nameEditText.setText(name);
        addressEditText.setText(address);
        idCodeEditText.setText(idCode);
    }

    private void setListeners() {
        addressEditText.addTextChangedListener(new TextWatcherListenerCheckValidate(addressTextFieldBoxes));
        nameEditText.addTextChangedListener(new TextWatcherListenerCheckValidate(nameTextFieldBoxes));
        updateInfo.setOnClickListener(v -> {
            if (!addressTextFieldBoxes.isOnError() && !nameTextFieldBoxes.isOnError() &&
                    !addressEditText.getText().toString().isEmpty() && !nameEditText.getText().toString().isEmpty()) {
                if (!addressEditText.getText().toString().equals(address) || !nameEditText.getText().toString().equals(name)) {
                    queryEditMuseum.getQuery(nameEditText.getText().toString(), addressEditText.getText().toString(), Integer.parseInt(idCode));
                }
            } else {
                Toast.makeText(getContext(), "Проверьте введённые данные", Toast.LENGTH_SHORT).show();
            }
        });
    }
}