package com.example.museums.view.fragments.museum.editExhibition;


import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.museums.R;


public class EditExhibition extends Fragment   {


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_edit_exhibition, container, false);
         // получить список экспонатов
        //получить инфу об выставке
        // установить в адаптере
        //при нажатии на редактирование и удаление экспоната - открыть edit exhibit
        return rootView;
    }
}
