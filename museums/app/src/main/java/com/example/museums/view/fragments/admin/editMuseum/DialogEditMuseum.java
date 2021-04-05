package com.example.museums.view.fragments.admin.editMuseum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.museums.API.models.enums.MuseumStateEnum;
import com.example.museums.R;
import com.example.museums.view.fragments.admin.allMuseums.AllMuseums;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerShare;
import com.example.museums.view.services.Listeners.textWatchers.TextWatcherListenerCheckValidate;


import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class DialogEditMuseum extends DialogFragment {
    public final String KEY_NAME_MUSEUM = "key_name_museum";
    public final String KEY_ID_CODE = "key_id_code";
    public final String KEY_STATE_MUSEUM = "key_state_museum";
    public final String KEY_ADDRESS = "key_address";
    private EditText nameEditText, addressEditText;
    private TextView refactorMuseumTV, loginOwnerTV;
    private String address, name;
    private Button updateInfo;
    private ProgressBar progressBar;
    private Integer idCode;
    private DialogEditMuseumViewModel viewModel;
    private TextFieldBoxes nameTextFieldBoxes, addressTextFieldBoxes;
    private String stateMuseum;
    private ImageView shareIV;
    private String owner;
    private ClickListenerShare clickListenerShare = new ClickListenerShare();

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
            idCode = bd.getInt(KEY_ID_CODE);
            stateMuseum = bd.getString(KEY_STATE_MUSEUM);
            getOwner();
            setData();
        }
    }

    public DialogEditMuseum newInstance(Integer id, String name, String address, String museumState) {
        final DialogEditMuseum myFragment = new DialogEditMuseum();
        final Bundle args = new Bundle(2);
        args.putString(KEY_ADDRESS, address);
        args.putString(KEY_STATE_MUSEUM, museumState);
        args.putString(KEY_NAME_MUSEUM, name);
        args.putInt(KEY_ID_CODE, id);
        myFragment.setArguments(args);
        return myFragment;
    }

    private void initViews(View rootView) {
        viewModel = ViewModelProviders.of(this).get(DialogEditMuseumViewModel.class);
        nameEditText = rootView.findViewById(R.id.dialog_museum_edit_name_edit_text);
        addressEditText = rootView.findViewById(R.id.dialog_museum_edit_address_edit_text);
        progressBar = rootView.findViewById(R.id.dialog_museum_edit_progress_bar);
        nameTextFieldBoxes = rootView.findViewById(R.id.dialog_museum_edit_name_text_field_boxes);
        addressTextFieldBoxes = rootView.findViewById(R.id.dialog_museum_edit_address_text_field_boxes);
        updateInfo = rootView.findViewById(R.id.dialog_museum_edit_update_btn);
        refactorMuseumTV = rootView.findViewById(R.id.dialog_detailed_museum_edit_refactor_museum_text_view);
        loginOwnerTV = rootView.findViewById(R.id.dialog_museum_edit_login_text_field_boxes);
        shareIV = rootView.findViewById(R.id.dialog_museum_edit_share_image_view);
    }


    private void setData() {
        switch (MuseumStateEnum.valueOf(stateMuseum)) {
            case NOT_ACTIVE:
                refactorMuseumTV.setText("Удалить");
                break;
            case BLOCKED:
                refactorMuseumTV.setText("Разблокировать");
                break;
            case ACTIVE:
                refactorMuseumTV.setText("Заблокировать");
                break;
        }
        nameEditText.setText(name);
        addressEditText.setText(address);

    }

    private void setListeners() {
        addressEditText.addTextChangedListener(new TextWatcherListenerCheckValidate(addressTextFieldBoxes));
        nameEditText.addTextChangedListener(new TextWatcherListenerCheckValidate(nameTextFieldBoxes));
        updateInfo.setOnClickListener(v -> {

            if (!addressTextFieldBoxes.isOnError() && !nameTextFieldBoxes.isOnError() &&
                    !addressEditText.getText().toString().isEmpty() && !nameEditText.getText().toString().isEmpty()) {
                if (!addressEditText.getText().toString().equals(address) || !nameEditText.getText().toString().equals(name)) {
                    editMuseum();
                }
            } else {
                Toast.makeText(getContext(), "Проверьте введённые данные", Toast.LENGTH_SHORT).show();
            }

        });
        refactorMuseumTV.setOnClickListener(v -> {
            if (MuseumStateEnum.valueOf(stateMuseum) == MuseumStateEnum.NOT_ACTIVE) {
                deleteMuseum();
            } else {
                refactorMuseumTV.setText("Разблокировать");
                lockMuseum();
            }
        });

        shareIV.setOnClickListener(
                clickListenerShare
         );

    }

    private void deleteMuseum() {
        viewModel.getLiveDataDeleteMuseum(idCode)
                .observe(this, model -> {
                    if (model == null) {
                        Toast.makeText(getContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), model.getMessage(), Toast.LENGTH_SHORT).show();
                        ((AllMuseums) getParentFragment()).getListMuseums();
                        dismiss();
                    }
                });
    }

    private void getOwner() {
        viewModel.getOwner(idCode)
                .observe(this, model -> {
                    if (model == null) {
                        Toast.makeText(getContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
                    } else {
                        owner = model.getMessage();
                        loginOwnerTV.setText(owner);
                        clickListenerShare.setInfo(getActivity(), createString());
                    }
                });
    }

    private void lockMuseum() {
        viewModel.getLiveDataLockMuseum(idCode)
                .observe(this, model -> {
                    if (model == null) {
                        Toast.makeText(getContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), model.getMessage(), Toast.LENGTH_SHORT).show();
                        updateInfo();
                        ((AllMuseums) getParentFragment()).getListMuseums();

                    }
                });

    }

    private void updateInfo() {
        viewModel.getLiveDataMuseum(idCode)
                .observe(this, model -> {
                    if (model == null) {
                        Toast.makeText(getContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
                    } else {
                        address = model.getAddress();
                        name = model.getName();
                        stateMuseum = model.getState().name();
                        setData();
                    }
                });
    }

    private void editMuseum() {
        viewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading) progressBar.setVisibility(View.VISIBLE);
            else progressBar.setVisibility(View.GONE);
        });
        viewModel.getLiveData(nameEditText.getText().toString(), addressEditText.getText().toString(), idCode)
                .observe(this, model -> {
                    viewModel.getIsLoading().postValue(false);
                    if (model == null) {
                        Toast.makeText(getContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), model.getMessage(), Toast.LENGTH_SHORT).show();
                        ((AllMuseums) getParentFragment()).getListMuseums();
                    }
                });


    }

    private String createString() {
        StringBuilder str = new StringBuilder();
        str.append("Поздравляем с успешной регистрацией!\n");
        str.append("Используйте следующие данные для регистрации музея: \n");
        str.append("Логин: " + owner + " \n");
        str.append("Идентификационный код: " + idCode + " \n");
        str.append("@App \"Выставочный зал\" by Darya" + "\n");
        return str.toString();
    }


}