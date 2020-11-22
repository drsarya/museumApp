package com.example.museums.view.fragments.museum;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.museums.R;
import com.example.museums.view.fragments.common.Dialogs.DialogLogOut;
import com.example.museums.view.fragments.common.Dialogs.dialogUpdatePassword.DialogUpdatePassword;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHideDescription;

public class MainInfoMuseumPageEdit extends Fragment implements PopupMenu.OnMenuItemClickListener {
    private Button descriptionBtn;
    private TextView descriptionTextView;
    private ImageButton imbtn;
    private String login;
    public static final String LOGIN_KEY_USER = "login_key_user";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         View rootView =
                inflater.inflate(R.layout.fragment_main_info_museum_edit, container, false);


        return rootView;
    }






    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Bundle arguments = getArguments();

        if (arguments != null) {
            login = arguments.getString(LOGIN_KEY_USER);
            System.out.println(login + "ttttttttttttttttttttttttttttttttttttttttttttttt");

        } else {
            System.out.println("errrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");

        }
    }

    private void initViews() {
        imbtn = getActivity().findViewById(R.id.main_info_museum_edit_menu_popup);
        descriptionBtn = getActivity().findViewById(R.id.main_info_museum_edit_description_btn);
        descriptionTextView = getActivity().findViewById(R.id.main_info_museum_edit_description_text_view);
    }

    public void showPopup(View view) {
        Context wrapper = new ContextThemeWrapper(getActivity().getApplicationContext(), R.style.menuStyle);
        PopupMenu popup = new PopupMenu(wrapper, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_logout_change_password, popup.getMenu());
        popup.setOnMenuItemClickListener(this);
        popup.show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        initViews();
        setListeners();
    }

    private void setListeners() {
        imbtn.setOnClickListener(this::showPopup);
        descriptionBtn.setOnClickListener(new ClickListenerHideDescription(descriptionTextView));
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_page_logout:
                DialogLogOut myFragment = new DialogLogOut();
                final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                myFragment.show(ft, "dialog");
                return true;
            case R.id.menu_item_change_password:
                DialogUpdatePassword dialogUpdatePassword = new DialogUpdatePassword();
                Bundle bd = new Bundle();
                if (login != null) {
                    bd.putString(DialogUpdatePassword.LOGIN_KEY, login);
                    dialogUpdatePassword.setArguments(bd);
                }
                final FragmentTransaction ft1 = getActivity().getSupportFragmentManager().beginTransaction();
                dialogUpdatePassword.show(ft1, "dialog2");
                return true;
            default:
                return false;
        }
    }
}
