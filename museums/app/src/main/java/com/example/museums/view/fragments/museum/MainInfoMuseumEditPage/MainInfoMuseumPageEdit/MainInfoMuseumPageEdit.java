package com.example.museums.view.fragments.museum.MainInfoMuseumEditPage.MainInfoMuseumPageEdit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.museums.R;
import com.example.museums.view.fragments.common.Dialogs.DialogLogOut;
import com.example.museums.view.fragments.common.Dialogs.dialogUpdatePassword.DialogUpdatePassword;
import com.example.museums.view.fragments.common.Exhibitions;
import com.example.museums.view.fragments.museum.MainInfoMuseumEditPage.DialogChangeDescriptionMuseum.DialogChangeDescriptionMuseum;
import com.example.museums.view.fragments.museum.MainInfoMuseumEditPage.DialogChangeImageMuseum.DialogChangeMuseumPhoto;
import com.example.museums.view.services.Listeners.clickListeners.ClickListenerHideDescription;

public class MainInfoMuseumPageEdit extends Fragment implements PopupMenu.OnMenuItemClickListener {
    private Button descriptionBtn;
    public TextView descriptionTextView;
    private ImageButton imbtn;
    private String login;
    private ImageView imageViewEditDescription;
    public static final String LOGIN_KEY_USER = "login_key";
    public ImageView imageViewMainImage;
    public TextView addressTextView;
    public TextView chooseImageTextView;
    public static final String descriptionIsEmpty = "Добавьте описание музея";
    public ProgressBar progressBar;
    public TextView nameOfMuseumTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =
                inflater.inflate(R.layout.fragment_main_info_museum_edit, container, false);

        return rootView;
    }
    public MainInfoMuseumPageEdit newInstance(String login) {
        final MainInfoMuseumPageEdit myFragment = new MainInfoMuseumPageEdit();
        final Bundle args = new Bundle(1);
        args.putString(LOGIN_KEY_USER, login);
        myFragment.setArguments(args);
        return myFragment;
    }

    private void getArgumentsFromBundle() {
        if (getArguments() != null) {
            login = getArguments().getString(LOGIN_KEY_USER);
        }
    }


    /*
     * Название музея nameOfMuseumTextView
     * Фото музея imageViewMainImage
     * Описани музея descriptionTextView
     * Адрес музея addressTextView
     *
     * */

    private void initViews() {
        chooseImageTextView = getActivity().findViewById(R.id.main_info_museum_edit_choose_photo_text_view);
        progressBar = getActivity().findViewById(R.id.main_info_museum_edit_progress_bar);
        nameOfMuseumTextView = getView().findViewById(R.id.main_info_museum_edit_name_of_museum_text_view);
        imageViewEditDescription = getActivity().findViewById(R.id.main_info_museum_edit_description_image_view);
        imageViewMainImage = getActivity().findViewById(R.id.main_info_museum_edit_image_view);
        imbtn = getActivity().findViewById(R.id.main_info_museum_edit_menu_popup);
        descriptionBtn = getActivity().findViewById(R.id.main_info_museum_edit_hide_description_btn);
        descriptionTextView = getActivity().findViewById(R.id.main_info_museum_edit_description_text_view);
        addressTextView = getActivity().findViewById(R.id.main_info_museum_edit_address_text_view);


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
        getArgumentsFromBundle();

        updateImageView();
    }

    public void updateImageView() {
        QueryMainInfoMuseumPageEdit q = new QueryMainInfoMuseumPageEdit(this);
        q.getImageQuery(login);

    }

    private void setListeners() {
        imbtn.setOnClickListener(this::showPopup);
        descriptionBtn.setOnClickListener(new ClickListenerHideDescription(descriptionTextView));
        imageViewMainImage.setOnClickListener(v -> {


            BitmapDrawable drawable = (BitmapDrawable) imageViewMainImage.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            DialogChangeMuseumPhoto dialogUpdatePassword = new DialogChangeMuseumPhoto().newInstance(bitmap, login);

            final FragmentTransaction ft1 = getActivity().getSupportFragmentManager().beginTransaction();
            dialogUpdatePassword.show(ft1, "dialog3");


        });
        imageViewEditDescription.setOnClickListener(v -> {

            DialogChangeDescriptionMuseum dialogUpdatePassword = new DialogChangeDescriptionMuseum().newInstance(descriptionTextView.getText().toString(), login);
            final FragmentTransaction ft1 = getActivity().getSupportFragmentManager().beginTransaction();

            dialogUpdatePassword.show(ft1, "dialog4");


        });
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
                    bd.putString(DialogUpdatePassword.ID_MUSEUM_KEY, login);
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
