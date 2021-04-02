package com.example.museums.view.fragments.common.dialogs.dialogUpdatePassword;

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
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.museums.API.models.OkModel;
import com.example.museums.R;
import com.example.museums.view.services.Listeners.textWatchers.TextWatcherListenerCheckValidate;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class DialogUpdatePassword extends DialogFragment {

    private EditText oldPassEditText;
    private EditText newPassEditText;
    private TextFieldBoxes oldPassTextFieldBoxes;
    private TextFieldBoxes newPassTextFieldBoxes;
    private Button updateBtn;
    public ProgressBar progressBar;
    private Integer id;
    public static final String ID_KEY = "id_key";
    private DialogUpdatePasswordViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.dialog_change_password, container, false);
        initViews(rootView);
        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getInt(ID_KEY);
        }
        setListeners();
        return rootView;
    }

    private void initViews(View rootView) {
        oldPassEditText = rootView.findViewById(R.id.dialog_change_pass_old_pass_edit_text);
        newPassEditText = rootView.findViewById(R.id.dialog_change_pass_new_pass_edit_text);
        oldPassTextFieldBoxes = rootView.findViewById(R.id.dialog_change_pass_old_pass_text_field_boxes);
        newPassTextFieldBoxes = rootView.findViewById(R.id.dialog_change_pass_new_pass_text_field_boxes);
        updateBtn = rootView.findViewById(R.id.dialog_change_pass_btn);
        progressBar = rootView.findViewById(R.id.dialog_change_pass_progress_bar);
    }


    private void setListeners() {
        oldPassEditText.addTextChangedListener(new TextWatcherListenerCheckValidate(oldPassTextFieldBoxes));
        newPassEditText.addTextChangedListener(new TextWatcherListenerCheckValidate(newPassTextFieldBoxes));
        updateBtn.setOnClickListener(v -> {
            updatePassword();
        });
    }

    private void updatePassword() {
        viewModel = ViewModelProviders.of(this).get(DialogUpdatePasswordViewModel.class);
        viewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) progressBar.setVisibility(View.VISIBLE);
                else progressBar.setVisibility(View.GONE);
            }
        });
        viewModel.getLiveDataUpdatePassword(id, oldPassEditText.getText().toString(), newPassEditText.getText().toString())
                .observe(this, new Observer<OkModel>() {
                    @Override
                    public void onChanged(@Nullable OkModel aBoolean) {
                        viewModel.getIsLoading().postValue(false);
                        if (aBoolean == null) {
                            Toast.makeText(getContext(), "Проверьте введённые данные", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), aBoolean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
