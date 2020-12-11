package com.example.museums.view.fragments.common.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.museums.R;
import com.example.museums.view.activities.common.Authorization.Authorization;
import com.example.museums.view.activities.common.RegistrationMuseum.RegistrationMuseum;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

public class DialogLogOut extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AboutDialog);
        builder.setTitle("Выйти?")
                .setMessage("Вы точно хотите выйти")
                .setPositiveButton("да", (dialog, id) -> {
                    while (getChildFragmentManager().getBackStackEntryCount() > 0){
                        getChildFragmentManager().popBackStack();


                    }
                    Intent intent1 = new Intent(getActivity(), Authorization.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(intent1);
                     // Закрываем окно
                      dialog.cancel();
                }).setNegativeButton("нет", (v, id) -> v.cancel());
        return builder.create();
    }
}
