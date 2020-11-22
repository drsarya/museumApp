package com.example.museums.view.fragments.common.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.museums.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogLogOut extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Выйти?")
                .setMessage("Вы точно хотите выйти")
                .setPositiveButton("да", (dialog, id) -> {
                    // Закрываем окно
                    // dialog.cancel();
                }).setNegativeButton("нет", (v, id) -> v.cancel());
        return builder.create();
    }
}
