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
import androidx.lifecycle.ViewModelProviders;

import com.example.museums.R;
import com.example.museums.view.services.Listeners.textWatchers.TextWatcherListenerCheckValidate;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

import static com.example.museums.view.ConstantKeys.ID_USER_KEY;

public class DialogUpdatePassword extends DialogFragment {

    private EditText oldPassEditText;
    private EditText newPassEditText;
    private TextFieldBoxes oldPassTextFieldBoxes;
    private TextFieldBoxes newPassTextFieldBoxes;
    private Button updateBtn;
    public ProgressBar progressBar;
    private Integer id;
    private DialogUpdatePasswordViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.dialog_change_password, container, false);
        initViews(rootView);
        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getInt(ID_USER_KEY);
        }
        setListeners();
        return rootView;
    }
    public static DialogUpdatePassword newInstance(Integer idUser) {
        final DialogUpdatePassword myFragment = new DialogUpdatePassword();
        final Bundle args = new Bundle(1);
        args.putInt(ID_USER_KEY, idUser);
        myFragment.setArguments(args);
        return myFragment;
    }
    private void initViews(View rootView) {
        oldPassEditText = rootView.findViewById(R.id.dialog_change_pass_old_pass_edit_text);
        newPassEditText = rootView.findViewById(R.id.dialog_change_pass_new_pass_edit_text);
        oldPassTextFieldBoxes = rootView.findViewById(R.id.dialog_change_pass_old_pass_text_field_boxes);
        newPassTextFieldBoxes = rootView.findViewById(R.id.dialog_change_pass_new_pass_text_field_boxes);
        updateBtn = rootView.findViewById(R.id.dialog_change_pass_btn);
        progressBar = rootView.findViewById(R.id.dialog_change_pass_progress_bar);
        viewModel = ViewModelProviders.of(this).get(DialogUpdatePasswordViewModel.class);
    }


    private void setListeners() {
        oldPassEditText.addTextChangedListener(new TextWatcherListenerCheckValidate(oldPassTextFieldBoxes));
        newPassEditText.addTextChangedListener(new TextWatcherListenerCheckValidate(newPassTextFieldBoxes));
        updateBtn.setOnClickListener(v -> {
            if (!oldPassEditText.getText().toString().isEmpty() && !newPassEditText.getText().toString().isEmpty() &&
                    !oldPassTextFieldBoxes.isOnError() && !newPassTextFieldBoxes.isOnError() && id != null) {
                updatePassword();
            } else {
                Toast.makeText(getContext(), "Проверьте введённые данные", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updatePassword() {

        viewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading) progressBar.setVisibility(View.VISIBLE);
            else progressBar.setVisibility(View.GONE);
        });
        viewModel.getLiveDataUpdatePassword(id, oldPassEditText.getText().toString(), newPassEditText.getText().toString())
                .observe(this, aBoolean -> {
                    viewModel.getIsLoading().postValue(false);
                    if (aBoolean == null) {
                        Toast.makeText(getContext(), "Проверьте введённые данные", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), aBoolean.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
