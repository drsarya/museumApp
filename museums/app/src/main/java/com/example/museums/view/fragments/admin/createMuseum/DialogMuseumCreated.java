package com.example.museums.view.fragments.admin.createMuseum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.museums.R;

public class DialogMuseumCreated extends DialogFragment {

    private TextView login;
    public static final String LOGIN_KEY = "login_key";
    public static final String CODE_KEY = "code_key";
    private String loginFromBundle;
    private String codeFromBundle;

    private TextView idCode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.dialog_museum_created, container, false);
        idCode = rootView.findViewById(R.id.dialog_museum_created_id_code);
        login = rootView.findViewById(R.id.dialog_museum_created_login);
        Bundle bd = getArguments();
        if (bd != null) {
            loginFromBundle = bd.getString(LOGIN_KEY);
            codeFromBundle = bd.getString(CODE_KEY);
            setData();
        }
        return rootView;
    }

    public void setData() {
        idCode.setText(codeFromBundle);
        login.setText(loginFromBundle);
    }
}