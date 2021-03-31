package com.example.museums.view.fragments.admin.editMuseum;

import android.content.DialogInterface;
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

import com.example.museums.API.models.OkModel;
import com.example.museums.API.presenter.BasePresenter;
import com.example.museums.R;
import com.example.museums.view.fragments.admin.allMuseums.AllMuseums;
import com.example.museums.view.services.Listeners.textWatchers.TextWatcherListenerCheckValidate;


import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class DialogEditMuseum extends DialogFragment implements BasePresenter.View {
    public final String KEY_NAME_MUSEUM = "key_name_museum";
    public final String KEY_ID_CODE = "key_id_code";
    public final String KEY_ADDRESS = "key_address";
    private EditText nameEditText, addressEditText;
    private TextView idCodeEditText;
    private String address, name, idCode;
    private Button updateInfo;
    public ProgressBar progressBar;
    private DialogEditMuseumPresenter presenter = new DialogEditMuseumPresenter();
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
        presenter.attach(this);

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
            presenter.setInfo(nameEditText.getText().toString(), addressEditText.getText().toString(), idCode);
            presenter.loadData();
        });
    }

    @Override
    public <T> void showData(T data) {
        Toast.makeText(getContext(), ((OkModel) data).getMessage(), Toast.LENGTH_SHORT).show();
        ((AllMuseums) getParentFragment()).presenter.loadData();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detach();
    }


}